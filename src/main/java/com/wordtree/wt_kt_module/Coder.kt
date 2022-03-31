package com.wordtree.wt_kt_module

import com.wordtree.wt_config.Index_Config
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.application.Application
import javafx.application.Platform
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.Alert.AlertType
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.ImagePattern
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import org.fxmisc.flowless.VirtualizedScrollPane
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


private val codeArea = CodeArea()
private val root = BorderPane()
private val topBar = MenuBar()
private val userBox = VBox()
private val USER_WIDTH = 340.0
private val centerPane = SplitPane()
//左侧文件树
private var file: File? = File("D:\\3.Node.js\\项目以及学习目录\\JavaScript学习") //这个是整个编辑器的母文件夹
private val fileItemRoot = TreeItem<Label>(Label("--项目名称--"))
private val fileTreeView = TreeView(fileItemRoot)
private var fileViewOpen = true
private var cursorId = ArrayList<String>() //记录所有tab标签的id值
//编辑区上面的tap标签页
private val tabPane = TabPane()
val fileTab = VBox()//放置tab和它文本编辑器的盒子
//用户的头像
private val ImageSize = R.textName("System.User.Image.Size").toDouble()
private val gradeBox = HBox()
private val grade = Label()
private val tabSeed = TabPane()
//用户的名称签名
private val textUserBox = VBox()
private val nameBox = HBox()
private val editBut = Label()
private val pi2 = ProgressBar(0.6)//这个对象是进度条
private  val name = Label()
private val motto = TextField(Index_Config.App_User_Motto)
//显示用户进度以及用户的插件箱
private val manuscriptBox = VBox()
private val userSpeedProgressBox = VBox()
//编辑器当前文本对象
private var row = 0
private var col = 0
private var caretPosition = 0
private var cursorPosition:HashMap<String, Any> = HashMap() //每一个tab文本都有对应的自己的光标值
private var nowFile: File? = null
private var indexFileName = SimpleIntegerProperty(0)
private var fileBaocun = 0

class Coder():Application(){
    override fun start(primaryStage: Stage) {
        val scene = Scene(root)
        scene.stylesheets.add("static/css/disanfan.css")
        primaryStage.scene = scene
        primaryStage.width = R.textName("ADMIN_WIDTH").toDouble()
        primaryStage.height = R.textName("ADMIN_HEIGHT").toDouble()
        primaryStage.icons.add(Image(Index_Config.APP_ICON))
        //因为动态调用链的需要，必须要把布局放在这个位置
        布局()
        primaryStage.show()
    }
    fun show(){
        Thread {
            val stage = Stage()
            stage.scene = Scene(root).apply {
                stylesheets.add("static/css/disanfan.css")
            }
            布局()
            stage.width = R.textName("ADMIN_WIDTH").toDouble()
            stage.height = R.textName("ADMIN_HEIGHT").toDouble()
            stage.icons.add(Image(Index_Config.APP_ICON))
            stage.title = Index_Config.APP_NAME
            stage.show()
        }.run()
    }

    private fun 属于用户的操作逻辑区域(){
        //用户部分布局
        userBox.apply {
            maxWidth = USER_WIDTH
            minWidth = 0.0
            val userLineBox = HBox()
            //用户的名片部分
            userLineBox.apply {
                name.text = Index_Config.APP_NAME
                grade.text = "lv:45"
                val userHeadPortrait = Image(R.ImageUrl("System.User.Image"))
                val userImage = Circle(ImageSize, ImagePattern(userHeadPortrait))

                prefHeight = 120.0
                background = Background(BackgroundFill(Paint.valueOf("#ff7875"),null,null))
                padding = Insets(20.0,0.0,0.0,20.0)
                //用户的名称签名
                textUserBox.apply {
                    spacing = 10.0
                    name.style = """
                        -fx-text-fill:#f0f0f0;
                        -fx-font-size: 20;
                    """.trimIndent()

                    nameBox.apply {
                        editBut.graphic = ImageView(Image("static/img/编辑.png",15.0,15.0,true,true))
                        editBut.padding = Insets(7.0,0.0,0.0,0.0)
                        children.addAll(name,editBut)
                        spacing = 10.0
                    }

                    pi2.styleClass.add("progressbarYt")
                    gradeBox.children.addAll(grade,pi2)
                    gradeBox.spacing = 10.0
                    children.addAll(nameBox,gradeBox,motto)
                    padding = Insets(3.0, 0.0, 0.0, 10.0)
                }
                children.addAll(userImage,textUserBox)
            }
            //顶层选择栏部分
            val userRecord = VBox()
            userRecord.apply {
                prefHeight = 320.0
                //首先是一个tap页，这个页可以切换，让我们查看不同的状态
                val laohuantou = Tab("老环头").apply { this.isClosable = false }
                val heihun = Tab("黑魂").apply { this.isClosable = false }
                val zhilang = Tab("只狼").apply { this.isClosable = false }
                tabSeed.tabs.addAll(
                    laohuantou,heihun,zhilang
                )
                tabSeed.selectionModel.selectedItemProperty().addListener{ _,old,new->
                    if (new == laohuantou){
                        println("老头环")
                    }else if(new == heihun){
                        println("黑魂")
                    }else if(new == zhilang){
                        println("直男")
                    }
                }
                //常用工具箱
                manuscriptBox.styleClass.add("manuscript-box")
                manuscriptBox.padding = Insets(10.0)
                manuscriptBox.apply {
                    children.addAll(
                        Label("常用工具").apply {
                            padding = Insets(10.0,0.0,10.0,0.0)
                        },
                        GridPane().apply {
                            val iconUrl = "static/img/用户工具箱icon/"
                            //设置垂直间距
                            this.vgap = 8.0;
                            //设置水平间距
                            this.hgap = 10.0;
                            val toolboxItem1 = Button()
                            toolboxItem1.graphic = ImageView(Image(iconUrl.plus("jifen.png"),18.0,18.0,true,true))
                            val toolboxItem2 = Button()
                            toolboxItem2.graphic = ImageView(Image(iconUrl.plus("kabao.png"),18.0,18.0,true,true))
                            val toolboxItem3 = Button()
                            toolboxItem3.graphic = ImageView(Image(iconUrl.plus("pinglun.png"),18.0,18.0,true,true))
                            val toolboxItem4 = Button()
                            toolboxItem4.graphic = ImageView(Image(iconUrl.plus("shaixuan.png"),18.0,18.0,true,true))
                            val toolboxItem5 = Button()
                            toolboxItem5.graphic = ImageView(Image(iconUrl.plus("shandian.png"),18.0,18.0,true,true))
                            val toolboxItem6 = Button()
                            toolboxItem6.graphic = ImageView(Image(iconUrl.plus("shipin.png"),18.0,18.0,true,true))
                            val toolboxItem7 = Button()
                            toolboxItem7.graphic = ImageView(Image(iconUrl.plus("shizhong.png"),18.0,18.0,true,true))
                            val toolboxItem8 = Button()
                            toolboxItem8.graphic = ImageView(Image(iconUrl.plus("shoucang.png"),18.0,18.0,true,true))
                            add(toolboxItem1,0,0)
                            add(toolboxItem2,1,0)
                            add(toolboxItem3,2,0)
                            add(toolboxItem4,3,0)
                            add(toolboxItem5,0,1)
                            add(toolboxItem6,1,1)
                            add(toolboxItem7,2,1)
                            add(toolboxItem8,3,1)

                            //提示用户当前鼠标停留的位置是什么用法
                            val components = CommonComponents()
                            components.simplePromptBox("金币",toolboxItem1)
                            components.simplePromptBox("金币",toolboxItem2)
                            components.simplePromptBox("金币",toolboxItem3)
                            components.simplePromptBox("金币",toolboxItem4)
                            components.simplePromptBox("金币",toolboxItem5)
                            components.simplePromptBox("金币",toolboxItem6)
                            components.simplePromptBox("金币",toolboxItem7)
                            components.simplePromptBox("金币",toolboxItem8)
                        }
                    )
                }

                //monitor这里是提醒用户的进度
                userSpeedProgressBox.apply {
                    padding = Insets(10.0)
                    val label = Label("实时进度")
                    label.apply {
                        padding = Insets(10.0,0.0,10.0,0.0)
                    }
                    val context = GridPane()
                    context.apply {
                        styleClass.add("cart-content")
                        //设置垂直间距
                        this.vgap = 8.0;
                        //设置水平间距
                        this.hgap = 10.0;
                        context.add(Label("本次字数").apply { styleClass.add("topLabel") },0,0)
                        context.add(Label("输入速度(字/时)").apply { styleClass.add("topLabel") },1,0)
                        context.add(Label("码字时间").apply { styleClass.add("topLabel") },2,0)

                        val timerIcon = ImageView(Image("static/img/计时器.png", 15.0, 15.0, true, true))
                        val timerIcon2 = ImageView(Image("static/img/计时器2.png", 15.0, 15.0, true, true))
                        val timerLabel = Label().apply { styleClass.add("topLabel");graphic=timerIcon2;}

                        context.add(timerLabel, 4, 0)

                        context.add(Label("0").apply { styleClass.add("bottomLabel") },0,1)
                        context.add(Label("0").apply { styleClass.add("bottomLabel") },1,1)

                        //这个是计时器代码
                        var second = 1;
                        val dateFormat = SimpleDateFormat("HH时mm分ss秒")
                        dateFormat.timeZone = TimeZone.getTimeZone("GMT+0");
                        var timerStr:StringProperty = SimpleStringProperty("00时00分00秒")
                        val timer = Label().apply { styleClass.add("bottomLabel");this.textProperty().bind(timerStr)}
                        context.add(timer,2,1)

                        //计时器事件
                        timerLabel.onMouseEntered= EventHandler {
                            timerLabel.graphic = timerIcon
                        }
                        timerLabel.onMouseExited= EventHandler {
                            timerLabel.graphic = timerIcon2
                        }
                        val shop = SimpleBooleanProperty(false)
                        val thread = Thread {
                            while (true) {
                                if (shop.get()){
                                    Thread.sleep(1000);
                                    Platform.runLater {
                                        //这个是他的总时间用秒计算
                                        timerStr.set(dateFormat.format(Date(second*1000L)))
                                        second++
                                    }
                                }

                            }
                        }
                        thread.start()
                        timerLabel.onMouseClicked = EventHandler {
                            shop.set(!(shop.value))
                            println(shop)
                        }
                    }
                    children.addAll(label,context)
                }
                children.addAll(tabSeed,manuscriptBox,userSpeedProgressBox)
            }
            //书架#b5f5ec
            val userBookshelf = VBox()
            userBookshelf.apply {
                val labelName = Label("我的书架")
                labelName.apply {
                    padding = Insets(7.0,0.0,7.0,10.0)
                    styleClass.add("label-name")
                }
                val bookProject = ScrollPane()
                bookProject.apply {
                    this.prefHeightProperty().bind(root.scene.window.heightProperty().multiply(0.45))
                    this.hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
                    this.padding = Insets(10.0)
                    val box = HBox()
                    val bookData = FlowPane(20.0,10.0)
                    box.prefWidth = 290.0
                    box.children.add(bookData)
                    bookData.alignment = Pos.CENTER_LEFT
                    bookData.children.addAll(
                        BookBox(),
                        BookBox(),
                        BookBox(),
                        BookBox(),
                        BookBox(),
                        BookBox(),
                        BookBox(),
                    )
                    this.content = box
                }
                children.addAll(labelName,bookProject)
            }
            children.addAll(userLineBox,userRecord,userBookshelf)
        }
    }

    private fun 文件树(){
        indexFileName.addListener{
            _,_,new->
            if (new.toInt()==0){
                Thread{
                    println("conzhil")
                    Thread.sleep(40);
                    Platform.runLater {
                        codeArea.clear()
                    }
                }.start()
            }
        }
        //这个是文件树部分
        fileTreeView.apply {
            Thread{
                if (file != null){
                    val listFiles = file!!.listFiles()
                    添加一个文件节支(listFiles, fileItemRoot)
                }
            }.start()
        }
    }

    private fun 添加一个文件节支(listFile: Array<File>, itemUi: TreeItem<Label>) {
        for (file in listFile) {
            if (file.isDirectory) {
                val label = Label(file.name)
                label.onMouseClicked = EventHandler {
                    println(file)
                }
                val item = TreeItem(label)
                添加一个文件节支(file.listFiles(),item)
                itemUi.children.add(item)
            } else {
                val label = Label(file.name)
                label.onMouseClicked = EventHandler {
                    if (it.clickCount >= 2){
                        文件树点击事件对象(file)
                    }
                }
                val item = TreeItem(label)
                itemUi.children.add(item)
            }
        }
    }

    //这个方法用于显示文字到编辑器上面
    private fun 显示文本到编辑区(file: File, textArea: CodeArea){
        fileBaocun = 0
        if (cursorPosition.get(file.path.plus("change_times"))==null){
            val 记录文件被改变的次数 = 0
            nowFile?.let { file -> cursorPosition.put(file.path.plus("change_times"), 记录文件被改变的次数 as Any) }
        }else{
            cursorPosition.set(file.path.plus("change_times"),0)
        }
        val isr = InputStreamReader(FileInputStream(file), "UTF-8")
        val reader = BufferedReader(isr)
        val readerString = reader.readText()
        isr.close()
        textArea.replaceText(0,0,readerString)
    }

    private fun 菜单栏(){
        //这里是上界面menu的内容
        val 新建 = MenuItem(Index_Config.FileMenu_新建)
        val 保存 = MenuItem(Index_Config.FileMenu_保存)
        val 打开文件 = MenuItem(Index_Config.FileMenu_打开)
        val 打开文件夹 = MenuItem(Index_Config.FileMenu_打开文件夹)
        val menu = Menu("文件")
        menu.items.addAll(新建,保存,打开文件,打开文件夹)
        topBar.menus.addAll(menu)//将menu放到菜单栏当中
        root.top = topBar

        新建.onAction = EventHandler {
            println("我点击了新建")
        }
        保存.onAction = EventHandler {
            保存文件()
        }
        打开文件.onAction = EventHandler {

        }
        打开文件夹.onAction = EventHandler {
            val chooser = DirectoryChooser()
            chooser.title = "请选择文件夹"
            val fileChoose = chooser.showDialog(topBar.scene.window)
            if (fileChoose != null){
                file = fileChoose
                fileItemRoot.value.text = file!!.name
                文件树()
            }
            println(file)
        }
    }
    private fun 保存文件(){
        val writer = BufferedWriter(OutputStreamWriter(FileOutputStream(nowFile),"utf-8"))
        writer.write(codeArea.text)
        val stage = root.scene.window as Stage
        stage.title = ""
        writer.close()
    }

    private fun 内容区(){
        //将codearea里面的内容绑定到readerText里面
        codeArea.textProperty().addListener{
            or,old,new->
            fileBaocun++
            val stage = codeArea.scene.window as Stage
            stage.title = ""
            if (new != ""&& fileBaocun>1){
                记录光标位置()
                stage.title = "*"
            }
            /* val plus = nowFile?.path.plus("change_times")
             if (cursorPosition.get(plus) != null){
                 var index = cursorPosition.get(plus) as Int
                 println(new == "")
                 if (new!=""){
                     index++
                 }
                 println("index1:\t"+index)
                 val textIcon = cursorPosition.get(nowFile?.path.plus("icon_text")) as SimpleStringProperty
                 if (index > 1){
                     println("index2:\t"+index+"\t"+ nowFile?.name)
                     textIcon.set("*".plus(nowFile?.name))
                 }
                 cursorPosition.set(nowFile?.path.plus("change_times"),index as Any)
             }*/
        }

        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        //这个是tab文件操作部分
        centerPane.apply {
            codeArea.prefHeightProperty().bind(root.scene.window.heightProperty().add(-100))
            fileTab.children.addAll(tabPane, VirtualizedScrollPane(codeArea))
            this.setDividerPosition(0,-0.1)
            this.setDividerPosition(1,0.5)
            root.center = centerPane
        }
        //每次点击编辑器记录光标的位置
        codeArea.onMouseClicked = EventHandler {
            记录光标位置()
        }
        //每当codeArea发生键盘输入事件，都会对光标位置进行一次刷新
        codeArea.setOnKeyPressed{
            记录光标位置()
        }
    }

    private fun 左侧项目栏(){
        val but = Button("项".plus("\n目")).apply {
            prefWidth = 30.0
            onMouseClicked = EventHandler {
                if(!fileViewOpen) {
                    centerPane.setDividerPosition(0,0.2)
                    fileViewOpen = true
                }else {
                    centerPane.setDividerPosition(0,0.0)
                    fileViewOpen = false
                }
            }
            prefHeight = 60.0
            styleClass.add("legend")
        }
        root.left = VBox().apply {
            prefWidth =30.0
            children.addAll(but)
        }
    }

    private fun 布局(){
        //这里是上界面menu的内容
        菜单栏()

        //用户部分布局
        属于用户的操作逻辑区域()

        //这个是文件树部分
        文件树()

        内容区()

        左侧项目栏()
        //确定内容区布局,包过文件树,文件tab,和用户box
        centerPane.items.addAll(fileTreeView,fileTab,userBox)
    }

    private fun 文件树点击事件对象(file: File){
        nowFile = file
        if (cursorId.indexOf(file.path) == -1){
            val iconText = SimpleStringProperty("")
            cursorPosition.put(file.path.plus("icon_text"),iconText as Any)
            tab标签的切换与文本区光标的聚焦(tabPane, codeArea,file)
        }else{
            val filter = tabPane.tabs.filter { it.id == file.path }
            tabPane.selectionModel.select(filter[0])
        }
    }

    private fun 记录光标位置(){
        caretPosition = codeArea.caretPosition
        nowFile?.let { file -> cursorPosition.set(file.path, caretPosition as Any) }
    }

    private fun tab标签的切换与文本区光标的聚焦(tabPane: TabPane, codeAreaRequest: CodeArea, file: File){
        //添加头部标签
        val tab = Tab()
        tab.id = file.path
        val textIcon = cursorPosition.get(file.path.plus("icon_text")) as SimpleStringProperty
        textIcon.set(file.name)
        tab.textProperty().bind(textIcon)

        //当tab被父类方法selectionModel调用的时候发生下面事件
        tab.onSelectionChanged = EventHandler {
            if ( (root.scene.window as Stage).title == "*" ){
                保存文件()
            }
            codeArea.clear()
            显示文本到编辑区(file,codeAreaRequest)
            codeAreaRequest.requestFocus() //聚焦到文本区
            cursorPosition[file.path]?.let { position -> codeAreaRequest.moveTo(position as Int);codeAreaRequest.showParagraphInViewport(position) }
        }
        //当tab标签发生改变的时候，将光标位置聚焦到上一次光标位置
        tabPane.selectionModel.selectedItemProperty().addListener { _, _, new ->
            if (new!=null){
                Platform.runLater {  codeAreaRequest.requestFocus() }
            }

        }
        //关闭标签的时候清空整个文本区，删除cursorText里面的值,必须要放在最后这个位置
        tab.onCloseRequest = EventHandler {
//            if (cursorPosition.get(file.path.plus("change_times")) as Int > 1){
            if (fileBaocun > 1){
                val alert = Alert(
                    AlertType.CONFIRMATION, "", ButtonType("不保存", ButtonBar.ButtonData.APPLY),
                    ButtonType("保存", ButtonBar.ButtonData.YES),ButtonType("取消", ButtonBar.ButtonData.NO)
                )
                alert.title = "警告！！！"
                alert.headerText = "文件还未保存确定关闭吗？"
                alert.contentText = "需要保存文件请点击ok！\n点击取消将不保存文件"
                val result = alert.showAndWait()
                if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                    //删除与该文件有关的数据
                    cursorId.remove(file.path)
                    cursorPosition.remove(nowFile?.path)
                    cursorPosition.remove(nowFile?.path.plus("change_times"))
                    cursorPosition.remove(nowFile?.path.plus("icon_text"),file.name)
                    保存文件()
                    it.clone()
                } else if (result.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
                    it.consume()
                }else if (result.get().getButtonData().equals(ButtonBar.ButtonData.APPLY)){
                    cursorId.remove(file.path)
                    it.clone()
                }
            }else{
                cursorId.remove(file.path)
            }
            indexFileName.set(cursorId.size)
        }
        tabPane.tabs.add(tab)
        tabPane.selectionModel.select(tab)
        cursorId.add(file.path)
        indexFileName.set(cursorId.size)
    }
}

class BookBox(var name:String = "作者：你自己",var date:Date = Date(),var zishu:Int = 0,var image: Image = Image("static/img/486-2.jpg",105.0,137.0,true,true)):VBox(){
    private val nameLabel = Label(name)
    private val dateLabel = Label(SimpleDateFormat("yyyy-MM-dd").format(this.date))
    private val zishuLabel = Label((this.zishu/10000).toString().plus("万字"))
    init {
        spacing = 5.0
        styleClass.add("book")
        padding = Insets(8.0,15.0,5.0,15.0)
        children.addAll(ImageView(this.image),nameLabel,dateLabel,zishuLabel)
        alignment = Pos.CENTER
    }
}

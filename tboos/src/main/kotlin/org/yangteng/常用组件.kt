package org.yangteng

import javafx.application.Application
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.collections.ListChangeListener
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.ClipboardContent
import javafx.scene.input.MouseEvent
import javafx.scene.input.TransferMode
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import javafx.scene.text.TextAlignment
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.io.InputStream

open class 按钮() : Button() {
    private val 按钮_Css = 按钮::class.java.getResource("按钮_样式表.css").toExternalForm()
    private val ripplerFill = SimpleObjectProperty<Paint>()

    constructor(按钮名: String = "按钮", 点击次数: Int = 1, 点击事件: () -> Unit = {}, 按钮类型: 按钮_类型 = 按钮_类型.无) : this() {
        this.apply {
            text = 按钮名
            stylesheets.add(按钮_Css)
            initStyle(按钮类型)
        }
        this.onMouseClicked = EventHandler {
            if (it.clickCount == 点击次数) {
                点击事件()
            }
        }
    }

    constructor(按钮名: String, 按钮类型: 按钮_类型) : this() {
        this.apply {
            text = 按钮名
            stylesheets.add(按钮_Css)
            initStyle(按钮类型)
        }
    }

    constructor(graphic: Node, 按钮类型: 按钮_类型, 点击事件: () -> Unit = {}) : this() {
        this.apply {
            this.graphic = graphic
            stylesheets.add(按钮_Css)
            initStyle(按钮类型)
        }
        this.onMouseClicked = EventHandler {
            点击事件()
        }
    }

    fun initStyle(按钮类型: 按钮_类型) {
        when (按钮类型) {
            按钮_类型.无 -> {
                this.styleClass.add("white")
            }
            按钮_类型.绿色 -> this.styleClass.add("green")
            按钮_类型.红色 -> this.styleClass.add("red")
            按钮_类型.蓝色 -> this.styleClass.add("blue")
            按钮_类型.黑暗 -> this.styleClass.add("none")
            按钮_类型.警告 -> this.styleClass.add("yellow")
        }
    }

    fun java代码书写css样式() {
        this.style = """
            -fx-text-fill: #ffff;
            -fx-background-color: #595959;
            -fx-border-radius: 15px;
            -fx-background-radius: 15px;
            -fx-min-width: 100px;
            -fx-min-height: 26px;
            -fx-font-size: 20;
            -fx-border-color: #595959;
        """.trimIndent()
        this.hoverProperty().addListener { _, _, new ->
            if (new) {
                this.style = """
                -fx-background-color: derive(#595959,40%);
                """.trimIndent()
            } else {
                this.style = """
                -fx-background-color: #595959;
                """.trimIndent()
            }
        }
    }

}

open class 对话框(node: Node, vararg buttonType: ButtonType) : Dialog<ButtonType>() {
    private val selfDialogPane = MyDialogPane(this)

    init {
        selfDialogPane.content = node
        this.dialogPane = selfDialogPane
        if (buttonType.size>0){
            selfDialogPane.buttonTypes.addAll(*buttonType)
        }
    }

    class MyDialogPane(val selfDialog: 对话框) : DialogPane() {
        override fun createButton(buttonType: ButtonType): Node {
            val buttonData = buttonType.buttonData
            val button = when (buttonData) {
                ButtonBar.ButtonData.YES -> 按钮(buttonType.text, 按钮_类型.蓝色)
                ButtonBar.ButtonData.NO -> 按钮(buttonType.text, 按钮_类型.无)
                ButtonBar.ButtonData.APPLY -> 按钮(buttonType.text, 按钮_类型.绿色)
                ButtonBar.ButtonData.CANCEL_CLOSE -> 按钮(buttonType.text, 按钮_类型.红色)
                else -> 按钮(buttonType.text, 按钮_类型.红色)
            }
            button.addEventHandler(ActionEvent.ACTION) { ae: ActionEvent ->
                selfDialog.close()
            }
            ButtonBar.setButtonData(button, buttonData)

            return button
        }
    }
}

open class 选项卡_布局 : TabPane() {
    init {
//        this.stylesheets.add(选项卡_布局::class.java.getResource("选项卡.css").toExternalForm())
    }
    fun 拖动事件(): VBox {
        var pos = 0
        var prePos = 0
        val vBox = VBox()
        var 判断添加那个颜色 = true
        val listView = ListView<Label>()
        //这个是基本属性
        listView.apply {
            this.orientation = Orientation.HORIZONTAL
            this.prefHeight = 0.0
            this.isVisible = false
        }
        this@选项卡_布局.apply {
            this@选项卡_布局.onDragDetected = EventHandler {
                val drop = this@选项卡_布局.startDragAndDrop(TransferMode.MOVE)
                val content = ClipboardContent()
                content.putString(this@选项卡_布局.selectionModel.selectedItem.text)
                drop.setContent(content)
                listView.prefHeight = 50.0
                listView.isVisible = true
            }
            this@选项卡_布局.onDragDone = EventHandler {
                listView.prefHeight = 0.0
                listView.isVisible = false
                if (pos < this.tabs.size) {
                    val tab = this.selectionModel.selectedItem
                    this.tabs.removeAt(prePos)
                    this.tabs.add(pos, tab)
                    this.selectionModel.select(pos)
                } else {
                    this.tabs.add(this.selectionModel.selectedItem)
                    this.tabs.removeAt(prePos)
                    this.selectionModel.selectLast()
                }
            }
            this@选项卡_布局.tabs.addListener(ListChangeListener {
                if (it.next()) {
                    if (it.wasAdded()) {
                        //添加该选项卡
                        val label = Label()
                        label.apply {
                            prefWidth = 100.0
                            prefHeight = 50.0
                        }
                        label.onDragOver = EventHandler {
                            try {
                                if (pos < this.tabs.size) {
                                    listView.items.get(pos).style = "-fx-background-color: #ffff"
                                    this.tabs.get(pos).style = "-fx-border-color: none"
                                }
                                this.tabs.get(this.tabs.size - 1).style = "-fx-border-color: none"
                                val indexOf = listView.items.indexOf(label)
                                pos = indexOf
                                label.style = """
                                    -fx-background-color: red;
                                """.trimIndent()
                            } catch (err: IndexOutOfBoundsException) {
                                println("没有该值，请重新拖拽")
                            }
                        }
                        //为label添加拖拽检测事件
                        listView.items.add(label)
                    } else if (it.wasRemoved()) {
                        //删除该选项卡
                        listView.items.removeAt(pos)
                    }
                }

            })
            this@选项卡_布局.onDragOver = EventHandler {
                //获取拖拽之后的位置
                val index = this.selectionModel.selectedIndex
                prePos = index
            }
        }
        vBox.children.addAll(listView, this)
        return vBox
    }



    @Deprecated("该方法已经被弃用了")
    fun 拖动事件(pane: TabPane): VBox {
        var pos = 0
        var prePos = 0
        val vBox = VBox()
        val listView = ListView<Label>().apply {
            orientation = Orientation.HORIZONTAL
            prefHeight = 0.0
            isVisible = false
            prefWidthProperty().bind(pane.widthProperty())
            for (i in pane.tabs) {
                val label = Label(i.text)
                label.onDragOver = EventHandler {
                    try {
                        if (pos < pane.tabs.size) {
                            this.items.get(pos).style = "-fx-background-color: #ffff"
                            pane.tabs.get(pos).style = "-fx-border-color: none"
                        }
                        pane.tabs.get(pane.tabs.size - 1).style = "-fx-border-color: none"

                        val indexOf = this.items.indexOf(label)
                        pos = indexOf
                        label.style = """
                        -fx-background-color: red;
                    """.trimIndent()
                    } catch (err: IndexOutOfBoundsException) {
                        println("没有该值，请重新拖拽")
                    }
                }
                this.items.add(label)
            }
        }

        vBox.children.addAll(listView, pane)
        //为pane注册拖拽事件
        pane.apply {
            tabs.addListener(ListChangeListener {

            })
            onDragDetected = EventHandler {
                val drop = pane.startDragAndDrop(TransferMode.MOVE)
                val content = ClipboardContent()
                content.putString(pane.selectionModel.selectedItem.text)
                drop.setContent(content)
                listView.prefHeight = 50.0
                listView.isVisible = true
            }
            onDragDone = EventHandler {
                listView.prefHeight = 0.0
                listView.isVisible = false
                if (pos < pane.tabs.size) {
                    val tab = pane.selectionModel.selectedItem
                    pane.tabs.get(pos).style = "-fx-border-color: none"
                    pane.tabs.removeAt(prePos)
                    pane.tabs.add(pos, tab)
                    pane.selectionModel.select(pos)
                } else {
                    pane.tabs.add(pane.selectionModel.selectedItem)
                    pane.tabs.removeAt(prePos)
                    pane.selectionModel.selectLast()
                }
            }
            onDragOver = EventHandler {
                //获取拖拽之后的位置
                val index = pane.selectionModel.selectedIndex
                println("拖拽之前的位置：$index")
                prePos = index
            }
        }

        return vBox
    }

}

open class 选项卡 : Tab() {

    init {
        tabRightClickEvent()
    }

    //添加右键点击事件
    private fun tabRightClickEvent(){
        val contextMenu = ContextMenu()
        val menuClose = MenuItem("关闭")
        val menuCloseOthrn = MenuItem("关闭其他")
        val menuCloseAll = MenuItem("关闭所有")
        menuClose.addEventHandler(ActionEvent.ACTION) {
            this.tabPane.tabs.remove(this)
        }
        menuCloseOthrn.addEventHandler(ActionEvent.ACTION) {
            this.tabPane.tabs.removeIf {
                it != this
            }
        }
        menuCloseAll.addEventHandler(ActionEvent.ACTION) {
            this.tabPane.tabs.removeIf {
                true
            }
        }
        contextMenu.apply {
            items.addAll(menuClose,menuCloseOthrn,menuCloseAll)
        }

        this.contextMenu = contextMenu
    }
}

open class 标签

open class 文件树():TreeView<Label>(){
    init {

    }
}


class 图标() : ImageView(){
    constructor(
        imgUrl: InputStream,
        width: Double = 14.0,
        height: Double = 14.0,
        image: Image = Image(imgUrl,
            width,
            height,
            true,
            true)
    ) : this() {
        this.image = image
    }
    constructor(
        imgUrl: String,
        width: Double = 14.0,
        height: Double = 14.0,
        image: Image = Image(imgUrl,
            width,
            height,
            true,
            true)
    ):this(){

    }

}

open class 自定义窗口(node:Node,val SHOW_WIN_WIDTH:Double = 300.0, val SHOW_WIN_HEIGTH:Double = 500.0): Stage(){
    private var xOffset = 0.0
    private var yOffset = 0.0 //自定义dialog移动横纵坐标
    private val iconSize = 25.0
    private val horizontalBar = AnchorPane()
    var myIsAlwaysOnTop = true
    set(value){
        field = value
        this.isAlwaysOnTop = value
    }
    var icon = Label().apply { graphic = 图标(获取当前目录路径(自定义窗口::class.java, "img/ic3.png")) }
           set(value) {
               field = value
               val index = this.horizontalBar.children.indexOf(icon)
               this.horizontalBar.children.remove(icon)
               this.horizontalBar.children.add(index,icon)
           }
    init {
        this.isAlwaysOnTop = myIsAlwaysOnTop;
        this.maxWidth = SHOW_WIN_WIDTH
        this.maxHeight = SHOW_WIN_HEIGTH
        this.minHeight = SHOW_WIN_WIDTH
        this.minWidth = SHOW_WIN_HEIGTH
        //隐藏windows平台原有的样式
        this.initStyle(StageStyle.TRANSPARENT)
        val box = VBox()
        //设置窗口横幅
        setHorizontalBarStyle(box)
        //设置窗口内容
        box.children.add(node)
        this.scene = Scene(box)
        MouseDragEvent(box,this)
    }

    //窗口的拖动事件
    private fun MouseDragEvent(root: Node, primaryStage: Stage) {
        //监听窗口的x跟y值，当他们发生改变的时候刷新值
        primaryStage.xProperty()
            .addListener { observable: ObservableValue<out Number?>?, oldValue: Number?, newValue: Number? ->
                if (newValue != null) {
                    x = newValue.toDouble()
                }
            }
        primaryStage.yProperty()
            .addListener { observable: ObservableValue<out Number?>?, oldValue: Number?, newValue: Number? ->
                if (newValue != null) {
                    y = newValue.toDouble()
                }
            }

        //当鼠标点击该位置的时候获取横纵坐标
        root.onMousePressed = EventHandler { event: MouseEvent ->
            event.consume()
            xOffset = event.sceneX
            if (event.sceneY > 46) {
                yOffset = 0.0
            } else {
                yOffset = event.sceneY
            }
        }

        //根据横纵坐标的位置，监听鼠标的拖动事件，改变窗体位置
        root.onMouseDragged = EventHandler { event: MouseEvent ->
            //根据鼠标的横纵坐标移动dialog位置
            if (yOffset != 0.0) {
                primaryStage.x = event.screenX - xOffset
                if (event.screenY - yOffset < 0) {
                    primaryStage.y = 0.0
                } else {
                    primaryStage.y = event.screenY - yOffset
                }
            }
        }
    }

    //设置窗口横幅的样式
    private fun setHorizontalBarStyle(box:VBox){
        /*val close = Label().apply { graphic = 图标(获取当前目录路径(自定义窗口::class.java, "img/关闭.png"),iconSize,iconSize) }
         val min = Label().apply { graphic = 图标(获取当前目录路径(自定义窗口::class.java, "img/最小化.png"),iconSize,iconSize) }
         val max = Label().apply{ graphic = 图标(获取当前目录路径(自定义窗口::class.java, "img/最大化.png"),iconSize,iconSize) }*/
        val close = Label().apply { text = "X";prefWidth = iconSize;prefHeight = iconSize;textAlignment = TextAlignment.CENTER }
        val min = Label().apply { text = "-" ;prefWidth = iconSize;prefHeight = iconSize;textAlignment = TextAlignment.CENTER}
        val max = Label().apply{ text = "口";prefWidth = iconSize;prefHeight = iconSize ;textAlignment = TextAlignment.CENTER}
        close.layoutX = SHOW_WIN_HEIGTH
        min.layoutX = SHOW_WIN_HEIGTH - 30
        max.layoutX = SHOW_WIN_HEIGTH - 50
        icon.layoutX = 0.0
        close.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            this.close()
        }
        close.hoverProperty().addListener{_,_,new->
            if(new){
                close.style = "-fx-background-color: red;-fx-text-fill: #ffff"
            }else{
                close.style = "-fx-background-color: transparent"
            }
        }
        min.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            this.isIconified = true
        }
        min.hoverProperty().addListener{_,_,new->
            if(new){
                min.style = "-fx-background-color: rgba(217, 217, 217,0.7);-fx-text-fill: #ffff"
            }else{
                min.style = "-fx-background-color: transparent"
            }
        }
        max.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            if (this.isMaximized){
                this.isMaximized = false
            }else{
                this.isMaximized = true
            }
        }
        max.hoverProperty().addListener{_,_,new->
            if(new){
                max.style = "-fx-background-color: rgba(217, 217, 217,0.7)"
            }else{
                max.style = "-fx-background-color: transparent"
            }
        }
        close.padding = Insets(0.0,0.0,0.0,10.0)
        min.padding = Insets(0.0,0.0,0.0,10.0)
        max.padding = Insets(0.0,0.0,0.0,0.0)
        icon.padding = Insets(5.0,0.0,0.0,5.0)
        horizontalBar.children.addAll(icon,close,min)
        horizontalBar.padding = Insets(0.0,0.0,0.0,0.0)
        horizontalBar.style = "-fx-background-color: #9254de;"
        box.children.add(horizontalBar)
    }
}

open class 悬浮窗口(){

}

open class 可以改变_自定义窗口(val node: Node) : Stage(){
    private var x1 = 0.00
    private var y1 = 0.00
    private var width1 = 0.00
    private var height1 = 0.00
    private var isMax = false
    private var isRight // 是否处于右边界调整窗口状态
            = false
    private var isBottomRight // 是否处于右下角调整窗口状态
            = false
    private var isBottom // 是否处于下边界调整窗口状态
            = false
    private val RESIZE_WIDTH = 5.00
    private val MIN_WIDTH = 400.00
    private val MIN_HEIGHT = 300.00
    private var xOffset = 0.0
    private var yOffset = 0.0 //自定义dialog移动横纵坐标
    private var ICON_SIZE = 25.0

    init {
        init(this)
    }

    fun init(primaryStage: Stage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT)
        val root = BorderPane()
        val gridPane = GridPane()
        gridPane.alignment = Pos.CENTER_LEFT
        gridPane.padding = Insets(2.0)
        val icon = Label().apply { graphic = 图标(获取当前目录路径(自定义窗口::class.java, "img/ic3.png"),ICON_SIZE,ICON_SIZE) }
        val min = Label("—").apply { prefWidth = ICON_SIZE;prefHeight = ICON_SIZE;textAlignment = TextAlignment.CENTER }
        val max = Label("口").apply { prefWidth = ICON_SIZE;prefHeight = ICON_SIZE;textAlignment = TextAlignment.CENTER }
        val close = Label("X").apply { prefWidth = ICON_SIZE;prefHeight = ICON_SIZE;textAlignment = TextAlignment.CENTER }
        close.padding = Insets(0.0,5.0,0.0,5.0)
        min.padding = Insets(0.0,5.0,0.0,5.0)
        max.padding = Insets(0.0,5.0,0.0,5.0)
        gridPane.add(icon, 0, 0)
        gridPane.add(min, 1, 0)
        gridPane.add(max, 2, 0)
        gridPane.add(close, 3, 0)
        GridPane.setHgrow(icon, Priority.ALWAYS)
        root.top = gridPane
        root.center = node
        root.style = "-fx-background-color: white ;-fx-border-color: rgb(128,128,64); -fx-border-width: 1;"
        gridPane.style = "-fx-background-color: #9254de;"
        min.onMouseClicked = EventHandler { primaryStage.isIconified = true }
        min.hoverProperty().addListener { _, _, new ->
            if (new) {
                min.style = "-fx-background-color: rgba(217, 217, 217,0.7);-fx-text-fill: #ffff"
            } else {
                min.style = "-fx-background-color: transparent"
            }
        }
        max.onMouseClicked = EventHandler {
            val rectangle2d = Screen.getPrimary().visualBounds
            isMax = !isMax
            if (isMax) {
                // 最大化
                primaryStage.x = rectangle2d.minX
                primaryStage.y = rectangle2d.minY
                primaryStage.width = rectangle2d.width
                primaryStage.height = rectangle2d.height
            } else {
                // 缩放回原来的大小
                primaryStage.x = x1
                primaryStage.y = y1
                primaryStage.width = width1
                primaryStage.height = height1
            }
        }
        max.hoverProperty().addListener{_,_,new->
            if(new){
                max.style = "-fx-background-color: rgba(217, 217, 217,0.7)"
            }else{
                max.style = "-fx-background-color: transparent"
            }
        }
        close.onMouseClicked = EventHandler { event -> primaryStage.close() }
        close.hoverProperty().addListener{_,_,new->
            if(new){
                close.style = "-fx-background-color: red;-fx-text-fill: #ffff"
            }else{
                close.style = "-fx-background-color: transparent"
            }
        }
        primaryStage.xProperty()
            .addListener { observable: ObservableValue<out Number?>?, oldValue: Number?, newValue: Number? ->
                if (newValue != null && !isMax) {
                    x1 = newValue.toDouble()
                }
            }
        primaryStage.yProperty()
            .addListener { observable: ObservableValue<out Number?>?, oldValue: Number?, newValue: Number? ->
                if (newValue != null && !isMax) {
                    y1 = newValue.toDouble()
                }
            }
        primaryStage.widthProperty()
            .addListener { observable: ObservableValue<out Number?>?, oldValue: Number?, newValue: Number? ->
                if (newValue != null && !isMax) {
                    width1 = newValue.toDouble()
                }
            }
        primaryStage.heightProperty()
            .addListener { observable: ObservableValue<out Number?>?, oldValue: Number?, newValue: Number? ->
                if (newValue != null && !isMax) {
                    height1 = newValue.toDouble()
                }
            }
        root.onMouseMoved = EventHandler { event: MouseEvent ->
            event.consume()
            val x = event.sceneX
            val y = event.sceneY
            val width = primaryStage.width
            val height = primaryStage.height
            var cursorType = Cursor.DEFAULT // 鼠标光标初始为默认类型，若未进入调整窗口状态，保持默认类型
            // 先将所有调整窗口状态重置
            isBottom = false
            isBottomRight = isBottom
            isRight = isBottomRight
            if (y >= height - RESIZE_WIDTH) {
                if (x <= RESIZE_WIDTH) { // 左下角调整窗口状态
                    //不处理
                } else if (x >= width - RESIZE_WIDTH) { // 右下角调整窗口状态
                    isBottomRight = true
                    cursorType = Cursor.SE_RESIZE
                } else { // 下边界调整窗口状态
                    isBottom = true
                    cursorType = Cursor.S_RESIZE
                }
            } else if (x >= width - RESIZE_WIDTH) { // 右边界调整窗口状态
                isRight = true
                cursorType = Cursor.E_RESIZE
            }
            // 最后改变鼠标光标
            root.cursor = cursorType
        }
        root.onMouseDragged = EventHandler { event: MouseEvent ->

            //根据鼠标的横纵坐标移动dialog位置
            event.consume()
            if (yOffset != 0.0) {
                primaryStage.x = event.screenX - xOffset
                if (event.screenY - yOffset < 0) {
                    primaryStage.y = 0.0
                } else {
                    primaryStage.y = event.screenY - yOffset
                }
            }
            val x = event.sceneX
            val y = event.sceneY
            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
            val nextX = primaryStage.x
            val nextY = primaryStage.y
            var nextWidth = primaryStage.width
            var nextHeight = primaryStage.height
            if (isRight || isBottomRight) { // 所有右边调整窗口状态
                nextWidth = x
            }
            if (isBottomRight || isBottom) { // 所有下边调整窗口状态
                nextHeight = y
            }
            if (nextWidth <= MIN_WIDTH) { // 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                nextWidth = MIN_WIDTH
            }
            if (nextHeight <= MIN_HEIGHT) { // 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                nextHeight = MIN_HEIGHT
            }
            // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
            primaryStage.x = nextX
            primaryStage.y = nextY
            primaryStage.width = nextWidth
            primaryStage.height = nextHeight
        }
        //鼠标点击获取横纵坐标
        root.onMousePressed = EventHandler { event: MouseEvent ->
            event.consume()
            xOffset = event.sceneX
            yOffset = if (event.sceneY > 46) {
                0.0
            } else {
                event.sceneY
            }
        }
        val scene = Scene(root, 400.0, 600.0)
        primaryStage.scene = scene
        primaryStage.title = "自定义窗口"
        primaryStage.show()
    }
}

class appTest:Application(){
    override fun start(primaryStage: Stage?) {
        val xiaoc = 自定义窗口(Label("不是一次失败"))
        val button = Button("点击显示弹窗")
        button.onAction = EventHandler {
            xiaoc.myIsAlwaysOnTop = false
            xiaoc.show()
        }
        val box = VBox()
        box.children.addAll(button)
        primaryStage!!.scene = Scene(box)
        primaryStage.show()
    }

}

class 评论树(val commentData:ArrayList<评论>? = null,val userName:String = "世界树用户:"):Application(){
    var nawItem:TreeItem<VBox>? = null
    override fun start(primaryStage: Stage?) {
        val root = VBox()
        root.children.add(Label("评论树"))
        val view = TreeView<VBox>()

        //显示区
        val treeItemRoot = TreeItem(评论箱子("评论箱子",""))
        nawItem = treeItemRoot
        view.root = treeItemRoot
        view.root.isExpanded = true
        view.isShowRoot = false
        root.children.add(view)
        if (commentData != null){
            评论递归(commentData,treeItemRoot)
        }


        //评论区
        val commentBox = HBox()
        val spanLabel = Label("${userName}评论：")
        val field = TextField()
        val commentBut = Button("发送")
        commentBox.children.addAll(spanLabel,field,commentBut)
        root.children.add(commentBox)

        commentBut.setOnMouseClicked {
            val text = field.text
            if (text.trim().isNotEmpty()){
                val treeItem = TreeItem(评论箱子(userName.plus(text.trim()),""))
                treeItem.isExpanded = true
                nawItem!!.children.add(treeItem)
            }
        }
        view.selectionModel.selectedItemProperty().addListener { _,_,new->
            nawItem = new
            val label = new.value.children.get(0) as Label
            val indexOf = label.text.indexOf(":")
            println(indexOf)
            if (indexOf != -1){
                spanLabel.text = "${userName}评论：@${label.text.substring(0,indexOf)}"
            }
        }

        primaryStage!!.width = 600.0
        primaryStage.height = 600.0
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }

    private fun 评论递归(commentData:ArrayList<评论>,parent:TreeItem<VBox>){
        for (comment in commentData){
            val treeItem = TreeItem(评论箱子(comment.userName.plus(comment.comment),comment.image))
            parent.children.add(treeItem)
            if (comment.commentChildren.size > 0){
                评论递归((comment.commentChildren as ArrayList<评论>),parent.children.last())
            }
        }
    }

    private fun 评论箱子(评论:String,图片地址:String):VBox{
        val box = VBox()
        box.children.add(Label(评论))
        if (图片地址.isNotEmpty()){
            box.children.add(图标(图片地址))
        }
        return box
    }


}

fun main() {
    Application.launch(评论树::class.java)
}


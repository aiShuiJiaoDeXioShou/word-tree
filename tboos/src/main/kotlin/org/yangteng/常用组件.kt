package org.yangteng

import javafx.application.Application
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.collections.ListChangeListener
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.ClipboardContent
import javafx.scene.input.MouseEvent
import javafx.scene.input.TransferMode
import javafx.scene.layout.VBox
import javafx.scene.paint.Paint
import javafx.stage.Stage
import javafx.stage.StageStyle

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
        selfDialogPane.buttonTypes.addAll(*buttonType)
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

open class 列表布局<T:Node>():ListView<T>(){
    init {
        this.stylesheets.add(列表布局::class.java.getResource("列表布局.css")?.toExternalForm())
    }

    /*fun 列表拖拽(){
        this.setCellFactory { param: ListView<T>? ->
            var position = 0
            val cell = MyListCell<T>()
            cell.hoverProperty().addListener { _, _, new ->
                if (new){
                    position = param!!.items.indexOf(cell.itemG)
                    println(position)
                }
            }
            cell
        }
    }

    class MyListCell<T:Node>():ListCell<T>(){
        var itemG: T? = null
        override fun updateItem(item: T, empty: Boolean) {
            super.updateItem(item, empty)
            if (!empty){
                itemG = item
                this.graphic = itemG
            }
        }
    }*/
}

open class 自定义窗口(node:Node): Stage(){
    private val SHOW_WIN_WIDTH = 300.0
    private val SHOW_WIN_HEIGTH = 500.0
    private var xOffset = 0.0
    private  var yOffset = 0.0 //自定义dialog移动横纵坐标
    init {
        this.isAlwaysOnTop = true;
        this.maxWidth = SHOW_WIN_WIDTH
        this.maxHeight = SHOW_WIN_HEIGTH
        this.minHeight = SHOW_WIN_WIDTH
        this.minWidth = SHOW_WIN_HEIGTH
        //隐藏windows平台原有的样式
        this.initStyle(StageStyle.TRANSPARENT)
        val box = VBox()
        box.children.addAll(node)
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
}

open class 悬浮窗口(){

}

class appTest:Application(){
    override fun start(primaryStage: Stage?) {
        val xiaoc = 自定义窗口(Label("不是一次失败"))
        val button = Button("点击显示弹窗")
        button.onAction = EventHandler {
            xiaoc.show()
        }
        val box = VBox()
        box.children.add(button)
        primaryStage!!.scene = Scene(box)
        primaryStage.show()
    }

}

fun main() {
    Application.launch(appTest::class.java)
}


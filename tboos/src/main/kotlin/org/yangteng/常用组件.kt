package org.yangteng

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ListChangeListener
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.input.ClipboardContent
import javafx.scene.input.TransferMode
import javafx.scene.layout.VBox
import javafx.scene.paint.Paint

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
}

open class 选项卡 : Tab() {

    init {

    }
}

open class 标签


package org.yangteng

import javafx.beans.property.SimpleObjectProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.paint.Paint

open class 按钮():Button(){
    private val 按钮_Css = 按钮::class.java.getResource("按钮_样式表.css").toExternalForm()
    private val ripplerFill = SimpleObjectProperty<Paint>()

    constructor(按钮名:String = "按钮",点击次数:Int = 1,点击事件:()->Unit={},按钮类型: 按钮_类型 = 按钮_类型.无) : this() {
        this.apply {
            text = 按钮名
            stylesheets.add(按钮_Css)
            initStyle(按钮类型)
        }
        this.onMouseClicked = EventHandler {
            if (it.clickCount == 点击次数){
                点击事件()
            }
        }
    }

    constructor(按钮名:String,按钮类型:按钮_类型):this(){
        this.apply {
            text = 按钮名
            stylesheets.add(按钮_Css)
            initStyle(按钮类型)
        }
    }

    constructor(graphic:Node,按钮类型:按钮_类型,点击事件:()->Unit={}):this(){
        this.apply {
            this.graphic = graphic
            stylesheets.add(按钮_Css)
            initStyle(按钮类型)
        }
        this.onMouseClicked = EventHandler {
            点击事件()
        }
    }

    fun initStyle(按钮类型: 按钮_类型){
        when(按钮类型){
            按钮_类型.无->{ this.styleClass.add("white")}
            按钮_类型.绿色->this.styleClass.add("green")
            按钮_类型.红色->this.styleClass.add("red")
            按钮_类型.蓝色->this.styleClass.add("blue")
            按钮_类型.黑暗->this.styleClass.add("none")
            按钮_类型.警告->this.styleClass.add("yellow")
        }
    }

    fun java代码书写css样式(){
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
        this.hoverProperty().addListener{_,_,new->
            if (new){
                this.style = """
                -fx-background-color: derive(#595959,40%);
                """.trimIndent()
            }else{
                this.style = """
                -fx-background-color: #595959;
                """.trimIndent()
            }
        }
    }

}

open class 对话框(node: Node, vararg buttonType: ButtonType) : Dialog<ButtonType>(){
    private val selfDialogPane = MyDialogPane(this)

    init {
        selfDialogPane.content = node
        this.dialogPane = selfDialogPane
        selfDialogPane.buttonTypes.addAll(*buttonType)
    }

    class MyDialogPane(val selfDialog:对话框):DialogPane(){
        override fun createButton(buttonType: ButtonType): Node {
            val buttonData = buttonType.buttonData
            val button = when(buttonData){
                ButtonBar.ButtonData.YES->按钮(buttonType.text, 按钮_类型.蓝色)
                ButtonBar.ButtonData.NO->按钮(buttonType.text, 按钮_类型.无)
                ButtonBar.ButtonData.APPLY->按钮(buttonType.text, 按钮_类型.绿色)
                ButtonBar.ButtonData.CANCEL_CLOSE->按钮(buttonType.text, 按钮_类型.红色)
                else-> 按钮(buttonType.text, 按钮_类型.红色)
            }
            button.addEventHandler(ActionEvent.ACTION) { ae: ActionEvent ->
                selfDialog.close()
            }
            ButtonBar.setButtonData(button, buttonData)

            return button
        }
    }
}

open class 选项卡_布局():TabPane(){
    init {
        this.stylesheets.add(选项卡_布局::class.java.getResource("选项卡.css").toExternalForm())
    }
}

open class 选项卡():Tab(){

}

class 标签 {

}

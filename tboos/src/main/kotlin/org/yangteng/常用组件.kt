package org.yangteng

import javafx.event.EventHandler
import javafx.scene.control.Button

class 按钮():Button(){
    private val 按钮_Css = 按钮::class.java.getResource("按钮_样式表.css").toExternalForm()

    constructor(按钮名:String = "按钮",点击次数:Int = 1,点击事件:()->Unit = {},按钮类型: 按钮_类型 = 按钮_类型.无) : this() {
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
    init {

    }

    fun initStyle(按钮类型: 按钮_类型){
        when(按钮类型){
            按钮_类型.无->{ this.styleClass.add("none")}
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

class 对话框 {

}

class 标签 {

}

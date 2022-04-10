package com.wordtree.wt_kt_note_book.view

import javafx.scene.Node
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import org.yangteng.对话框
import java.util.*

class 设置面板(node: Node): 对话框(node,
    ButtonType("不保存", ButtonBar.ButtonData.APPLY),
    ButtonType("保存", ButtonBar.ButtonData.YES),
    ButtonType("取消", ButtonBar.ButtonData.NO)
) {
    constructor():this(node = Label("啥都没有，别看着我啊")){

    }
    private val wait: Optional<ButtonType> = this.showAndWait()
    private val button: ButtonBar.ButtonData = wait.get().buttonData
    init {
        下方按钮控制操作()
    }

    private fun 下方按钮控制操作(){
        if(button.equals(ButtonBar.ButtonData.YES)){
            println("点击了yes")
        }else if (button == ButtonBar.ButtonData.NO){
            println("点击了no")
        }else if (button == ButtonBar.ButtonData.APPLY){
            println("点击了apply")
        }
    }
}

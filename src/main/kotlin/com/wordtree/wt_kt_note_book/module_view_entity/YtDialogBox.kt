package com.wordtree.wt_kt_note_book.module_view_entity

import javafx.scene.Node
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import java.util.*

class YtDialogBox(content: Node): BaseSetting(
    AlertType.NONE, content,
    ButtonType("应用", ButtonBar.ButtonData.APPLY),
    ButtonType("保存", ButtonBar.ButtonData.YES),
    ButtonType("取消", ButtonBar.ButtonData.NO)
) {
    private val wait: Optional<ButtonType> = this.showAndWait()
    private val button: ButtonBar.ButtonData = wait.get().buttonData
    init {
        下方按钮控制操作()
    }

    private fun 下方按钮控制操作(){
        if(button.equals(ButtonBar.ButtonData.YES)){
            this.close()
        }else if (button == ButtonBar.ButtonData.NO){
            this.close()
        }else if (button == ButtonBar.ButtonData.APPLY){
            this.close()
        }
    }
}

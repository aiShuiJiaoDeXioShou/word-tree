package com.wordtree.wt_kt_note_book.view.user

import javafx.scene.Node
import javafx.scene.control.ButtonBar.ButtonData.NO
import javafx.scene.control.ButtonBar.ButtonData.YES
import javafx.scene.control.ButtonType
import org.yangteng.对话框

class 用户面板Controller(){

}

class 用户面板View(node: Node): 对话框(node, ButtonType("确定", YES), ButtonType("取消", NO)){
    init {
        this.show()
    }
}


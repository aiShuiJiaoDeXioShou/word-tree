package com.wordtree.wt_kt_note_book.module_view_entity

import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType

class YtAlert: Alert(AlertType.NONE,"不会吧这么苍白",
    ButtonType("确定", ButtonBar.ButtonData.YES),
    ButtonType("取消",ButtonBar.ButtonData.NO),
    ButtonType("应用",ButtonBar.ButtonData.APPLY))
{

}

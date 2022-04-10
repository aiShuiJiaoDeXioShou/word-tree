package com.wordtree.wt_kt_module

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Control
import javafx.scene.control.Label
import javafx.scene.control.Tooltip
import org.controlsfx.control.PopOver

class CommonComponents {
    private val popOver = PopOver()
    fun simplePromptBox(str:String,node: Node){
        popOver.contentNode = Label(str)
        node.onMouseEntered = EventHandler {
            popOver.show(node)
        }

        node.onMouseExited = EventHandler {
            popOver.hide()
        }
    }
    fun simpleToolTip(str: String,node: Control){
        val tooltip = Tooltip(str)
        tooltip.contentDisplay = ContentDisplay.TOP
//        tooltip.graphic 使用这个可以自定义组件哦 tooltip.graphic = VBox()
        node.tooltip = tooltip
    }
}

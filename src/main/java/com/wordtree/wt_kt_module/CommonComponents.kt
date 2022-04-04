package com.wordtree.wt_kt_module

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.*
import javafx.stage.Window
import org.controlsfx.control.PopOver

class CommonComponents {
    fun simplePromptBox(str:String,node: Node){
        val popOver = PopOver(Label(str))
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
       node.tooltip = tooltip
    }
}

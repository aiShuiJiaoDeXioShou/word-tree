package com.wordtree.wt_kt_module

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.Label
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
}
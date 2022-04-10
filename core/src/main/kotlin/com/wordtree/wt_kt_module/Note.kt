package com.wordtree.wt_kt_module

import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.TextArea
import javafx.scene.input.KeyCode
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class Note(){
    private val area = TextArea()
    private val pane = BorderPane()
    private val box = VBox()
    private var STEIP = 0
    fun note():Pane{
        area.apply {
            this.prefRowCount = 100
            onKeyPressed = EventHandler {
                if(it.code== KeyCode.ENTER){
                    box.children.add(Text((this.caretPosition).toString()))
                    println("anchor:"+this.anchor)
                    println("caretPosition:"+this.caretPosition)
                }
                if(it.code == KeyCode.BACK_SPACE&&STEIP!=0){

                }
            }
        }
        pane.center = area
        box.apply {
            prefWidth = 20.0
            alignment = Pos.BASELINE_CENTER
        }
        pane.apply {
            left = box
        }
        return pane
    }
}

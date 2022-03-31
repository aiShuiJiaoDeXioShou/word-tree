package com.wordtree.wt_kt_note_book

import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.layout.VBox

fun 左侧项目栏() {
    val but = Button("项".plus("\n目")).apply {
        prefWidth = 30.0
        onMouseClicked = EventHandler {
            if (!fileViewOpen) {
                centerPane.setDividerPosition(0, 0.2)
                fileViewOpen = true
            } else {
                centerPane.setDividerPosition(0, 0.0)
                fileViewOpen = false
            }
        }
        prefHeight = 60.0
        styleClass.add("legend")
    }
    root.left = VBox().apply {
        prefWidth = 30.0
        children.addAll(but)
    }
}

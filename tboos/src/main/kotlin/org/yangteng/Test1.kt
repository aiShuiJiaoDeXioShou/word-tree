package org.yangteng

import com.jfoenix.controls.JFXButton
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage

class app1():Application(){

    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox)
        vBox.children.addAll(按钮("按钮"), JFXButton("呵呵呵"))
        primaryStage.scene = scene
        primaryStage.show()
    }


}

fun main() {
    Application.launch(app1::class.java)
}

package org.yangteng

import com.jfoenix.controls.JFXAlert
import com.jfoenix.controls.JFXButton
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage

//按钮单元测试
class app1():Application(){

    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox)
        vBox.children.addAll(按钮("按钮"), JFXButton("呵呵呵"))
        primaryStage.scene = scene
        primaryStage.show()
    }


}

//对话框单元测试
class app2():Application(){

    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox)
        primaryStage.scene = scene

        val jfxAlert = Dialog<Any>()
        jfxAlert.contentText = "你点击我了，凡人"
        JFXAlert<Any>()
        vBox.children.addAll(按钮("按钮",1,{
            println("点击我了")
            jfxAlert.show()
        }), JFXButton("呵呵呵"))
        primaryStage.show()
    }
}

//对话框单元测试
class app3():Application(){

    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox)
        primaryStage.scene = scene
        val dia = 对话框(Label("怕个吊，上去就是一脚"),
            ButtonType("确定",ButtonBar.ButtonData.YES),
            ButtonType("取消",ButtonBar.ButtonData.NO),
        )
        vBox.children.addAll(按钮("按钮",1,{
            dia.show()
        }), JFXButton("呵呵呵"))
        primaryStage.show()
    }
}

//选项卡单元测试
class app4:Application(){
    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox,400.0,400.0)
        val tabPane = 选项卡_布局()
        tabPane.tabs.addAll(
            Tab("年后"),
            Tab("年后"),
            Tab("年后"),
            Tab("年后"),
            Tab("年后"),
            Tab("年后"),
        )
        vBox.children.addAll(tabPane)
        primaryStage.scene = scene
        primaryStage.show()
    }
}


fun main() {
    Application.launch(app4::class.java)
}

package com.wordtree.wt_kt_module

import com.wordtree.wt_config.Index_Config
import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.text.Font
import javafx.stage.Stage
import javafx.stage.StageStyle

class Login:Application(){
    private val root = VBox()
    private val label = Label("用户:\t")
    private val label2 = Label("密码:\t")
    private val 点击登入 = Button("点击登入")
    private val 注册用户 = Button("注册用户")
    private val name = TextField()
    private val yonhu = HBox()
    private val mima = HBox()
    private val password = PasswordField()
    private var stage: Stage? = null
    private val logo = Label("Shoppe")
    private val logofont = Font(40.0)
    private val 操作区域 = HBox()

    private fun 布局(stage: Stage){
        //这个是界面布局
        label.styleClass.add("text1")
        label2.styleClass.add("text1")

        logo.padding = Insets(20.0)
        logo.font = logofont
        logo.style = "-fx-font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;-fx-text-fill:#cd5c5c"

        name.prefWidth = 300.0
        name.prefHeight = 40.0

        yonhu.children.addAll(label, name)
        yonhu.alignment = Pos.CENTER
        password.prefWidth = 300.0
        password.prefHeight = 40.0
        mima.alignment = Pos.CENTER
        mima.children.addAll(label2, password)

        点击登入.styleClass.add("button")
        注册用户.styleClass.add("button")
        操作区域.children.addAll(点击登入, 注册用户)
        操作区域.alignment = Pos.CENTER
        操作区域.spacing = 50.0

        root.children.addAll(logo, yonhu, mima, 操作区域)
        root.alignment = Pos.CENTER
        root.spacing = 10.0
        root.background = Background(BackgroundImage(Image("static/img/hehe.jpg", stage.width, stage.height, true, true), null, null, BackgroundPosition.CENTER, null))
    }
    private fun 操作(){
        //这个是逻辑操作
        点击登入.onMouseClicked = EventHandler { e: MouseEvent? ->
//            登入判定()
            TotalPage().show()
            stage?.close()
        }
        password.onKeyPressed = EventHandler {
            if(it.code== KeyCode.ENTER){
                登入判定()
            }
        }
    }
    private fun 登入判定(){
        val nameText = name.text
        val passwordText = password.text
        println(nameText + passwordText)
        if (nameText == "admin" && passwordText == "123456") {
            TotalPage().show()
            stage?.close()
        }
    }

    override fun start(primaryStage: Stage) {
        this.stage = primaryStage
        val scene = Scene(root)
        scene.stylesheets.addAll("static/css/style.css")
        primaryStage.isResizable = false
        primaryStage.width = 1000.0
        primaryStage.height = 600.0
        primaryStage.title = Index_Config.APP_NAME
        primaryStage.icons.add(Image("static/img/icon.png"))
        布局(primaryStage)
        操作()
        primaryStage.scene = scene
        primaryStage.initStyle(StageStyle.DECORATED)
        primaryStage.show()
    }
}

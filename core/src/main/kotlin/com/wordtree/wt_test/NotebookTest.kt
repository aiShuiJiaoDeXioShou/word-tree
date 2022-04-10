package com.wordtree.wt_kt_module

import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Hyperlink
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import javafx.scene.text.Font
import javafx.stage.Stage


/**
 * 写一个笔记本的代码哦
 */
class Notebook(app: Application) : Stage() {
    init {
        val box = VBox()
        //这个是javafx里面的文字链接
        val like = Hyperlink()
        like.text = "www.baidu.com"
        like.setOnAction {
            app.hostServices.showDocument(like.text)
        }
        box.children.setAll(like)
        this.scene = Scene(box)
        this.width = 800.0
        this.height = 700.0
        this.show()
    }
}

class StageTest : Application() {
    override fun start(stage: Stage) {
        val box = VBox()
        val button1 = Button("主页")
        val button2 = Button("奥特曼")
        val button3 = Button("迪迦")
        val button4 = Button("kai")
        box.spacing = 10.0
        box.padding = Insets(0.0, 100.0, 0.0, 0.0)
        box.alignment = Pos.BOTTOM_RIGHT
        //这下面是box的操作
        box.children.setAll(button1, button2, button3, button4)
//        box.background = Background(BackgroundFill(Color.valueOf("#ff7875"),null,null)) 设置背景颜色
        //设置背景图片
        stage.width = 800.0
        stage.height = 700.0
        box.background = Background(
            BackgroundImage(
                Image(
                    "img/hutao.jpg",
                    stage.width,
                    stage.height,
                    true,
                    true
                ), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null
            )
        )
        stage.scene = Scene(box)
        stage.show()
    }
}

//这是一个弹出窗
class Tips(msg:String) : Stage(){
    private val box = VBox()
    private val msgText = Label(msg)
    private val closeButton = Button("关闭")
    init {
        msgText.font = Font.font(24.0)
        msgText.textFill = Paint.valueOf("red")
        box.children.addAll(msgText,closeButton)
        box.alignment = Pos.CENTER
        box.spacing = 50.0
        this.scene = Scene(box)
        closeButton.setOnMouseClicked {
            this.close()
        }
        this.width = 400.0
        this.height = 400.0
        this.isResizable = false//设置大小不可变
    }
}
//javafx布局类的学习
class 绝对布局类 : Application() {
    override fun start(stage: Stage) {
        val button = Button("这个是用于测试的按钮")
        val pane = AnchorPane()
        AnchorPane.setBottomAnchor(button, 10.0)
        AnchorPane.setLeftAnchor(button, 10.0)
        AnchorPane.setRightAnchor(button, 10.0)
        AnchorPane.setTopAnchor(button, 10.0)
        pane.children.add(button)
        stage.scene = Scene(pane)
        stage.width = 800.0
        stage.height = 700.0
        stage.show()
    }
}

fun bgImage(label: Pane, src: String, stage: Stage) {
    label.background =
        Background(
            BackgroundImage(
                Image(src, stage.width * 0.7, stage.height, true, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                null,
                null
            )
        )
}


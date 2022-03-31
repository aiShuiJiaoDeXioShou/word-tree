package com.wordtree.wt_kt_module

import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.text.Text
import javafx.stage.Stage

//计算器
class AppComputer() : Stage() {
    private var x = 0
    private var y = 0
    private var moshi = true
    private val text = Text()
    private val jisuan = Text()
    private val text2 = Text()
    private var result = Text()
    private var sum = 0.0
    private val gridPane = GridPane()
    public val box = VBox()
    init {

        text.text = x.toString()
        text2.text = ""
        gridPane.vgap = 5.0
        gridPane.hgap = 5.0

        //设置计算机的按钮
        val button1 = Button("1")
        val button2 = Button("2")
        val button3 = Button("3")
        val button4 = Button("4")
        val button5 = Button("5")
        val button6 = Button("6")
        val button7 = Button("7")
        val button8 = Button("8")
        val button9 = Button("9")
        val button0 = Button("0")

        val butJia = Button("+")
        butJia.setOnMouseClicked {
            moshi = false
            jisuan.text = "+"
        }
        val butJian = Button("-")
        butJian.setOnMouseClicked {
            moshi = false
            jisuan.text = "-"
        }
        val butChen = Button("*")
        butChen.setOnMouseClicked {
            moshi = false
            jisuan.text = "*"
        }
        val butChu = Button("/")
        butChu.setOnMouseClicked {
            moshi = false
            jisuan.text = "/"
        }
        val butGui = Button("归零")
        butGui.setOnMouseClicked {
            x = 0
            y = 0
            moshi = true
            jisuan.text = ""
            text2.text = ""
            text.text = ""
            sum = 0.0
            result.text = ""
        }
        val butX = Button("<-")
        butX.setOnMouseClicked {
            if (moshi) {
                if (text.text.length > 0) {
                    text.text = jianText(text.text).trim()
                    x = if (text.text.isNotEmpty()) text.text.toInt() else 0
                } else {
                    moshi = false
                }
            } else {
                if (text2.text.length > 0) {
                    text2.text = jianText(text2.text).trim()
                    y = if (text2.text.isNotEmpty()) text2.text.toInt() else 0
                } else {
                    jisuan.text = jianText(jisuan.text)
                    moshi = true
                }
            }
            jisuanResult()
        }
        val butDenyu = Button("=")
        butDenyu.setOnMouseClicked {
            jisuanResult()
        }
        gridPane.addColumn(0, button1, button2, button3, button4, button5)
        gridPane.addColumn(1, button6, button7, button8, button9, button0)
        for (child in gridPane.children) {
            child.setOnMouseClicked {
                inputText(child as Button, text2)
            }
        }
        gridPane.addColumn(2, butJia, butJian, butChen, butChu, butGui)
        gridPane.addColumn(3, butX, butDenyu)
        //设置背景图片
        try {
            box.background =
                Background(BackgroundImage(Image(javaClass.getClassLoader().getResource("static/img/img.png").toString()), null, null, null, null))
            println(javaClass.getClassLoader().getResource("static/img/img.png").toString())
        } catch (e: IllegalArgumentException) {
            System.out.println("没有找到资源")
        }
        val hBox = HBox()
        hBox.children.addAll(text, jisuan, text2, result)
        hBox.alignment = Pos.CENTER
        box.children.addAll(hBox, gridPane)
        //设置组件居中显示
        box.alignment = Pos.CENTER
        box.spacing = 10.0
        gridPane.alignment = Pos.CENTER
        val scene = Scene(box)
        this.scene = scene
        width = 400.0
        height = 600.0
    }

    private fun inputText(button: Button, text2: Text) {
        if (moshi) {
            x = (x.toString() + button.text).toInt()
            text.text = x.toString()
        } else {
            y = (y.toString() + button.text).toInt()
            text2.text = y.toString()
        }
    }

    private fun jianText(s: String): String {
        var newS = ""
        val split = s.split("")
        var mou: Int
        if (split.size - 1 > 0) {
            mou = split.size - 2
            val subList = split.subList(0, mou)
            for (ss in subList) {
                newS += ss
            }
            return newS
        } else {
            return newS
        }
    }

    private fun jisuanResult() {
        when (jisuan.text) {
            "+" -> sum = (x + y).toDouble();
            "*" -> sum = (x * y).toDouble();
            "/" -> sum = (x / y).toDouble();
            "-" -> sum = (x - y).toDouble();
            else -> sum = (x + y).toDouble()
        }
        result.text = "= ${sum}"
    }
}

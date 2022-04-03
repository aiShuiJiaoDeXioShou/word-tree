package com.wordtree.wt_kt_module

import com.wordtree.wt_toolkit.cmd_expand.JavaExeCmd
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage
import java.io.File

//python脚本执行器
class PythonRun() : Stage() {
    val root = VBox().apply { prefWidth = 830.0;prefHeight = 750.0 }
    private val label1 = Label("请输入python解释器所在的文件夹")
    private val label2 = Label("请输入python脚本所在的文件夹")
    private val pythonExE = TextField()
    private val pythonRunFile = TextField()
    private val text = Text()
    private val determine = Button("确定")
    private val cancel = Button("取消")
    private val buttonBar = HBox()
    private val pythonTreeItem = TreeItem<String>("python可执行文件")
    private val pythonFile = TreeView(pythonTreeItem)

    init {
        layout()
        inits("static/img/486-2.jpg")
        cssPart()
        controller()
    }

    fun layout() {
        root.alignment = Pos.CENTER
        buttonBar.children.addAll(determine, cancel)
        root.children.addAll(label1, pythonExE, label2, pythonRunFile, text, buttonBar, pythonFile)
    }

    fun inits(resource:String) {
        bgImage(root,resource, this)
        this.scene = Scene(root)
    }

    fun controller() {
        var url = ""
        var i = 1
        //获取python可执行文件
        determine.setOnMouseClicked {
            url = pythonRunFile.text.trim()
            val file = File(url)
            val listFiles = file.listFiles()
            listFiles.forEach { file ->
                pythonTreeItem.children.add(TreeItem(file.name))
            }
        }
        //设置运行python文件的方法
        pythonFile.selectionModel.selectedItemProperty().addListener(ChangeListener<TreeItem<String>>(fun(
            _: ObservableValue<out TreeItem<String>>?,
            _: TreeItem<String>?,
            newValue: TreeItem<String>?
        ) {
            if(newValue!=pythonFile.root){
                val value = newValue?.value
                println(url.plus("/").plus(value))
                JavaExeCmd.runFile("python ${url.plus("/").plus(value)}", "img/static/wenjian${i++}")
            }
        }))
    }

    fun cssPart() {
        //css部分
        label1.style = """
            -fx-text-fill:white
        """.trimIndent()
        label2.style = """
            -fx-text-fill:white
        """.trimIndent()
        //设置透明度的组件？
        //设置组件通用的一些设计
        for (children in root.children) {
            children.opacity = 0.8
        }
    }
}

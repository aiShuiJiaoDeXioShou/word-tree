package com.wordtree.wt_kt_module

import com.wordtree.wt_config.Index_Config
import com.wordtree.wt_kt_note_book.Coder
import com.wordtree.wt_module.writing.Win
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.stage.Stage
import java.io.File
import kotlin.system.exitProcess

//这个是图片查看器
class TotalPage : Stage() {
    //定义头部公共部分
    private val rootPane = BorderPane()
    private val treeRoot = TreeItem<String>()
    private val label = VBox().apply { prefHeight = 600.0;prefWidth = 1000.0 }
    private val treeImage = TreeItem("图片")
    private val file = File(this.javaClass.getClassLoader().getResource("static/img").toURI())
    private val fileList = file.listFiles()
    private val note = Note()
    private val coder = Coder()

    init {
        layout()
        this.title = Index_Config.APP_NAME
        this.scene = Scene(rootPane)
        this.scene.stylesheets.add("static/css/disanfan.css")
        this.icons.add(Image(Index_Config.APP_ICON))
        //设置当这个窗口关闭之后，关闭整个程序
        this.onCloseRequest = EventHandler {
            Platform.exit()
            exitProcess(0)
        }
    }

    private fun layout() {
        val computer = AppComputer()
        val pythonRun = PythonRun()
        treeRoot.value = "工作区"//这个用于设定和更改所在节点的名称
        treeRoot.isExpanded = true //设置默认展开

        //这个是左界面的编写
        navigation()
        val treeView = TreeView(treeRoot)
        treeView.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            val stage = rootPane.scene.window as Stage
            when (newValue.value) {
                "图片" -> {
                    rootPane.center = label;
                    stage.width = label.width
                    stage.height = label.width
                }
                "计算机" -> {
                    rootPane.center = computer.box
                    stage.width = computer.box.width
                    stage.height = computer.box.height
                }
                "pythonRun" -> {
                    rootPane.center = pythonRun.root
                    stage.width = pythonRun.root.prefWidth
                    stage.height = pythonRun.root.prefHeight
                }
                "管理系统" -> Win.run()
                "writeUi" -> {
                    Tips("注意这个是控制台应用").show()
                    //WriterUI().run()
                }
                "Note" -> {
                    rootPane.center = note.note()
                    stage.width = note.note().prefWidth
                    stage.height = note.note().prefHeight
                }
                "第三方编辑器" -> {
                    Thread{
                        Platform.runLater {
                            coder.show()
                        }
                    }.start()
                }
            }
            for (file in fileList) {
                if (file.name == newValue?.value) {
                    bgImage(label, file.path, this)
                }
            }
        }

        //最下面是一些基本设置
        treeView.isShowRoot = false //这里表示是否显示根节点,我这里设置不显示根节点
        treeView.scrollTo(13)//如果面积不够,则滚动到多少

        //这个是右界面的编写
        rootPane.center = label
        rootPane.left = treeView
    }

    private fun navigation() {
        for (file in fileList) {
            val item = TreeItem(file.name)
            treeImage.children.add(item)
        }
        val itemJ = TreeItem("计算机")
        val itemT = TreeItem("坦克")
        val itemY = TreeItem("以往的项目")
        val itemN = TreeItem("Note")
        val 第三方编辑器 = TreeItem("第三方编辑器")
        itemY.children.addAll(TreeItem("管理系统"), TreeItem("writeUi"))
        val pythonRun = TreeItem("pythonRun")
        treeRoot.children.addAll(treeImage, itemJ, itemT, pythonRun, itemY, itemN, 第三方编辑器)
    }
}

package com.wordtree.wt_kt_note_book

import com.wordtree.wt_kt_note_book.module_view_entity.MyTab
import com.wordtree.wt_kt_note_book.module_view_entity.YtTreeItem
import com.wordtree.wt_toolkit.flie_expand.FileToolYt
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.yangteng.选项卡_布局
import java.io.File


var root = BorderPane()
val topBar = MenuBar()
val userBox = VBox()
val centerPane = SplitPane()
val centerPaneRoot = SplitPane().apply { orientation = javafx.geometry.Orientation.VERTICAL;setDividerPosition(0,1.0) }
val bar = ProgressBar(0.0)

//左侧文件树
var file: File? = FileToolYt.createFile(null) //这个是整个编辑器的母文件夹
var fileItemRoot = YtTreeItem(file!!)
val fileTreeView = TreeView(fileItemRoot)
var fileViewOpen = true

var txtTreeView = TreeView<Label>()

//编辑区上面的tap标签页
val tabPane = 选项卡_布局()

val fileTab = tabPane.拖动事件()//放置tab和它文本编辑器的盒子

//用户的头像
val ImageSize = R.getPropertie("System.User.Image.Size").toDouble()
val gradeBox = HBox()
val grade = Label()


//用户的名称签名
val textUserBox = VBox()
val nameBox = HBox()
val editBut = Label()
val pi2 = ProgressBar(0.6)//这个对象是进度条
val name = Label()
val motto = TextField()

//显示用户进度以及用户的插件箱
val manuscriptBox = VBox()
val userSpeedProgressBox = VBox()

//编辑器当前文本对象
var nowFile: File? = null
var globalTab:MyTab? = null


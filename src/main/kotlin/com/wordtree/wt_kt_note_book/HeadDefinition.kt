package com.wordtree.wt_kt_note_book

import com.wordtree.wt_kt_note_book.module_view_entity.YtTreeItem
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.fxmisc.richtext.CodeArea
import java.io.File

val codeArea = CodeArea()
val root = BorderPane()
val topBar = MenuBar()
val userBox = VBox()
val USER_WIDTH = 340.0
val centerPane = SplitPane()

//左侧文件树
var file: File? = File("D:\\1.java\\worspace-java\\Vue3\\study") //这个是整个编辑器的母文件夹
var fileItemRoot = YtTreeItem(file!!)
val fileTreeView = TreeView(fileItemRoot)
var fileViewOpen = true
var cursorId = ArrayList<String>() //记录所有tab标签的id值

//编辑区上面的tap标签页
val tabPane = TabPane()
val fileTab = VBox()//放置tab和它文本编辑器的盒子

//用户的头像
val ImageSize = R.textName("System.User.Image.Size").toDouble()
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
var cursorPosition: HashMap<String, Any> = HashMap() //每一个tab文本都有对应的自己的光标值
var nowFile: File? = null
var indexFileName = SimpleIntegerProperty(0)
var fileBaocun = 0

val bar = ProgressBar(0.0)

fun cssInit(){
    codeArea.styleClass.addAll("coder","coderYt")
}

package com.wordtree.wt_kt_note_book

import com.wordtree.wt_config.Index_Config
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
var file: File? = File("D:\\3.Node.js\\项目以及学习目录\\JavaScript学习") //这个是整个编辑器的母文件夹
var fileItemRoot = TreeItem<Label>(Label(file!!.name))
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
val tabSeed = TabPane()

//用户的名称签名
val textUserBox = VBox()
val nameBox = HBox()
val editBut = Label()
val pi2 = ProgressBar(0.6)//这个对象是进度条
val name = Label()
val motto = TextField(Index_Config.App_User_Motto)

//显示用户进度以及用户的插件箱
val manuscriptBox = VBox()
val userSpeedProgressBox = VBox()

//编辑器当前文本对象
var row = 0
var col = 0
var cursorPosition: HashMap<String, Any> = HashMap() //每一个tab文本都有对应的自己的光标值
var nowFile: File? = null
var indexFileName = SimpleIntegerProperty(0)
var fileBaocun = 0

fun cssInit(){
    codeArea.styleClass.addAll("coder","coderYt")
}

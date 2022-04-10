package com.wordtree.wt_kt_note_book.view

import com.wordtree.wt_config.Index_Config
import com.wordtree.wt_kt_note_book.addFileThrift
import com.wordtree.wt_kt_note_book.fileTreeView
import com.wordtree.wt_kt_note_book.module_view_entity.YtIcon
import com.wordtree.wt_kt_note_book.module_view_entity.YtTreeItem
import com.wordtree.wt_kt_note_book.topBar
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.control.ButtonBar.ButtonData.NO
import javafx.scene.control.ButtonBar.ButtonData.YES
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser
import org.yangteng.对话框
import java.io.File

class 新建项目弹窗(node:Node): 对话框(node, ButtonType("确定", YES), ButtonType("取消", NO)){
    private val borderPane = node as BorderPane
    private val center = borderPane.center
    init {
        this.isResizable = true
        val showAndWait = this.showAndWait()
        when(showAndWait.get().buttonData){
            YES -> {
                when(center.id){
                    "new_file_set_root"-> new_file_set_root(center)
                }
                this.close()
            }
            NO -> this.close()

        }
    }

    private fun new_file_set_root(center:Node){
        val anchorPane = center as Pane
        val fileSetPath = anchorPane.children.get(2) as TextField
        if (fileSetPath.textProperty().get() != ""){
            val file = File(fileSetPath.textProperty().get())
            if (file.mkdirs()) {
                val treeItem = YtTreeItem(file)
                addFileThrift(file.listFiles(), treeItem)
                fileTreeView.root = treeItem
                R.addProperty("fileChoose",file.toString());
            }
        }
    }


}

class 新建项目fxml_控制器(){
    @FXML
    private lateinit var newFileSetTreeView:TreeView<Label>
    @FXML
    private lateinit var root:BorderPane

    private lateinit var 新建项目:TreeItem<Label>
    private lateinit var 新建小说项目:TreeItem<Label>
    private lateinit var 新建项目View:Node
    fun initialize(){
        文件树()
        事件()
    }
    fun 文件树(){
        新建项目 = TreeItem<Label>(Label(Index_Config.FileMenu_新建项目))
        新建小说项目 = TreeItem<Label>(Label(Index_Config.FileMenu_新建小说))
        新建项目View = FXMLLoader(None::class.java.getResource("新建项目fxml.fxml")).load()
        val rootTree = TreeItem<Label>()
        rootTree.children.addAll(新建项目,新建小说项目)
        newFileSetTreeView.root = rootTree
        newFileSetTreeView.isShowRoot = false
        newFileSetTreeView.prefHeightProperty().bind(root.heightProperty())
        root.center = 新建项目View
        事件()
    }

    fun 事件(){
        新建项目.value.onMouseClicked = EventHandler {
            if (it.clickCount == 2 && root.center != 新建项目View){
                root.center = 新建项目View
            }
        }
        新建小说项目.value.onMouseClicked = EventHandler {
            if (it.clickCount == 2){
                root.center = VBox()
            }
        }
    }
}

class 新建项目fxmlItem_项目_控制器(){
    @FXML
    private lateinit var iconSeek:Label
    @FXML
    private lateinit var nameTextInput:TextField
    @FXML
    private lateinit var fileTextInput:TextField

    fun initialize(){
        iconSeek.graphic = YtIcon(R.ImageUrl2("文件选择器图标"))
        事件()
    }

    fun 事件(){
        iconSeek.onMouseClicked = EventHandler {
            //然后有一个输入框可以输入项目位置，后面有三个小点可以跳出弹窗选择项目位置
            val chooser = DirectoryChooser()
            chooser.title = "请选择新建项目的位置"
            val fileChooser = chooser.showDialog(topBar.scene.window)
            if (fileChooser != null){
                if (fileChooser.path.last().toString() == "\\" || fileChooser.path.last().toString() == "/"){
                    fileTextInput.textProperty().set(fileChooser.path.plus(nameTextInput.textProperty().get()))
                }else{
                    fileTextInput.textProperty().set(fileChooser.path.plus(nameTextInput.textProperty().get().plus("\\")))
                }
            }
        }
        nameTextInput.textProperty().addListener { _,_,new->
            val text = fileTextInput.textProperty().get()
            val lastIndexOf = when(text.lastIndexOf("\\") != -1){
                true->text.lastIndexOf("\\")
                false->text.lastIndexOf("/")
            }
            if (lastIndexOf == -1){
                fileTextInput.style = """
                -fx-border-color: red;
            """.trimIndent()
            }else{
                fileTextInput.textProperty().set(text.dropLast(text.length-lastIndexOf-1).plus(new))
                fileTextInput.style = """
                -fx-border-color: none;
            """.trimIndent()
            }
        }
    }

    fun 失败的作品(){
        nameTextInput.focusedProperty().addListener{_,_,new:Boolean->
            if (new){
                nameTextInput.textProperty().addListener { _,_,new->
                    val text = fileTextInput.textProperty().get()
                    val lastIndexOf = when(text.lastIndexOf("\\") != -1){
                        true->text.lastIndexOf("\\")
                        false->text.lastIndexOf("/")
                    }
                    if (lastIndexOf == -1){
                        fileTextInput.style = """
                -fx-border-color: red;
            """.trimIndent()
                    }else{
                        fileTextInput.textProperty().set(text.dropLast(text.length-lastIndexOf-1).plus(new))
                        fileTextInput.style = """
                -fx-border-color: none;
            """.trimIndent()
                    }
                }
            }else{
                nameTextInput.textProperty().addListener{_,_,_->}
            }
        }

        fileTextInput.focusedProperty().addListener{_,_,new:Boolean->
            if (new){
                fileTextInput.textProperty().addListener { _,_,new->
                    val text = fileTextInput.textProperty().get()
                    val lastIndexOf = when(text.lastIndexOf("\\") != -1){
                        true->text.lastIndexOf("\\")
                        false->text.lastIndexOf("/")
                    }
                    if (lastIndexOf == -1){
                        fileTextInput.style = """
                    -fx-border-color: red;
                """.trimIndent()
                    }else{
                        nameTextInput.textProperty().set(text.drop(lastIndexOf-1))
                        fileTextInput.style = """
                    -fx-border-color: none;
                """.trimIndent()
                    }
                }
            }else{
                fileTextInput.textProperty().addListener{_,_,_->}
            }
        }
    }
}

class None(){

}

package com.wordtree.wt_kt_note_book

import com.wordtree.wt_kt_module.CommonComponents
import com.wordtree.wt_kt_note_book.module_view_entity.TaskPromptBar
import com.wordtree.wt_kt_note_book.module_view_entity.YtIcon
import com.wordtree.wt_kt_note_book.module_view_entity.YtTreeItem
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.concurrent.Task
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.stage.Stage
import org.fxmisc.richtext.CodeArea
import java.io.*
private var filesNumber = SimpleDoubleProperty(0.0)
private var fileSavings = 0.0

fun 添加一个文件节支(listFile: Array<File>, itemUi: TreeItem<Label>, count: (num:Double)->Unit = {}) {
    val commonComponents = CommonComponents()
    commonComponents.simplePromptBox("正在加载文件，请稍等....", bar)
    bar.isVisible = true
    //遍历所有的文件路径
    for (file in listFile) {
        filesNumber.set(filesNumber.add(1.0).get())
        bar.progress = filesNumber.get()/fileSavings
        //判断该路径是否为文件夹
        if (file.isDirectory) {
            val item = YtTreeItem(file)
            添加一个文件节支(file.listFiles(), item)
            itemUi.children.add(item)
        } else {
            val item = YtTreeItem(file)
            itemUi.children.add(item)
        }
    }
}

private fun low版添加(file: File,itemUi: TreeItem<Label>){
    //判断该路径是否为文件夹
    if (file.isDirectory) {
        val label = Label(file.name)
//            label.graphic = YtIcon(R.ImageUrl2("FileSetIcon"))
        val item = TreeItem(label)
        添加一个文件节支(file.listFiles(), item)
        节点的右击事件(item, file)
        itemUi.children.add(item)
    } else {
        val label = Label(file.name)
//            label.graphic = YtIcon(R.ImageUrl2("FileIcon"))
        val item = TreeItem(label)
        节点的右击事件(item, file)
        itemUi.children.add(item)
    }
}

fun 计算所有的文件节支(listFile: Array<File>):Double{
    for (file in listFile) {
        fileSavings +=  1
        //判断该路径是否为文件夹
        if (file.isDirectory) {
            计算所有的文件节支(file.listFiles())
        }
    }
    return fileSavings
}

fun 节点的右击事件(item: TreeItem<Label>, file: File) {
    if (file.isFile) {
        文件操作(item, file)
    } else if (file.isDirectory) {
        文件夹操作(item, file)
    }
}

fun 文件操作(item: TreeItem<Label>, file: File) {
    var nowFileAbc = file
    val node = item.value
    val contextMenu = ContextMenu()
    val newFile = MenuItem("新建文件")
    newFile.onAction = EventHandler {
        val dialog = TextInputDialog()
        val showAndWait = dialog.showAndWait()
        if (showAndWait.isPresent) {
            println(dialog.editor.text)
            val treeItem = TreeItem<Label>()
            val label = Label(dialog.editor.text)
            treeItem.value = label
            val newFile1 = File(file.parent.plus("/${dialog.editor.text}"))
            newFile1.createNewFile()
            节点的右击事件(treeItem, newFile1)
            item.parent.children.add(treeItem)
        }
    }
    val delFile = MenuItem("删除该文件")
    delFile.onAction = EventHandler {
        item.parent.children.remove(item)
        file.delete()
    }
    val againFile = MenuItem("重命名")
    againFile.onAction = EventHandler {
        val dialog = TextInputDialog()
        val showAndWait = dialog.showAndWait()
        if (showAndWait.isPresent) {
            val pth = file.parent + "/" + dialog.editor.text
            val file1 = File(pth)
            val renameTo = file.renameTo(file1)
            if (renameTo) {
                nowFileAbc = file1
                item.value.textProperty().set(dialog.editor.text)
                for (tab in tabPane.tabs.filter { it.id == file.path }) {
                    tab.textProperty().set(dialog.editor.text)
                    tab.id = pth
                    cursorId.remove(file.path)
                    cursorId.add(pth)
                }
            }

        }
    }
    contextMenu.items.addAll(newFile, delFile, againFile)
    node.onMouseClicked = EventHandler {
        if (it.button == MouseButton.SECONDARY) {
            node.contextMenu = contextMenu
        } else if (it.clickCount >= 2) {
            文件树点击事件对象(nowFileAbc,node)
        }
    }
}

fun 文件夹操作(item: TreeItem<Label>, file: File) {
    var nowFileAbc = file
    val node = item.value
    val contextMenu = ContextMenu()
    val newFileJia = MenuItem("新建文件夹")
    newFileJia.onAction = EventHandler {
        val dialog = TextInputDialog()
        val showAndWait = dialog.showAndWait()
        if (showAndWait.isPresent) {
            val treeItem = TreeItem<Label>()
            val label = Label(dialog.editor.text)
            treeItem.value = label
            val newFile1 = File(file.path.plus("/${dialog.editor.text}"))
            val mkdir = newFile1.mkdirs()
            if (mkdir) {
                节点的右击事件(treeItem, newFile1)
                item.children.add(treeItem)
            }
        }
    }
    val delFile = MenuItem("删除该文件夹")
    delFile.onAction = EventHandler {
        val deleteRecursively = file.deleteRecursively()
        if (deleteRecursively) {
            item.parent.children.remove(item)
        }
    }
    val againFile = MenuItem("重命名")
    againFile.onAction = EventHandler {
        val dialog = TextInputDialog()
        val showAndWait = dialog.showAndWait()
        if (showAndWait.isPresent) {
            val pth = file.parent + "/" + dialog.editor.text
            val file1 = File(pth)
            val renameTo = file.renameTo(file1)
            if (renameTo) {
                if (com.wordtree.wt_kt_note_book.file != null) {
                    val listFiles = com.wordtree.wt_kt_note_book.file!!.listFiles()
                    val fileItemRoot2 =
                        TreeItem<Label>(Label(com.wordtree.wt_kt_note_book.file!!.name))
                    fileItemRoot2.graphic =
                        ImageView(Image(R.ImageUrl2("FileSet"), 15.0, 15.0, true, true))
                    添加一个文件节支(listFiles, fileItemRoot2)
                    fileTreeView.root = fileItemRoot2
                    tabPane.tabs.clear()
                    cursorId = ArrayList<String>()
                    Thread {
                        文件夹操作(fileItemRoot2, com.wordtree.wt_kt_note_book.file!!)
                    }.start()
                }
            }
        }
    }
    val newFile = MenuItem("新建文件")
    newFile.onAction = EventHandler {
        val dialog = TextInputDialog()
        val showAndWait = dialog.showAndWait()
        if (showAndWait.isPresent) {
            val treeItem = TreeItem<Label>()
            val label = Label(dialog.editor.text)
            treeItem.value = label
            val newFile1 = File(file.path.plus("/${dialog.editor.text}"))
            newFile1.createNewFile()
            节点的右击事件(treeItem, newFile1)
            item.children.add(treeItem)
        }
    }
    node.onMouseClicked = EventHandler {
        if (it.button == MouseButton.SECONDARY) {
            node.contextMenu = contextMenu
        }
    }
    contextMenu.items.addAll(newFileJia, newFile, delFile, againFile)
}

fun 文件树点击事件对象(file: File, label: Label) {
    if (cursorId.indexOf(file.path) == -1) {
        val iconText = SimpleStringProperty("")
        cursorPosition.put(file.path.plus("icon_text"), iconText as Any)
        tab标签的切换与文本区光标的聚焦(tabPane, codeArea, file)
    } else {
        val filter = tabPane.tabs.filter { it.id == file.path }
        tabPane.selectionModel.select(filter[0])
    }
}

fun 记录光标位置() {
    var caretPosition = 0
    try {
        if (cursorPosition.get(nowFile!!.path) != null) {
            caretPosition = codeArea.caretPosition
            cursorPosition.put(nowFile!!.path, caretPosition as Any)
        } else {
            cursorPosition.set(nowFile!!.path, caretPosition as Any)
        }
    } catch (e: Exception) {
        println("空个鬼，我不是判断了吗")
    }

}

fun tab标签的切换与文本区光标的聚焦(tabPane: TabPane, codeAreaRequest: CodeArea, file: File) {
    //添加头部标签
    val tab = Tab()
    tab.id = file.path
    tab.textProperty().set(file.name)
    tab.graphic = YtIcon(R.ImageUrl2("FileIcon"))
    //当tab被父类方法selectionModel调用的时候发生下面事件
    tab.onSelectionChanged = EventHandler {
        if ((root.scene.window as Stage).title == "*") {
            保存文件()
        }
        codeArea.clear()
        println(file.name + "@@@")
        println(file.isFile)
        if (file.isFile) {
            显示文本到编辑区(file, codeAreaRequest)
        }
        codeAreaRequest.requestFocus() //聚焦到文本区
    }
    //当tab标签发生改变的时候，将光标位置聚焦到上一次光标位置
    tabPane.selectionModel.selectedItemProperty().addListener { _, _, new ->
        if (new != null) {
            Platform.runLater { codeAreaRequest.requestFocus() }
        }

    }
    //关闭标签的时候清空整个文本区，删除cursorText里面的值,必须要放在最后这个位置
    tab.onCloseRequest = EventHandler {
        if (fileBaocun > 1) {
            val alert = Alert(
                Alert.AlertType.CONFIRMATION, "", ButtonType("不保存", ButtonBar.ButtonData.APPLY),
                ButtonType("保存", ButtonBar.ButtonData.YES), ButtonType("取消", ButtonBar.ButtonData.NO)
            )
            alert.title = "警告！！！"
            alert.headerText = "文件还未保存确定关闭吗？"
            alert.contentText = "需要保存文件请点击ok！\n点击取消将不保存文件"
            val result = alert.showAndWait()
            if (result.get().buttonData.equals(ButtonBar.ButtonData.YES)) {
                //删除与该文件有关的数据
                cursorId.remove(file.path)
                cursorPosition.remove(nowFile?.path)
                cursorPosition.remove(nowFile?.path.plus("change_times"))
                cursorPosition.remove(nowFile?.path.plus("icon_text"), file.name)
                保存文件()
                it.clone()
            } else if (result.get().buttonData.equals(ButtonBar.ButtonData.NO)) {
                it.consume()
            } else if (result.get().buttonData.equals(ButtonBar.ButtonData.APPLY)) {
                cursorId.remove(file.path)
                it.clone()
            }
        } else {
            cursorId.remove(file.path)
        }
        indexFileName.set(cursorId.size)
    }
    tabPane.tabs.add(tab)
    tabPane.selectionModel.select(tab)
    cursorId.add(file.path)
    indexFileName.set(cursorId.size)
}

//这个方法用于显示文字到编辑器上面
fun 显示文本到编辑区(file: File, textArea: CodeArea) {
    nowFile = file
    fileBaocun = 0
    if (cursorPosition.get(file.path.plus("change_times")) == null) {
        val 记录文件被改变的次数 = 0
        nowFile?.let { file -> cursorPosition.put(file.path.plus("change_times"), 记录文件被改变的次数 as Any) }
    } else {
        cursorPosition.set(file.path.plus("change_times"), 0)
    }
    val isr = InputStreamReader(FileInputStream(file), "UTF-8")
    val reader = BufferedReader(isr)
    val readerString = reader.readText()
    isr.close()
    textArea.replaceText(0, 0, readerString)
    if (cursorPosition.get(nowFile?.path) != null) {
        cursorPosition.get(nowFile?.path)
            ?.let { position -> codeArea.moveTo(position as Int);codeArea.showParagraphInViewport(position) }
    }
}

fun 保存文件() {
    if (nowFile!=null){
        val writer = BufferedWriter(OutputStreamWriter(FileOutputStream(nowFile), "utf-8"))
        writer.write(codeArea.text)
        val stage = root.scene.window as Stage
        stage.title = ""
        writer.close()
    }
}

fun 文件树(treeItem:TreeItem<Label>?=null, listFile: Array<File>?=null) {
    //这个是文件树部分
    fileTreeView.apply {
        if (file != null) {
            val fileTreeService = FileTreeService(file!!)
            val taskPromptBar = TaskPromptBar(fileTreeService)
        }else if(treeItem != null){
            添加一个文件节支(listFile!!, treeItem)
        }
    }
}

class FileTreeService(file: File):Task<Double>(){
    override fun call(): Double {
        filesNumber.set(0.0)
        fileSavings = 0.0
        val listFiles = file!!.listFiles()
        val nums = 计算所有的文件节支(listFiles)
        添加一个文件节支(listFiles, fileItemRoot){
            this.updateProgress(it,nums)
        }
//        popOver.hide()
        bar.isVisible = false
        return 0.0
    }

}

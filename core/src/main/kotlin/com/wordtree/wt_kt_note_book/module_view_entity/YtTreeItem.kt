package com.wordtree.wt_kt_note_book.module_view_entity

import com.wordtree.wt_kt_note_book.*
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.MouseButton
import java.io.File

open class YtTreeItem(var file:File,val isFileBool:Boolean = false) :TreeItem<Label>(){
    private val label = Label(file.name)
    init {
        this.value = label

        //判断手动传入的文件判断值，如果为默认值则什么都不会改变
        if (isFileBool){
            this.graphic = YtIcon(R.ImageUrl2("FileIcon"))
        }else{
            this.graphic = when(file.isFile){
                true->YtIcon(R.ImageUrl2("FileIcon"))
                else->YtIcon(R.ImageUrl2("FileSetIcon"))
            }
        }

        initial()
    }

    private fun initial(){
        if (file.isFile){
            this.fileOperations(this,file)
        }else if (file.isDirectory){
            this.fileSetOperations(this,file)
        }
    }

    //节点右击事件对文件的操作
    private fun fileOperations(item: TreeItem<Label>, file: File) {
        var nowFileAbc = file

        val node = item.value

        val contextMenu = ContextMenu()

        val newFile = MenuItem("新建文件")
        newFile.onAction = EventHandler {
            newFile(item,file)
        }

        val delFile = MenuItem("删除该文件")
        delFile.onAction = EventHandler {
            item.parent.children.remove(item)
            file.delete()
            tabPaneOrDelTab(file.path)
        }

        val againFile = MenuItem("重命名")
        againFile.onAction = EventHandler {
            val dialog = TextInputDialog()
            dialog.editor.text = file.name
            val showAndWait = dialog.showAndWait()

            if (showAndWait.isPresent) {
                val onceFile = file
                val pth = file.parent + "/" + dialog.editor.text
                val renameFile = File(pth)

                //判断当前nowFile是不是改变文件，如果是则将新的文件路径放上去
                if (file.renameTo(renameFile)) {
                    if (onceFile == nowFile){
                        nowFile = renameFile
                    }

                    nowFileAbc = renameFile
                    item.value.textProperty().set(dialog.editor.text)

                    //刷新文件事件
                    flush(renameFile)
                    for (tab in tabPane.tabs.filter { it.id == file.path }) {
                        (tab as MyTab).rename(renameFile)
                    }

                }

            }
        }

        contextMenu.items.addAll(newFile, delFile, againFile)

        node.onMouseClicked = EventHandler {
            if (it.button == MouseButton.SECONDARY) {
                node.contextMenu = contextMenu
            } else if (it.clickCount >= 2) {
                fileTreeClickEvent(nowFileAbc)
            }
        }
    }

    //新建一个文件
    private fun newFile(item: TreeItem<Label>, file: File){
        val dialog = TextInputDialog()
        val showAndWait = dialog.showAndWait()
        if (showAndWait.isPresent) {
            var newFile:File? = null
            if (file.isFile){
                 newFile = File(file.parent.plus("/${dialog.editor.text}"))
            }else{
                newFile = File(file.path.plus("/${dialog.editor.text}"))
            }

            newFile.createNewFile()

            val treeItem = YtTreeItem(newFile,true)

            fileOperations(treeItem,newFile)

            if (file.isFile){
                item.parent.children.add(treeItem)
            }else{
                item.children.add(treeItem)
            }
        }
    }

    //节点右击事件对文件夹的操作
    private fun fileSetOperations(item: TreeItem<Label>, file: File) {
        val node = item.value

        val contextMenu = ContextMenu()

        val newFileSet = MenuItem("新建文件夹")
        newFileSet.onAction = EventHandler {
            val dialog = TextInputDialog()
            val showAndWait = dialog.showAndWait()
            if (showAndWait.isPresent) {
                val treeItem = TreeItem<Label>()
                val label = Label(dialog.editor.text)
                treeItem.value = label
                val newFile1 = File(file.path.plus("/${dialog.editor.text}"))
                val mkdir = newFile1.mkdirs()
                if (mkdir) {
                    fileSetOperations(treeItem, newFile1)
                    item.children.add(treeItem)
                }
            }
        }

        val delFileSet = MenuItem("删除该文件夹")
        delFileSet.onAction = EventHandler {
            file.listFiles().forEach {
                tabPaneOrDelTab(it.path)
            }
            val deleteRecursively = file.deleteRecursively()
            if (deleteRecursively) {
                item.parent.children.remove(item)
            }
        }

        val newFileName = MenuItem("重命名")
        newFileName.onAction = EventHandler {
            val dialog = TextInputDialog()
            dialog.editor.text = file.name
            val showAndWait = dialog.showAndWait()
            if (showAndWait.isPresent) {
                //将要改变的文件路径
                val pth = file.parent + "/" + dialog.editor.text
                //曾经的文件路径下面的所有文件
                val onceFileList = file.listFiles()
                //重命名之后的文件路径
                val renameFile = File(pth)
                val renameTo = file.renameTo(renameFile)

                //命名成功之后
                if (renameTo) {
                    //改变文件树的item
                    item.value.textProperty().set(dialog.editor.text)
                    //当前treeTree文件发生改变
                    this.file = renameFile
                    //获取当前文件夹下面所有的文件
                    val nowListFiles = renameFile.listFiles()
                    //改变tab
                    if (tabPane.tabs.size != 0){
                        tabPane.tabs.forEachIndexed{ _, tab->
                            onceFileList.forEachIndexed {index,it->
                                if (tab.idProperty().get() == it.path){
                                    (tab as MyTab).rename(nowListFiles[index])
                                }
                            }
                        }
                        //重新给予事件
                        flush(renameFile)
                    }else{
                        flush(renameFile)
                    }

                }
            }
        }

        val newFile = MenuItem("新建文件")
        newFile.onAction = EventHandler {
            newFile(item, file)
        }

        node.onMouseClicked = EventHandler {
            if (it.button == MouseButton.SECONDARY) {
                node.contextMenu = contextMenu
            }
        }

        contextMenu.items.addAll(newFileSet, newFile, delFileSet, newFileName)
    }

    //文件树的点击事件
    private fun fileTreeClickEvent(file: File) {
        if (tabPane.tabs.size != 0){
            val filterTab = tabPane.tabs.filter { it.id == file.path }
            if (filterTab.isEmpty()){
                //tab标签的切换与文本区光标的聚焦(tabPane, codeArea, file)
                val myTab = MyTab(file)
                tabPane.tabs.add(myTab)
                tabPane.selectionModel.select(myTab)
                globalTab = myTab
            }else{
                tabPane.selectionModel.select(filterTab[0])
                globalTab = filterTab[0] as MyTab?
            }
        }else{
            val myTab = MyTab(file)
            tabPane.tabs.add(myTab)
            globalTab = myTab
        }
    }

    fun removeSelf(){
        if (this.parent != null){
            this.parent.children.remove(this)
        }
    }

    fun flush(file: File){
        val indexOf = this.parent.children.indexOf(this)
        if(file.isDirectory){
            if (this.parent != null){
                val selfParent = this.parent
                removeSelf()
                val treeItem = YtTreeItem(file)
                selfParent.children.add(indexOf, addFileThrift(file.listFiles(),treeItem))
                treeItem.isExpanded = true
                fileTreeView.selectionModel.select(treeItem)
            }
        }else{
            if (this.parent != null){
                val selfParent = this.parent
                removeSelf()
                val treeItem = YtTreeItem(file)
                selfParent.children.add(indexOf,treeItem)
                fileTreeView.selectionModel.select(treeItem)
            }
        }
    }
}

package com.wordtree.wt_kt_note_book.module_view_entity

import com.wordtree.wt_kt_note_book.*
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import java.io.File

open class YtTreeItem(val file:File) :TreeItem<Label>(){
    private val label = Label(file.name)
    init {
        this.value = label
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
            val dialog = TextInputDialog()
            val showAndWait = dialog.showAndWait()
            if (showAndWait.isPresent) {
                println(dialog.editor.text)
                val treeItem = TreeItem<Label>()
                val label = Label(dialog.editor.text)
                treeItem.value = label
                val newFile1 = File(file.parent.plus("/${dialog.editor.text}"))
                newFile1.createNewFile()
                fileOperations(treeItem, newFile1)
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
                fileTreeClickEvent(nowFileAbc)
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

        val delFile = MenuItem("删除该文件夹")
        delFile.onAction = EventHandler {
            val deleteRecursively = file.deleteRecursively()
            if (deleteRecursively) {
                item.parent.children.remove(item)
            }
        }

        val newFileName = MenuItem("重命名")
        newFileName.onAction = EventHandler {
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
                        addFileThrift(listFiles, fileItemRoot2)
                        fileTreeView.root = fileItemRoot2
                        tabPane.tabs.clear()
                        cursorId = ArrayList<String>()
                        Thread {
                            fileSetOperations(fileItemRoot2, com.wordtree.wt_kt_note_book.file!!)
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
                fileSetOperations(treeItem, newFile1)
                item.children.add(treeItem)
            }
        }

        node.onMouseClicked = EventHandler {
            if (it.button == MouseButton.SECONDARY) {
                node.contextMenu = contextMenu
            }
        }

        contextMenu.items.addAll(newFileSet, newFile, delFile, newFileName)
    }

    //文件树的点击事件
    private fun fileTreeClickEvent(file: File) {
        if (cursorId.indexOf(file.path) == -1) {
            val iconText = SimpleStringProperty("")
            cursorPosition.put(file.path.plus("icon_text"), iconText as Any)
            tab标签的切换与文本区光标的聚焦(tabPane, codeArea, file)
        } else {
            val filter = tabPane.tabs.filter { it.id == file.path }
            tabPane.selectionModel.select(filter[0])
        }
    }

}

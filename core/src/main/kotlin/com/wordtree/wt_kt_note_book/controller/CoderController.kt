package com.wordtree.wt_kt_note_book

import cn.hutool.core.io.FileUtil
import com.wordtree.wt_kt_module.CommonComponents
import com.wordtree.wt_kt_note_book.module_view_entity.TaskPromptBar
import com.wordtree.wt_kt_note_book.module_view_entity.YtTreeItem
import javafx.beans.property.SimpleDoubleProperty
import javafx.concurrent.Task
import javafx.scene.control.Label
import javafx.scene.control.TreeItem
import java.io.File

private var filesNumber = SimpleDoubleProperty(0.0)
private var fileSavings = 0.0

//为文件树添加一个文件枝节
fun addFileThrift(listFile: Array<File>, itemUi: TreeItem<Label>, count: (num:Double)->Unit = {}): TreeItem<Label>{
    //给予用户提示，正在执行的任务
    val com = CommonComponents()
    com.simplePromptBox("正在加载文件，请稍等....", bar)

    //当添加文件枝节的时候显示进度条
    bar.isVisible = true

    //遍历所有的文件路径
    for (file in listFile) {
        //更新进度条
        filesNumber.set(filesNumber.add(1.0).get())
        bar.progress = filesNumber.get()/fileSavings

        //判断该路径是否为文件夹
        if (file.isDirectory) {
            val item = YtTreeItem(file)
            addFileThrift(file.listFiles(), item)
            itemUi.children.add(item)
        } else {
            val item = YtTreeItem(file)
            itemUi.children.add(item)
        }
    }
    return itemUi
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

//执行保存文件操作
fun saveFile() {
    val nowCode = nowCode()
    if (nowCode.isModifyBol && nowFile != null){
        FileUtil.writeString(nowCode.text,nowFile!!,"utf-8")
        //更新UI界面
        nowTab()!!.textProperty().set(nowFile!!.name)
        nowCode.isModifyBol = false
    }
}

fun 文件树(treeItem:TreeItem<Label>?=null, listFile: Array<File>?=null) {
    //这个是文件树部分
    fileTreeView.apply {
        if (file != null) {
            val fileTreeService = FileTreeService(file!!)
            val taskPromptBar = TaskPromptBar(fileTreeService)
        }else if(treeItem != null){
            addFileThrift(listFile!!, treeItem)
        }
    }
}

class FileTreeService(file: File):Task<Double>(){
    override fun call(): Double {
        filesNumber.set(0.0)
        fileSavings = 0.0
        val listFiles = file!!.listFiles()
        val nums = 计算所有的文件节支(listFiles)
        addFileThrift(listFiles, fileItemRoot){
            this.updateProgress(it,nums)
        }
        bar.isVisible = false
        return 0.0
    }

}

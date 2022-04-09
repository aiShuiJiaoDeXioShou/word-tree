package com.wordtree.wt_kt_note_book.module_view_entity

import com.wordtree.wt_kt_note_book.globalTab
import com.wordtree.wt_kt_note_book.nowFile
import com.wordtree.wt_kt_note_book.saveFile
import com.wordtree.wt_kt_note_book.service.HTMLEditorService
import com.wordtree.wt_kt_note_book.service.JavaKeywordsService
import com.wordtree.wt_kt_note_book.service.XMLEditorService
import com.wordtree.wt_toolkit.flie_expand.FileToolYt
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Tab
import org.fxmisc.flowless.VirtualizedScrollPane
import org.fxmisc.richtext.CodeArea
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class MyTab(var file:File):Tab() {
    val coderArea = MyCode()
    private val coder = when(FileToolYt.getFileExtension(file)){
        ".java"-> JavaKeywordsService(coderArea).javaCodeArea()
        ".xml"->XMLEditorService(coderArea).init()
        ".html"-> HTMLEditorService(coderArea).init()
        ".c"->VirtualizedScrollPane<CodeArea>(coderArea)
        ".c++"->VirtualizedScrollPane<CodeArea>(coderArea)
        ".txt"->VirtualizedScrollPane<CodeArea>(coderArea)
        else -> {
            VirtualizedScrollPane<CodeArea>(coderArea)
        }
    }
    init {
        this.idProperty().set(this.file.path)
        this.textProperty().set(this.file.name)
        this.graphic = YtIcon(R.ImageUrl2("FileIcon"))
        this.contentProperty().set(coder)
        displayText()
        this.onSelectionChanged = EventHandler {
            nowFile = this.file
            globalTab = this
            Platform.runLater{
                coderArea.requestFocus()
            }
        }
        this.onCloseRequest = EventHandler {
            if (coderArea.isModifyBol){
                val alert = warningAlert()
                val result = alert.showAndWait()
                if (result.get().buttonData.equals(ButtonBar.ButtonData.YES)) {
                    saveFile()
                    it.clone()
                } else if (result.get().buttonData.equals(ButtonBar.ButtonData.NO)) {
                    it.consume()
                } else if (result.get().buttonData.equals(ButtonBar.ButtonData.APPLY)) {
                    it.clone()
                }
            }
        }
    }

    fun rename(file: File):MyTab{
        this.idProperty().set(file.path)
        this.textProperty().set(file.name)
        println("改变了文件路径")
        this.file = file
        return this
    }

    private fun displayText(){
        val isr = InputStreamReader(FileInputStream(this.file), "UTF-8")
        val reader = BufferedReader(isr)
        val readerString = reader.readText()
        coderArea.replaceText(0, 0, readerString)
        isr.close()
    }

    private fun warningAlert():Alert{
        val alert = Alert(
            Alert.AlertType.CONFIRMATION, "", ButtonType("不保存", ButtonBar.ButtonData.APPLY),
            ButtonType("保存", ButtonBar.ButtonData.YES), ButtonType("取消", ButtonBar.ButtonData.NO)
        )
        alert.title = "警告！！！"
        alert.headerText = "文件还未保存确定关闭吗？"
        alert.contentText = "需要保存文件请点击ok！\n点击取消将不保存文件"
        return alert
    }
}

package com.wordtree.wt_kt_note_book.module_view_entity

import com.wordtree.wt_kt_note_book.globalTab
import com.wordtree.wt_kt_note_book.nowFile
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.control.Tab
import org.fxmisc.flowless.VirtualizedScrollPane
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class MyTab(val file:File):Tab() {
    val coderArea = MyCode()
    private val coder = VirtualizedScrollPane(coderArea)
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
    }

    private fun displayText(){
        val isr = InputStreamReader(FileInputStream(this.file), "UTF-8")
        val reader = BufferedReader(isr)
        val readerString = reader.readText()
        coderArea.replaceText(0, 0, readerString)
        isr.close()
    }
}

package com.wordtree.wt_kt_note_book.module_view_entity

import com.wordtree.wt_kt_note_book.fileBaocun
import com.wordtree.wt_kt_note_book.root
import javafx.stage.Stage
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory

class MyCode():CodeArea() {
    init {
        this.paragraphGraphicFactory = LineNumberFactory.get(this)
        this.prefHeightProperty().bind(root.scene.window.heightProperty().add(-100))

        //将codearea里面的内容绑定到readerText里面
        this.textProperty().addListener { or, old, new ->
            fileBaocun++
            val stage = root.scene.window as Stage
            stage.title = ""
            if (new != "" && fileBaocun > 1) {
                stage.title = "*"
            }
        }
    }
}

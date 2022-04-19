package com.wordtree.wt_kt_note_book.module_view_entity

import com.wordtree.wt_kt_note_book.globalTab
import com.wordtree.wt_kt_note_book.nowFile
import com.wordtree.wt_kt_note_book.nowTab
import com.wordtree.wt_kt_note_book.root
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory

class MyCode():CodeArea() {
    private var fileBaocun = 0
    var isModifyBol = false
    init {
        this.paragraphGraphicFactory = LineNumberFactory.get(this)
        this.prefHeightProperty().bind(root.scene.window.heightProperty().add(-100))

        //将codearea里面的内容绑定到readerText里面
        this.textProperty().addListener { or, old, new ->
            fileBaocun++
            if (globalTab != null){
                val nowTab = nowTab()
                val plus = "${nowFile!!.name}*"
                if (new != "" && fileBaocun > 2) {
                    nowTab!!.textProperty().set(plus)
                    this.isModifyBol = true
                }
            }
        }

    }
}

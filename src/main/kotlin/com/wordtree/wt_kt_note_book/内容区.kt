package com.wordtree.wt_kt_note_book

import javafx.stage.Stage
import org.fxmisc.richtext.LineNumberFactory

fun 内容区() {
    //将codearea里面的内容绑定到readerText里面
    codeArea.textProperty().addListener { or, old, new ->
        fileBaocun++
        val stage = codeArea.scene.window as Stage
        stage.title = ""
        if (new != "" && fileBaocun > 1) {
            stage.title = "*"
        }
    }
    codeArea.paragraphGraphicFactory = LineNumberFactory.get(codeArea);
    //这个是tab文件操作部分
    centerPane.apply {
        codeArea.prefHeightProperty().bind(root.scene.window.heightProperty().add(-100))
        fileTab.children.addAll(tabPane)
        this.setDividerPosition(0, -0.1)
        this.setDividerPosition(1, 0.5)
        root.center = centerPane
    }
}

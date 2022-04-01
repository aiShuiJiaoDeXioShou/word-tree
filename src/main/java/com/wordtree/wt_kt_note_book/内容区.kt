package com.wordtree.wt_kt_note_book

import cn.hutool.core.io.FileUtil
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.stage.Stage
import org.fxmisc.flowless.VirtualizedScrollPane
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
    codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
    //这个是tab文件操作部分
    centerPane.apply {
        codeArea.prefHeightProperty().bind(root.scene.window.heightProperty().add(-100))
        fileTab.children.addAll(tabPane, VirtualizedScrollPane(codeArea))
        this.setDividerPosition(0, -0.1)
        this.setDividerPosition(1, 0.5)
        root.center = centerPane
    }
    //每次点击编辑器记录光标的位置
    codeArea.onMouseClicked = EventHandler {
        记录光标位置()
    }
    //每当codeArea发生键盘输入事件，都会对光标位置进行一次刷新
    codeArea.setOnKeyPressed {
        记录光标位置()
    }
}

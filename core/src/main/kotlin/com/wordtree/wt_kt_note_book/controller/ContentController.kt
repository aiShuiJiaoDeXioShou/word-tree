package com.wordtree.wt_kt_note_book

import com.wordtree.wt_kt_note_book.module_view_entity.MyCode
import com.wordtree.wt_kt_note_book.module_view_entity.MyTab
import javafx.stage.Stage
import org.fxmisc.richtext.LineNumberFactory


/**
 * 这里是操作tabPane标签的一些方法
 */
fun tabPaneOrDelTab(id:String){
    tabPane.tabs.removeIf { it.id == id }
}

/**
 * 获取当前的tab对象,此方法只能在第一个tab标签被创造出来时使用
 */
fun nowTab(): MyTab? {
    try {
        return globalTab!!
    }catch (e:NullPointerException){
        System.err.println("此方法只能在第一个tab标签被创造出来时使用")
    }
    return null
}

/**
 * 获取当前的code对象
 */
fun nowCode():MyCode{
    return nowTab()!!.coderArea
}

@Deprecated("该方法已经被弃用了")
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
        this.setDividerPosition(0, -0.1)
        this.setDividerPosition(1, 0.5)

        root.center = centerPaneRoot
    }
}

package com.wordtree.wt_kt_note_book

import com.kodedu.terminalfx.Terminal
import com.wordtree.wt_kt_note_book.module_view_entity.MyCode
import com.wordtree.wt_kt_note_book.module_view_entity.MyTab
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.Tab
import javafx.scene.layout.HBox
import org.yangteng.选项卡
import org.yangteng.选项卡_布局


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

private fun 终端(): Node {
    val hBox = HBox().apply { prefWidth = 1000.0}
    var terIndex = 1;
    hBox.apply {
        val terminalView = Terminal().apply {
            prefHeight = 500.0
            prefWidth = 500.0
        }
        val tabPane = 选项卡_布局()
        val tab = 选项卡()
        tab.apply { content = terminalView;text = "终端" }
        tabPane.tabs.add(tab)
        val box = tabPane.拖动事件().apply { prefWidth = 800.0 }
        val listView = ListView<Button>().apply {
            prefWidth = 50.0
            val button = Button("add")
            button.onAction = EventHandler { tabPane.tabs.add(Tab().apply { content=Terminal().apply {
                prefHeight = 500.0
                prefWidth = 500.0
            };text = "终端${terIndex++}"}) }
            val button1 = Button("2")
            val button2 = Button("3")
            this.items.addAll(button,button1,button2)
            orientation = Orientation.VERTICAL
            minWidth = 50.0
        }
        children.addAll(listView,box)
    }
    return hBox
}

fun 内容区() {
    //这个是tab文件操作部分
    centerPane.apply {
        this.setDividerPosition(0, 0.02)
        this.setDividerPosition(1, 0.5)
        root.center = centerPane
    }
    centerPaneRoot.apply {
        val terminalView = 终端()
        items.addAll(fileTab,terminalView)
        dividers[0].positionProperty().addListener { observable, oldValue, newValue ->
            if (newValue.toDouble() > 0.98) {
                terminalView.isVisible = false
            }else{
                terminalView.isVisible = true
            }
        }
    }
}

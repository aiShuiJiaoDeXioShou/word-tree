package com.wordtree.wt_kt_note_book

import com.wordtree.wt_config.Index_Config
import javafx.event.EventHandler
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.stage.DirectoryChooser

private val 保存 = MenuItem(Index_Config.FileMenu_保存)
private val 打开文件 = MenuItem(Index_Config.FileMenu_打开)
private val 打开文件夹 = MenuItem(Index_Config.FileMenu_打开文件夹)

fun 菜单栏() {
    //这里是上界面menu的内容
    val menu = Menu("文件")
    menu.items.addAll(保存, 打开文件, 打开文件夹)
    topBar.menus.addAll(menu)//将menu放到菜单栏当中
    root.top = topBar
    菜单栏操作()
}

private fun 菜单栏操作() {
    保存.onAction = EventHandler {
        保存文件()
    }
    打开文件.onAction = EventHandler {

    }
    打开文件夹.onAction = EventHandler {
        val chooser = DirectoryChooser()
        chooser.title = "请选择文件夹"
        val fileChoose = chooser.showDialog(topBar.scene.window)
        if (fileChoose != null) {
            file = fileChoose
            fileItemRoot.value.text = file!!.name
            文件树()
        }
    }
}

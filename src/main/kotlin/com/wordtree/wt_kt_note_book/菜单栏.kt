package com.wordtree.wt_kt_note_book

import com.wordtree.wt_config.Index_Config
import com.wordtree.wt_kt_note_book.module_view_entity.YtIcon
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.DirectoryChooser

private val 保存 = MenuItem(Index_Config.FileMenu_保存)
private val 打开文件 = MenuItem(Index_Config.FileMenu_打开)
private val 打开文件夹 = MenuItem(Index_Config.FileMenu_打开文件夹)

private var 设置菜单栏: Menu? = null

fun 菜单栏() {
    //从配置文件中获取菜单项数据
    val menuBarData = R.propertiesItem("menu_bar") as Map<String,Any>
    //这里是上界面menu的内容
    val menu = Menu("文件").apply { styleClass.add("zkh_MenuItem") }
    menu.items.addAll(保存, 打开文件,打开文件夹)

    //获取设置面板的数据
    val settingData = menuBarData.get("setting") as Map<String,Any>
    设置菜单栏 = Menu(settingData.get("item_name") as String).apply { styleClass.add("zkh_MenuItem"); }
    val settingMenuChildren = settingData.get("children") as ArrayList<String>
    if (settingMenuChildren.size != 0 ){
        for (settingChild in settingMenuChildren) {
            设置菜单栏!!.items.add(MenuItem(settingChild))
        }
    }

    topBar.apply {
        padding = Insets(2.0)
        styleClass.add("zkh_MenuBar")
        menus.addAll(menu,设置菜单栏)//将menu放到菜单栏当中
    }
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
            val file = fileChoose
            val treeItem = TreeItem<Label>()
            treeItem.value = Label(file.name).apply { graphic = YtIcon(R.ImageUrl2("FileSet")) }
            添加一个文件节支(file.listFiles(), treeItem)
            fileTreeView.root = treeItem
        }
    }
    for (item in 设置菜单栏!!.items) {
        when(item.text){
            "打开设置面板"-> item.onAction = EventHandler {
                设置面板()
            }
        }
    }
}

private data class MenuItemReader(val item_name:String,val children:ArrayList<String>){

}

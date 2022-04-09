package com.wordtree.wt_kt_note_book

import com.wordtree.wt_config.Index_Config
import com.wordtree.wt_kt_note_book.module_view_entity.YtIcon
import com.wordtree.wt_kt_note_book.view.属于用户的操作逻辑区域
import com.wordtree.wt_kt_note_book.view.左侧项目栏
import com.wordtree.wt_kt_note_book.view.菜单栏
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.stage.Stage
import org.kordamp.bootstrapfx.BootstrapFX
import kotlin.system.exitProcess


class Coder() : Application() {
    private var bol = true
    private val showStage = Stage()

    override fun start(primaryStage: Stage) {
        initYt(primaryStage)
        primaryStage.show()
        //退出java虚拟机
        primaryStage.onCloseRequest = EventHandler {
            exitProcess(0)
        }
    }

    fun show() {
        if (bol){
            Thread {
                initYt(showStage)
            }.run()
        }
        showStage.show()
    }


    /**
     * 实现对整个项目的初始化
     */
    private fun initYt(stage: Stage) {
        bol = false
        stage.scene = Scene(root).apply {
            stylesheets.add(BootstrapFX.bootstrapFXStylesheet())
            stylesheets.add("static/css/java-keywords.css")
            stylesheets.add("static/css/xml-highlighting.css")
            stylesheets.add("static/css/disanfan.css")
            hotKey(this)
        }
        stage.width = R.getPropertie("ADMIN_WIDTH").toDouble()
        stage.height = R.getPropertie("ADMIN_HEIGHT").toDouble()
        stage.icons.add(Image(Index_Config.APP_ICON))
        stage.title = Index_Config.APP_NAME
        //因为动态调用链的需要，必须要把布局放在这个位置
        布局()
        cssInit()
        全局监听事件()
    }

    /**
     * 全局快捷键定义之地
     */
    private fun hotKey(scene: Scene) {
        val codeCombination = KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN)
        scene.accelerators.put(codeCombination) {
            saveFile()
        }
    }

    private fun 布局() {
        //这里是上界面menu的内容
        菜单栏()

        //用户部分布局
        属于用户的操作逻辑区域()

        //这个是文件树部分
        fileItemRoot.graphic = YtIcon(R.ImageUrl2("FileSet"))
        文件树()

        内容区()

        左侧项目栏()


        //确定内容区布局,包过文件树,文件tab,和用户box
        centerPane.items.addAll(文件操作切换(), fileTab, userBox)
        //下面进度条
        进度条()
    }

    private fun 文件操作切换():TabPane{
        val 文件操作切换:TabPane = TabPane()
        val 普通文件夹操作 = Tab("项目库")
        普通文件夹操作.content = fileTreeView
        普通文件夹操作.isClosable = false
        val 小说项目操作 = Tab("项目")
        小说项目操作.content = txtTreeView
        小说项目操作.isClosable = false
        文件操作切换.tabs.addAll(普通文件夹操作,小说项目操作)
        小说项目操作.onSelectionChanged = EventHandler {
            if (nowFile != null){
                println(nowFile!!.name)
            }
        }
        return 文件操作切换
    }

    private fun 进度条(){
        root.bottom = bar.apply { prefWidth=300.0;prefHeight=10.0 }
        bar.isVisible = false
    }

    private fun 全局监听事件(){
        indexFileName.addListener { _, _, new ->
            if (new.toInt() == 0) {
                Thread {
                    Thread.sleep(40);
                    Platform.runLater {
                        codeArea.clear()
                    }
                }.start()
            }
        }

    }
}



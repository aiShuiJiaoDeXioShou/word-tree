package com.wordtree.wt_kt_note_book

import cn.hutool.core.io.FileUtil
import com.wordtree.wt_config.Index_Config
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.stage.Stage
import org.kordamp.bootstrapfx.BootstrapFX


class Coder() : Application() {
    private var bol = true
    private val showStage = Stage()
    override fun start(primaryStage: Stage) {
        初始化文件(primaryStage)
        primaryStage.show()
    }

    fun show() {
        if (bol){
            Thread {
                初始化文件(showStage)
            }.run()
        }
        showStage.show()
    }

    private fun 初始化文件(stage: Stage) {
        bol = false
        stage.scene = Scene(root).apply {
            stylesheets.add(BootstrapFX.bootstrapFXStylesheet())
            stylesheets.add("static/css/disanfan.css")
            快捷键(this)
        }
        stage.width = R.textName("ADMIN_WIDTH").toDouble()
        stage.height = R.textName("ADMIN_HEIGHT").toDouble()
        stage.icons.add(Image(Index_Config.APP_ICON))
        stage.title = Index_Config.APP_NAME
        //因为动态调用链的需要，必须要把布局放在这个位置
        布局()
        cssInit()
        全局监听事件()
    }

    private fun 快捷键(scene: Scene) {
        val codeCombination = KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN)
        scene.accelerators.put(codeCombination) {
            if ((codeArea.scene.window as Stage).title == "*"&&nowFile != null){
                FileUtil.writeString(codeArea.text,nowFile!!,"utf-8")
                (codeArea.scene.window as Stage).title = ""
            }
        }

    }

    private fun 布局() {
        //这里是上界面menu的内容
        菜单栏()

        //用户部分布局
        属于用户的操作逻辑区域()

        //这个是文件树部分
        println(R.ImageUrl("FileSet"))
        fileItemRoot.graphic = ImageView(Image(R.ImageUrl("FileSet"), 15.0, 15.0, true, true))
        文件夹操作(fileItemRoot, file!!)
        文件树()
        内容区()
        左侧项目栏()
        //确定内容区布局,包过文件树,文件tab,和用户box
        centerPane.items.addAll(fileTreeView, fileTab, userBox)
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



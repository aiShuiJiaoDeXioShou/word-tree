package org.yangteng

import com.jfoenix.controls.JFXAlert
import com.jfoenix.controls.JFXButton
import com.kodedu.terminalfx.Terminal
import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.ClipboardContent
import javafx.scene.input.TransferMode
import javafx.scene.layout.VBox
import javafx.stage.Stage

//按钮单元测试
class app1() : Application() {

    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox)
        vBox.children.addAll(按钮("按钮"), JFXButton("呵呵呵"))
        primaryStage.scene = scene
        primaryStage.show()
    }


}

//对话框单元测试
class app2() : Application() {

    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox)
        primaryStage.scene = scene

        val jfxAlert = Dialog<Any>()
        jfxAlert.contentText = "你点击我了，凡人"
        JFXAlert<Any>()
        vBox.children.addAll(按钮("按钮", 1, {
            println("点击我了")
            jfxAlert.show()
        }), JFXButton("呵呵呵"))
        primaryStage.show()
    }
}

//对话框单元测试
class app3() : Application() {

    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox)
        primaryStage.scene = scene
        val dia = 对话框(
            Label("怕个吊，上去就是一脚"),
            ButtonType("确定", ButtonBar.ButtonData.YES),
            ButtonType("取消", ButtonBar.ButtonData.NO),
        )
        vBox.children.addAll(按钮("按钮", 1, {
            dia.show()
        }), JFXButton("呵呵呵"))
        primaryStage.show()
    }
}

//选项卡单元测试
class app4 : Application() {
    override fun start(primaryStage: Stage) {
        val vBox = VBox()
        val scene = Scene(vBox, 400.0, 400.0)
        val tabPane = 选项卡_布局()
        tabPane.tabs.addAll(
            Tab("年后"),
            Tab("年后"),
            Tab("年后"),
            Tab("年后"),
            Tab("年后"),
            Tab("年后"),
        )
        vBox.children.addAll(tabPane)
        primaryStage.scene = scene
        primaryStage.show()
    }
}


class app5(val pane: TabPane) : Application() {
    override fun start(primaryStage: Stage?) {
        var pos = 0
        var prePos = 0
        val vBox = VBox()
        val listView = ListView<Label>().apply {
            orientation = Orientation.HORIZONTAL
            prefHeight = 0.0
            isVisible = false
            prefWidthProperty().bind(pane.widthProperty())
            for (i in pane.tabs) {
                val label = Label(i.text)
                label.onDragOver = EventHandler {
                    try {
                        if (pos < pane.tabs.size) {
                            this.items.get(pos).style = "-fx-background-color: #ffff"
                            pane.tabs.get(pos).style = "-fx-border-color: none"
                        }
                        pane.tabs.get(pane.tabs.size-1).style = "-fx-border-color: none"

                        val indexOf = this.items.indexOf(label)
                        println("$indexOf")
                        pos = indexOf
                        label.style = """
                        -fx-background-color: red;
                    """.trimIndent()
                        pane.tabs.get(pos).style = "-fx-background-color: #ffff"
                    } catch (err: IndexOutOfBoundsException) {
                        pane.tabs.get(pos).style = "-fx-border-color: none"
                        println("没有该值，请重新拖拽")
                    }
                }
                this.items.add(label)
            }
        }
        vBox.children.addAll(listView, pane)

        //为pane注册拖拽事件
        pane.apply {
            onDragDetected = EventHandler {
                val drop = pane.startDragAndDrop(TransferMode.MOVE)
                val content = ClipboardContent()
                content.putString(pane.selectionModel.selectedItem.text)
                drop.setContent(content)
                println("大饺饺")
                listView.prefHeight = 50.0
                listView.isVisible = true
            }
            onDragDone = EventHandler {
                listView.prefHeight = 0.0
                listView.isVisible = false
                if (pos< pane.tabs.size) {
                    val tab = pane.selectionModel.selectedItem
                    pane.tabs.get(pos).style = "-fx-border-color: none"
                    pane.tabs.removeAt(prePos)
                    pane.tabs.add(pos,tab)
                    pane.selectionModel.select(pos)
                }else{
                    pane.tabs.add(pane.selectionModel.selectedItem)
                    pane.tabs.removeAt(prePos)
                    pane.selectionModel.selectLast()
                }
            }
            onDragOver = EventHandler {
                //获取拖拽之后的位置
                val index = pane.selectionModel.selectedIndex
                println("拖拽之前的位置：$index")
                prePos = index
            }
        }

        val scene = Scene(vBox, 400.0, 400.0)
        primaryStage!!.scene = scene
        primaryStage.show()
    }

}

class app6():Application(){
    override fun start(primaryStage: Stage?) {
        val pane = 选项卡_布局()
        pane.tabs.addAll()
        val vbox = pane.拖动事件()
        val vBox = VBox()
        vBox.children.addAll(Button("添加").apply { onMouseClicked= EventHandler { pane.tabs.add(Tab("傻子")) } },vbox)
        val scene = Scene(vBox, 400.0, 400.0)
        primaryStage!!.scene = scene
        primaryStage.show()
    }
}
class app7():Application(){
    override fun start(primaryStage: Stage?) {
        val vBox = VBox()
        val terminalView = Terminal()
        terminalView.prefHeight = 500.0
        terminalView.prefWidth = 500.0
        vBox.children.add(terminalView)
        val scene = Scene(vBox, 400.0, 400.0)
        primaryStage!!.scene = scene
        primaryStage.show()
    }
}

class app8():Application(){
    override fun start(primaryStage: Stage?) {
        val vBox = VBox()

        val scene = Scene(vBox, 400.0, 400.0)
        primaryStage!!.scene = scene
        primaryStage.show()
    }
}


fun main() {
    Application.launch(app8::class.java)
//    regexTest3()
//    regexTest4()
}

fun regexTest1(){
    val regex = "=.*=".toRegex()
    /* val all = regex.findAll("===pinxixi===\nfjsldfjs===dlfjs===jsfldkfjslk===input===\n===第八章===")
     for (i in all) {
         println(i.value)
     }*/

    val str = """
        ===yangteng===
        fjsdlfjsdlf
        jsdlfjsdlfks
        fsdjflsjfls
        jdsflsdjflsd
        ===pinxixi===
    """.trimIndent()

    val toRegex = "[^=]".toRegex()
    /* val find = toRegex.find(str)
     println(find!!.value)*/
    val findAll = toRegex.findAll(str)
    findAll.iterator().forEach { println(it.value) }
    /*val indexOf = str.indexOf("===yangteng===")
    val indexOf1 = str.indexOf("===pinxixi===")
    println(indexOf)
    println(indexOf1)
    val substring = str.substring(indexOf, indexOf1)
    println(substring)*/
}

fun regexTest2(){
    val str = """
        gghjkhkjhk
        hkhkjhhkhk
        ===yangteng===
        fjsdlfjsdlf
        jsdlfjsdlfks
        fsdjflsjfls
        jdsflsdjflsd
        ===pinxixi===
        fsdfsdfsdfsdfs
        fsdfsdfsdfsdf
        fsdfsdfsd
    """.trimIndent()
    val regex = "^[=yangteng=][\\s\\S]*[===pinxixi===]+".toRegex()
    val find = regex.find(str)
    println(find!!.value)
}

fun regexTest3(){
    val str = """
        sdflsdf
        afjdsfljsd
        ba
        dfjsdlfja
        aaafjsdlfjb
        fff
    """.trimIndent()
    val str2 ="afsdfsb"
    val regex = "^s.+s$".toRegex()
    val find1 = regex.find(str2)
    val find = regex.findAll(str)
    find.iterator().forEach { println(it.value) }
    println(find1!!.value)
}

//匹配以指定字符开头，以指定字符结尾的字符串
fun regexTest4(){
    val toRegex = "[^===星辰变===]([\\s\\S]*)[^===变身奥特曼===]".toRegex()
    val find = toRegex.find("""
        ===星辰变===
        sfjdlfkdjlkfjdklfjl
        fjfjfjjjjjb
        fsdfsdfsdf
        ===变身奥特曼===
        fsdfsdfs
    """.trimIndent())
    println(find!!.value)
}

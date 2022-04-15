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
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.util.*

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

//连接sqlite数据库
class app8(){
    //连接sqllite数据库
    fun connectSqlite(): Connection {
        val driver = "org.sqlite.JDBC"
        val url = "jdbc:sqlite:wordtree1.db"
        Class.forName(driver)
        return DriverManager.getConnection(url)
    }
}


fun main() {
//    Application.launch(app7::class.java)
    val connection = app8().connectSqlite()
    val prepareStatement = connection.prepareStatement("select * from wt_user")
    val executeQuery = prepareStatement.executeQuery()
    while (executeQuery.next()) {
        println(executeQuery.getString("user_name"))
    }
    println(connection)
    connection.close()
}

package com.wordtree.wt_kt_note_book

import com.wordtree.wt_config.Index_Config
import com.wordtree.wt_kt_module.CommonComponents
import com.wordtree.wt_kt_module.assembly.BookBox
import com.wordtree.wt_toolkit.flie_expand.R
import javafx.application.Platform
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.Tab
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.ImagePattern
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import java.text.SimpleDateFormat
import java.util.*

fun 属于用户的操作逻辑区域() {
    //用户部分布局
    userBox.apply {
        maxWidth = USER_WIDTH
        minWidth = 0.0
        val userLineBox = HBox()
        //用户的名片部分
        userLineBox.apply {
            name.text = Index_Config.APP_NAME
            grade.text = "lv:45"
            val userHeadPortrait = Image(R.ImageUrl("System.User.Image"))
            val userImage = Circle(ImageSize, ImagePattern(userHeadPortrait))

            prefHeight = 120.0
            background = Background(BackgroundFill(Paint.valueOf("#ff7875"), null, null))
            padding = Insets(20.0, 0.0, 0.0, 20.0)
            //用户的名称签名
            textUserBox.apply {
                spacing = 10.0
                name.style = """
                        -fx-text-fill:#f0f0f0;
                        -fx-font-size: 20;
                    """.trimIndent()

                nameBox.apply {
                    editBut.graphic = ImageView(Image("static/img/编辑.png", 15.0, 15.0, true, true))
                    editBut.padding = Insets(7.0, 0.0, 0.0, 0.0)
                    children.addAll(name, editBut)
                    spacing = 10.0
                }

                pi2.styleClass.add("progressbarYt")
                gradeBox.children.addAll(grade, pi2)
                gradeBox.spacing = 10.0
                children.addAll(nameBox, gradeBox, motto)
                padding = Insets(3.0, 0.0, 0.0, 10.0)
            }
            children.addAll(userImage, textUserBox)
        }
        //顶层选择栏部分
        val userRecord = VBox()
        userRecord.apply {
            prefHeight = 320.0
            //首先是一个tap页，这个页可以切换，让我们查看不同的状态
            val laohuantou = Tab("老环头").apply { this.isClosable = false }
            val heihun = Tab("黑魂").apply { this.isClosable = false }
            val zhilang = Tab("只狼").apply { this.isClosable = false }
            tabSeed.tabs.addAll(
                laohuantou, heihun, zhilang
            )
            tabSeed.selectionModel.selectedItemProperty().addListener { _, old, new ->
                if (new == laohuantou) {
                    println("老头环")
                } else if (new == heihun) {
                    println("黑魂")
                } else if (new == zhilang) {
                    println("直男")
                }
            }
            //常用工具箱
            manuscriptBox.styleClass.add("manuscript-box")
            manuscriptBox.padding = Insets(10.0)
            manuscriptBox.apply {
                children.addAll(
                    Label("常用工具").apply {
                        padding = Insets(10.0, 0.0, 10.0, 0.0)
                    },
                    GridPane().apply {
                        val iconUrl = "static/img/用户工具箱icon/"
                        //设置垂直间距
                        this.vgap = 8.0;
                        //设置水平间距
                        this.hgap = 10.0;
                        val toolboxItem1 = Button()
                        toolboxItem1.graphic = ImageView(Image(iconUrl.plus("jifen.png"), 18.0, 18.0, true, true))
                        val toolboxItem2 = Button()
                        toolboxItem2.graphic = ImageView(Image(iconUrl.plus("kabao.png"), 18.0, 18.0, true, true))
                        val toolboxItem3 = Button()
                        toolboxItem3.graphic = ImageView(Image(iconUrl.plus("pinglun.png"), 18.0, 18.0, true, true))
                        val toolboxItem4 = Button()
                        toolboxItem4.graphic = ImageView(Image(iconUrl.plus("shaixuan.png"), 18.0, 18.0, true, true))
                        val toolboxItem5 = Button()
                        toolboxItem5.graphic = ImageView(Image(iconUrl.plus("shandian.png"), 18.0, 18.0, true, true))
                        val toolboxItem6 = Button()
                        toolboxItem6.graphic = ImageView(Image(iconUrl.plus("shipin.png"), 18.0, 18.0, true, true))
                        val toolboxItem7 = Button()
                        toolboxItem7.graphic = ImageView(Image(iconUrl.plus("shizhong.png"), 18.0, 18.0, true, true))
                        val toolboxItem8 = Button()
                        toolboxItem8.graphic = ImageView(Image(iconUrl.plus("shoucang.png"), 18.0, 18.0, true, true))
                        add(toolboxItem1, 0, 0)
                        add(toolboxItem2, 1, 0)
                        add(toolboxItem3, 2, 0)
                        add(toolboxItem4, 3, 0)
                        add(toolboxItem5, 0, 1)
                        add(toolboxItem6, 1, 1)
                        add(toolboxItem7, 2, 1)
                        add(toolboxItem8, 3, 1)

                        //提示用户当前鼠标停留的位置是什么用法
                        val components = CommonComponents()
                        components.simplePromptBox("金币", toolboxItem1)
                        components.simplePromptBox("金币", toolboxItem2)
                        components.simplePromptBox("金币", toolboxItem3)
                        components.simplePromptBox("金币", toolboxItem4)
                        components.simplePromptBox("金币", toolboxItem5)
                        components.simplePromptBox("金币", toolboxItem6)
                        components.simplePromptBox("金币", toolboxItem7)
                        components.simplePromptBox("金币", toolboxItem8)
                    }
                )
            }

            //monitor这里是提醒用户的进度
            userSpeedProgressBox.apply {
                padding = Insets(10.0)
                val label = Label("实时进度")
                label.apply {
                    padding = Insets(10.0, 0.0, 10.0, 0.0)
                }
                val context = GridPane()
                context.apply {
                    styleClass.add("cart-content")
                    //设置垂直间距
                    this.vgap = 8.0;
                    //设置水平间距
                    this.hgap = 10.0;
                    context.add(Label("本次字数").apply { styleClass.add("topLabel") }, 0, 0)
                    context.add(Label("输入速度(字/时)").apply { styleClass.add("topLabel") }, 1, 0)
                    context.add(Label("码字时间").apply { styleClass.add("topLabel") }, 2, 0)

                    val timerIcon = ImageView(Image("static/img/计时器.png", 15.0, 15.0, true, true))
                    val timerIcon2 = ImageView(Image("static/img/计时器2.png", 15.0, 15.0, true, true))
                    val timerLabel = Label().apply { styleClass.add("topLabel");graphic = timerIcon2; }

                    context.add(timerLabel, 4, 0)

                    context.add(Label("0").apply { styleClass.add("bottomLabel") }, 0, 1)
                    context.add(Label("0").apply { styleClass.add("bottomLabel") }, 1, 1)

                    //这个是计时器代码
                    var second = 1;
                    val dateFormat = SimpleDateFormat("HH时mm分ss秒")
                    dateFormat.timeZone = TimeZone.getTimeZone("GMT+0");
                    var timerStr: StringProperty = SimpleStringProperty("00时00分00秒")
                    val timer = Label().apply { styleClass.add("bottomLabel");this.textProperty().bind(timerStr) }
                    context.add(timer, 2, 1)

                    //计时器事件
                    timerLabel.onMouseEntered = EventHandler {
                        timerLabel.graphic = timerIcon
                    }
                    timerLabel.onMouseExited = EventHandler {
                        timerLabel.graphic = timerIcon2
                    }
                    val shop = SimpleBooleanProperty(false)
                    val thread = Thread {
                        while (true) {
                            if (shop.get()) {
                                Thread.sleep(1000);
                                Platform.runLater {
                                    //这个是他的总时间用秒计算
                                    timerStr.set(dateFormat.format(Date(second * 1000L)))
                                    second++
                                }
                            }

                        }
                    }
                    thread.start()
                    timerLabel.onMouseClicked = EventHandler {
                        shop.set(!(shop.value))
                        println(shop)
                    }
                }
                children.addAll(label, context)
            }
            children.addAll(tabSeed, manuscriptBox, userSpeedProgressBox)
        }
        //书架#b5f5ec
        val userBookshelf = VBox()
        userBookshelf.apply {
            val labelName = Label("我的书架")
            labelName.apply {
                padding = Insets(7.0, 0.0, 7.0, 10.0)
                styleClass.add("label-name")
            }
            val bookProject = ScrollPane()
            bookProject.apply {
                this.prefHeightProperty().bind(root.scene.window.heightProperty().multiply(0.45))
                this.hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
                this.padding = Insets(10.0)
                val box = HBox()
                val bookData = FlowPane(20.0, 10.0)
                box.prefWidth = 290.0
                box.children.add(bookData)
                bookData.alignment = Pos.CENTER_LEFT
                bookData.children.addAll(
                    BookBox(),
                    BookBox(),
                    BookBox(),
                    BookBox(),
                    BookBox(),
                    BookBox(),
                    BookBox(),
                )
                this.content = box
            }
            children.addAll(labelName, bookProject)
        }
        children.addAll(userLineBox, userRecord, userBookshelf)
    }
}


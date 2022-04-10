package com.wordtree.wt_kt_module.assembly

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import java.text.SimpleDateFormat
import java.util.*

class BookBox(
    var name: String = "作者：你自己",
    var date: Date = Date(),
    var zishu: Int = 0,
    var image: Image = Image("static/img/486-2.jpg", 105.0, 137.0, true, true)
) :
    VBox() {
    private val nameLabel = Label(name)
    private val dateLabel = Label(SimpleDateFormat("yyyy-MM-dd").format(this.date))
    private val zishuLabel = Label((this.zishu / 10000).toString().plus("万字"))

    init {
        spacing = 5.0
        styleClass.add("book")
        padding = Insets(8.0, 15.0, 5.0, 15.0)
        children.addAll(ImageView(this.image), nameLabel, dateLabel, zishuLabel)
        alignment = Pos.CENTER
    }
}

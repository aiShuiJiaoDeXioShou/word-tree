package com.wordtree.wt_kt_note_book.module_view_entity

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.InputStream


class YtIcon(
    imgUrl:InputStream,
    image: Image = Image(imgUrl,
    14.0,
    14.0,
    true,
    true)) : ImageView(image){

}

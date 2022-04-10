package com.wordtree.wt_kt_note_book.module_view_entity

import javafx.concurrent.Task
import javafx.scene.control.ProgressBar

class MyTask(val progressBar:ProgressBar):Task<Number>() {
    override fun updateMessage(message: String?) {
        super.updateMessage(message)
    }

    override fun updateValue(value: Number?) {
        super.updateValue(value)
        this.progressProperty().addListener{_,_,new->
            progressBar.progress = new as Double
        }
    }

    override fun call(): Number {
        var i = 0.0
        while(i<30){
            Thread.sleep(500)
            i++
            this.updateProgress(i,30.0)
        }
        return i
    }
}

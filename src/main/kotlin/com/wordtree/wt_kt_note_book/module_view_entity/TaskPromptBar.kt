package com.wordtree.wt_kt_note_book.module_view_entity

import javafx.concurrent.Task
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.ProgressBar

class TaskPromptBar(val myTask: Task<Double>,
                    progressBar: ProgressBar = ProgressBar(0.0),
                    val but: ButtonType = ButtonType("取消", ButtonBar.ButtonData.NO)
): CustomSetting(AlertType.NONE, progressBar, but)  {
    init {
        val thread = Thread(myTask)
        progressBar.prefWidth = 400.0
        //为进度条设置响应事件
        myTask.progressProperty().addListener{_,_,new->
            if (new.toDouble() == 1.0){
                progressBar.progress = new.toDouble()
                this.close()
                myTask.cancel()
            }
        }
        exit()
        //进行该进程
        thread.start()

    }


    //终止任务操作
    fun exit(){
        //终止任务操作
        this.setOnCloseRequest { e ->
            myTask.cancel()
            this.close()
        }
    }
}

package com.wordtree.wt_test;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class 鼠标拖动视窗 implements EventHandler<MouseEvent> {
    private Stage primaryStage; //primaryStage为start方法头中的Stage
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;
    public 鼠标拖动视窗(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(MouseEvent e) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {    //鼠标按下的事件
            this.oldStageX = this.primaryStage.getX();
            this.oldStageY = this.primaryStage.getY();
            this.oldScreenX = e.getScreenX();
            this.oldScreenY = e.getScreenY();

        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {  //鼠标拖动的事件
            this.primaryStage.setX(e.getScreenX() - this.oldScreenX + this.oldStageX);
            this.primaryStage.setY(e.getScreenY() - this.oldScreenY + this.oldStageY);
        }
    }
}

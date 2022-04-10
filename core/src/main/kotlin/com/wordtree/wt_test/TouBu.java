package com.wordtree.wt_test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class TouBu extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox window = new HBox();
        Button 关闭 = new Button("关闭");
        Button 最小化 = new Button("最小化");
        Button 用户中心 = new Button("用户中心");
        Button 最大化 = new Button("最大化");
        window.getChildren().addAll(关闭,最小化,用户中心,最大化);
        //设置最小化
        最小化.setOnMouseClicked(e->primaryStage.setIconified(true));
        关闭.setOnMouseClicked(e->primaryStage.close());
        最大化.setOnMouseClicked(e->primaryStage.setMaximized(!primaryStage.isMaximized()));
        Scene scene = new Scene(window);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNIFIED);
        scene.getStylesheets().add("static/css/style.css");
        primaryStage.show();
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        鼠标拖动视窗 _鼠标拖动视窗 = new 鼠标拖动视窗(primaryStage);
        window.setOnMousePressed(_鼠标拖动视窗);/* 鼠标按下 */
        window.setOnMouseDragged(_鼠标拖动视窗);/* 鼠标拖动 */

    }

    public static void main(String[] args) {
        launch(args);
    }


}

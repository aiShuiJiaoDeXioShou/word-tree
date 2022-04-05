package com.wordtree.wt_test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Shopping extends Application {

    @Override
    public void start(Stage 窗口) throws Exception {
        窗口.setScene(admin());
        ShopeInit(窗口);
    }

    void ShopeInit(Stage 窗口){
        窗口.setWidth(1000);
        窗口.setHeight(800);
        窗口.setTitle("Shope");
        窗口.getIcons().add(new Image(getClass().getClassLoader().getResource("icon.png").toString()));
        窗口.show();
    }
    public Scene Index(){
        Scene scene = new Scene(null);
        return scene;
    }
    public Scene admin(){
        //这个是界面布局
        VBox root = new VBox();
        Label label = new Label("用户:\t");
        Label label2 = new Label("密码:\t");
        Label logo = new Label("Shope");
        logo.setPadding(new Insets(20));
        Font font = new Font(40);
        logo.setFont(font);
        logo.setStyle("-fx-font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;-fx-text-fill:#cd5c5c");
        root.setBackground(new Background(new BackgroundImage(new Image("hehe.jpg",640,365,true,true),null,null, BackgroundPosition.CENTER,null)));

        TextField name = new TextField();
        name.setPrefWidth(300);
        name.setPrefHeight(40);
        HBox yonhu = new HBox();
        yonhu.getChildren().addAll(label,name);
        yonhu.setAlignment(Pos.CENTER);


        TextField password = new TextField();
        password.setPrefWidth(300);
        password.setPrefHeight(40);
        HBox mima = new HBox();
        mima.setAlignment(Pos.CENTER);
        mima.getChildren().addAll(label2,password);

        Button 点击登入 = new Button("点击登入");
        Button 注册用户 = new Button("注册用户");
        点击登入.getStyleClass().add("button");
        注册用户.getStyleClass().add("button");
        HBox 操作区域 = new HBox();
        操作区域.getChildren().addAll(点击登入,注册用户);
        操作区域.setAlignment(Pos.CENTER);
        操作区域.setSpacing(50);

        root.getChildren().addAll(logo,yonhu,mima,操作区域);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        //这个是逻辑操作
        点击登入.setOnMouseClicked(e->{
            String nameText = name.getText();
            String passwordText = password.getText();
            System.out.println(nameText+passwordText);
            if(nameText.equals("admin")&&passwordText.equals("123456")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("登入成功");
                alert.show();
            }

        });
        //下面这个是UI美化
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        return scene;
    }
}

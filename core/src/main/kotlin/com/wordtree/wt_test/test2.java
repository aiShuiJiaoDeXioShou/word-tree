package com.wordtree.wt_test;

import com.wordtree.wt_toolkit.flie_expand.R;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class test2 extends Application {
    public static void main(String[] args) throws IOException {
//        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Image images = new Image(R.ImageUrl2("FileIcon"));
        ImageView image = new ImageView(images);
        root.getChildren().add(image);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.getIcons().add(images);
    }
}

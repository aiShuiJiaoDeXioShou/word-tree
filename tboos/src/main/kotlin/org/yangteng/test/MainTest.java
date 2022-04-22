package org.yangteng.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.yangteng.MyListView;

public class MainTest extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MyListView myListView = new MyListView();
        myListView.getItems().addAll("dsf","fsdf","Fsdf");
        myListView.设置拖拽特效();
        VBox box = new VBox();
        box.getChildren().add(myListView);
        primaryStage.setScene(new Scene(box));
        primaryStage.show();
    }
}

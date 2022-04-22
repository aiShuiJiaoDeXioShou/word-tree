package org.yangteng;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MyListView extends ListView<String> {
    public MyListView() {
    }

    public void 设置拖拽特效(){
        setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            Label nowLabel;
            @Override
            public ListCell<String> call(ListView<String> param) {
                ListCell<String> cell = new ListCell<>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            nowLabel = new Label();
                            nowLabel.setText(item);
                            this.setGraphic(nowLabel);
                        }
                    }
                };


                cell.hoverProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            System.out.println(nowLabel.getText());
                            cell.setStyle("-fx-background-color: #00ff00");
                        } else {
//                            cell.setStyle("-fx-background-color: #ffffff");
                        }
                    }
                });

                return cell;
            }
        });
    }
}


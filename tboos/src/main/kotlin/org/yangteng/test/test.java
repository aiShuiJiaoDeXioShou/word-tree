package org.yangteng.test;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class test {
    public static void main(String[] args) {
        ListView listView = new ListView();
        listView.setCellFactory((Callback<ListView, ListCell>) param -> {
            ListCell listCell = new ListCell(){
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                }
            };

            return null;
        });
    }
}

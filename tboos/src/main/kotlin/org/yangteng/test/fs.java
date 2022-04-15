package org.yangteng.test;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.util.Callback;

public class fs {
    public static void main(String[] args) {
        ListView<Label> labelListView = new ListView<>();
        TabPane tabPane = new TabPane();
        labelListView.setCellFactory(new Callback<ListView<Label>, ListCell<Label>>() {
            @Override
            public ListCell<Label> call(ListView<Label> param) {
                Label label = new Label();
                label.onDragOverProperty().addListener(new ChangeListener<EventHandler<? super DragEvent>>() {
                    @Override
                    public void changed(ObservableValue<? extends EventHandler<? super DragEvent>> observable, EventHandler<? super DragEvent> oldValue, EventHandler<? super DragEvent> newValue) {
                        if (newValue != null) {

                        }
                    }
                });
                ListCell<Label> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Label item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getText());
                        }
                    }
                };
                return cell;
            }
        });
    }
}

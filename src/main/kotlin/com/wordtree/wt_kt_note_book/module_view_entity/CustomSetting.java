package com.wordtree.wt_kt_note_book.module_view_entity;

import javafx.beans.InvalidationListener;
import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomSetting extends Dialog<ButtonType> {
    private double x = 0.00;
    private double y = 0.00;
    private double RESIZE_WIDTH = 5.00;
    private double MIN_WIDTH = 400.00;
    private double MIN_HEIGHT = 50.0;
    private double xOffset = 0, yOffset = 0;//自定义dialog移动横纵坐标

    public static enum AlertType {
        NONE,
    }

    private WeakReference<DialogPane> dialogPaneRef;

    private boolean installingDefaults = false;
    private boolean hasCustomButtons = false;
    private boolean hasCustomTitle = false;
    private boolean hasCustomHeaderText = false;

    private final InvalidationListener headerTextListener = o -> {
        if (!installingDefaults) hasCustomHeaderText = true;
    };

    private final InvalidationListener titleListener = o -> {
        if (!installingDefaults) hasCustomTitle = true;
    };

    private final ListChangeListener<ButtonType> buttonsListener = change -> {
        if (!installingDefaults) hasCustomButtons = true;
    };


    public CustomSetting(@NamedArg("alertType") AlertType alertType,
                         @NamedArg("Node") Node node,
                         @NamedArg("buttonTypes") ButtonType... buttons) {
        super();

        final DialogPane dialogPane = getDialogPane();
        getDialogPane().getStyleClass().add("alert");

        dialogPaneRef = new WeakReference<>(dialogPane);
        hasCustomButtons = buttons != null && buttons.length > 0;
        if (hasCustomButtons) {
            for (ButtonType btnType : buttons) {
                dialogPane.getButtonTypes().addAll(btnType);
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("正在执行任务"));
        borderPane.setCenter(node);
        dialogPane.setContent(borderPane);
        borderPane.setMinWidth(MIN_WIDTH);
        borderPane.setMinHeight(MIN_HEIGHT);
        //获取当前视窗
        Stage window = (Stage)dialogPane.getScene().getWindow();
        //隐藏windows平台原有的样式
        window.initStyle(StageStyle.TRANSPARENT);
        //设置移动事件
        MouseDragEvent(borderPane,window);

        setAlertType(alertType);
        dialogPaneProperty().addListener(o -> updateListeners());
        titleProperty().addListener(titleListener);
        updateListeners();
    }

    //窗口的拖动事件
    private void MouseDragEvent(Node root,Stage primaryStage){
        //监听窗口的x跟y值，当他们发生改变的时候刷新值
        primaryStage.xProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                x = newValue.doubleValue();
            }
        });

        primaryStage.yProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                y = newValue.doubleValue();
            }
        });

        //当鼠标点击该位置的时候获取横纵坐标
        root.setOnMousePressed(event -> {
            event.consume();
            xOffset = event.getSceneX();
            if (event.getSceneY() > 46) {
                yOffset = 0;
            } else {
                yOffset = event.getSceneY();
            }
        });

        //根据横纵坐标的位置，监听鼠标的拖动事件，改变窗体位置
        root.setOnMouseDragged(event -> {
            //根据鼠标的横纵坐标移动dialog位置
            if (yOffset != 0 ) {
                primaryStage.setX(event.getScreenX() - xOffset);
                if (event.getScreenY() - yOffset < 0) {
                    primaryStage.setY(0);
                } else {
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            }
        });
    }

    private final ObjectProperty<AlertType> alertType = new SimpleObjectProperty<AlertType>(null) {
        final String[] styleClasses = new String[] { "information", "warning", "error", "confirmation" };

        @Override
        protected void invalidated() {
            String newTitle = "";
            String newHeader = "";
//            Node newGraphic = null;
            String styleClass = "";
            ButtonType[] newButtons = new ButtonType[] { ButtonType.OK };
            switch (getAlertType()) {
                case NONE: {
                    newButtons = new ButtonType[] { };
                    break;
                }
            }

            installingDefaults = true;
            if (!hasCustomTitle) setTitle(newTitle);
            if (!hasCustomHeaderText) setHeaderText(newHeader);
            if (!hasCustomButtons) getButtonTypes().setAll(newButtons);

            // update the style class based on the alert type. We use this to
            // specify the default graphic to use (i.e. via CSS).
            DialogPane dialogPane = getDialogPane();
            if (dialogPane != null) {
                List<String> toRemove = new ArrayList<>(Arrays.asList(styleClasses));
                toRemove.remove(styleClass);
                dialogPane.getStyleClass().removeAll(toRemove);
                if (! dialogPane.getStyleClass().contains(styleClass)) {
                    dialogPane.getStyleClass().add(styleClass);
                }
            }

            installingDefaults = false;
        }
    };

    public final AlertType getAlertType() {
        return alertType.get();
    }

    public final void setAlertType(AlertType alertType) {
        this.alertType.setValue(alertType);
    }

    public final ObjectProperty<AlertType> alertTypeProperty() {
        return alertType;
    }

    public final ObservableList<ButtonType> getButtonTypes() {
        return getDialogPane().getButtonTypes();
    }

    private void updateListeners() {
        DialogPane oldPane = dialogPaneRef.get();

        if (oldPane != null) {
            oldPane.headerTextProperty().removeListener(headerTextListener);
            oldPane.getButtonTypes().removeListener(buttonsListener);
        }

        DialogPane newPane = getDialogPane();
        if (newPane != null) {
            newPane.headerTextProperty().addListener(headerTextListener);
            newPane.getButtonTypes().addListener(buttonsListener);
        }

        dialogPaneRef = new WeakReference<DialogPane>(newPane);
    }

}

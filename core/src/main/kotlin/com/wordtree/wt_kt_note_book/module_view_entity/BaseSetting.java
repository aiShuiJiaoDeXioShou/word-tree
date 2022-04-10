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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BaseSetting extends Dialog<ButtonType> {

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


    public BaseSetting(@NamedArg("alertType") AlertType alertType,
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
        dialogPane.setContent(node);

        setAlertType(alertType);


        dialogPaneProperty().addListener(o -> updateListeners());
        titleProperty().addListener(titleListener);
        updateListeners();
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

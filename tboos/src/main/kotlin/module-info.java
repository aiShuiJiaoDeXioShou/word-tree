module tboos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;
    requires com.jfoenix;
    requires javafx.base;
    opens org.yangteng to javafx.fxml,javafx.controls;
    exports org.yangteng;
}

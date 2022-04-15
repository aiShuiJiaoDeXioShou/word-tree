module tboos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;
    requires com.jfoenix;
    requires javafx.base;
    requires com.kodedu.terminalfx;
    requires java.sql;
    opens org.yangteng to javafx.fxml,javafx.controls,javafx.graphics;
    opens org.yangteng.test to javafx.fxml,javafx.controls,javafx.graphics;
    exports org.yangteng;
}

module com.wordtree {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires kotlin.stdlib;
    requires java.desktop;
    requires java.sql;
    requires org.fxmisc.richtext;
    requires com.google.gson;
    requires org.fxmisc.flowless;

    opens com.wordtree to javafx.fxml;
    opens com.wordtree.wt_kt_module to javafx.fxml;
    exports com.wordtree.wt_kt_module;
    exports com.wordtree;
}

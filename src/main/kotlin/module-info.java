module com.wordtree {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires hutool.all;
    requires mysql.connector.java;
    requires java.desktop;
    requires java.sql;
    requires com.google.gson;
    requires org.fxmisc.richtext;
    requires org.fxmisc.flowless;

    opens com.wordtree to javafx.fxml,hutool.all;
    exports com.wordtree;
    exports com.wordtree.wt_kt_note_book;
    exports com.wordtree.wt_test;
}
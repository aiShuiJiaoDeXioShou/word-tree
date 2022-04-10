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
    requires reactfx;
    requires com.jfoenix;
    requires tboos;

    opens com.wordtree to javafx.fxml,hutool.all;
    opens com.wordtree.wt_kt_note_book to javafx.fxml,hutool.all;
    opens com.wordtree.wt_kt_note_book.view to javafx.fxml,hutool.all;
    opens com.wordtree.wt_kt_note_book.service to javafx.fxml,hutool.all;
    opens com.wordtree.wt_kt_note_book.view.user to javafx.fxml,hutool.all;
    exports com.wordtree;
    exports com.wordtree.wt_kt_note_book;
    exports com.wordtree.wt_test;
    exports com.wordtree.wt_kt_note_book.module_view_entity;
    exports com.wordtree.wt_kt_note_book.view;
    exports com.wordtree.wt_kt_note_book.service;
}

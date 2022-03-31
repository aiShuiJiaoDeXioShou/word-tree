package com.wordtree.wt_toolkit.flie_expand;

import com.wordtree.Main;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileItemUi {
    public void addItemUi(List<File> listFile, TreeItem itemUi){
        for (File file : listFile) {
            if (file.isDirectory()){
                TreeItem<String> item = new TreeItem<>(file.getName());
                addItemUiOnes(Arrays.asList(file.listFiles()),item);
            }else {
                itemUi.getChildren().add(file.getName());
            }
        }
    }

    public void addItemUiOnes(List<File> listFile, TreeItem itemUi){
        for (File file : listFile) {
            if (file.isDirectory()){
                TreeItem<String> item = new TreeItem<>(file.getName());
                addItemUi(Arrays.asList(file.listFiles()),item);
            }else {
                itemUi.getChildren().add(file.getName());
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("D:\\3.Node.js\\项目以及学习目录\\JavaScript学习");

    }
}

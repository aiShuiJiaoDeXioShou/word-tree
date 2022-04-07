package com.wordtree.wt_toolkit.flie_expand;

import java.io.File;
import java.net.URL;

public class FileToolYt {
    public static String getResource(String name){
        return FileToolYt.class.getClassLoader().getResource("static")+name;
    }
    public static URL getResource(){
        return FileToolYt.class.getClassLoader().getResource("img");
    }

    public static String getFileExtension(File file) {
        String extension = "";
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            e.printStackTrace();
            extension = "";
        }
        return extension;
    }
}

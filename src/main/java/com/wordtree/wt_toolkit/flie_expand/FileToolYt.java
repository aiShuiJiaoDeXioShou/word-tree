package com.wordtree.wt_toolkit.flie_expand;

import java.net.URL;

public class FileToolYt {
    public static String getResource(String name){
        return FileToolYt.class.getClassLoader().getResource("static")+name;
    }
    public static URL getResource(){
        return FileToolYt.class.getClassLoader().getResource("img");
    }
}

package com.wordtree.wt_test;

import java.io.InputStream;

public class aoteman {
    public static void main(String[] args) {
        InputStream resourceAsStream = aoteman.class.getResourceAsStream("HanSolo.png");
        System.out.println(resourceAsStream == null);
        System.out.println(aoteman.class.getResource("HanSolo.png"));
    }
}

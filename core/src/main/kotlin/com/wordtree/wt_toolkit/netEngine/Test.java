package com.wordtree.wt_toolkit.netEngine;

import java.io.IOException;

public class Test implements Runnable{
    private boolean banduan=true;
    public static void main(String[] args) throws IOException {
        NetEngine engine=new NetEngine();
        engine.WTQQ();
    }

    @Override
    public void run() {

    }

    public Test() {
    }

    public Test(boolean banduan) {
        this.banduan = banduan;
    }

    public boolean isBanduan() {
        return banduan;
    }

    public void setBanduan(boolean banduan) {
        this.banduan = banduan;
    }
}

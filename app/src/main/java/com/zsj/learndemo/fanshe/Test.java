package com.zsj.learndemo.fanshe;

import android.util.Log;

public class Test {
    private String msg = "zsj";
    private static final String abd = "xx";
    private int a;
    private void sendMsg(String ss,int sw){
        Log.d("zsjTest", "sendMsg: "+msg+"--"+ss+"-"+sw+"--"+a);
    }

    public String getMsg(){
        return msg;
    }

    public String getAbd(){
        return abd;
    }
}

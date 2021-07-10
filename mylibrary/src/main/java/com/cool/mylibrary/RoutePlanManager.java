package com.cool.mylibrary;

import android.util.Log;

import java.sql.RowId;

public class RoutePlanManager {
    private String name = "RoutePlanManager";
    public static String get() {
        return "hello world";
    }
    public static  int add(int a){
        return a+1;
    }
    public void testThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("ttttt","11111111111111111");
            }
        }).start();
    }
}

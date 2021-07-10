package com.cool.myjartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {
    public static final String SHOWSTRINGCLASS_PATH = "com.cool.mylibrary.RoutePlanManager";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMyDex();
//        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
//        String jarPath = sdcard + "/Jar/dex.jar";
//        String tmpPath = getApplicationContext().getDir("Jar", 0).getAbsolutePath();
//        DexClassLoader cl = new DexClassLoader(jarPath, tmpPath
//                , null, this.getClass().getClassLoader());
//        Class<?> libProviderCls = null;
//        try {
//            libProviderCls = cl.loadClass("com.cool.mylibrary.RoutePlanManager");
//            Constructor<?> localConstructor = libProviderCls.getConstructor(new Class[] {});
//            Object obj = localConstructor.newInstance(new Object[] {});
//            Method mMethodWrite = libProviderCls.getDeclaredMethod("get");
//            mMethodWrite.setAccessible(true);
//            String str = (String) mMethodWrite.invoke(obj);
//            Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    private void loadMyDex() {
        File cacheFile = FileUtils.getCacheDir(getApplicationContext());
        String internalPath = cacheFile.getAbsolutePath() + File.separator + "dex.jar";
        File desFile = new File(internalPath);
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();
                FileUtils.copyFiles(this, "dex.jar", desFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, getClassLoader());
        Class dexClazz;
        try {
            dexClazz = dexClassLoader.loadClass(SHOWSTRINGCLASS_PATH);
            Object instance = dexClazz.newInstance();
            Method action = dexClazz.getDeclaredMethod("get");
            String hello = (String) action.invoke(instance);
            Toast.makeText(this, hello, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("SIMON", e.getMessage());
        }
    }
}
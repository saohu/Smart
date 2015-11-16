package com.example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 007 on 2015/11/16.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    public String createTableSQL = "create table if not exists word"+"(_id integer primary key autoincrement ,word text)";
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableSQL);
        db.execSQL("insert into woed(word)values(?)",new String[]{"Android 应用程序开发"});
        db.execSQL("insert into woed(word)values(?)",new String[]{"Android 疯狂的android讲义"});
        db.execSQL("insert into woed(word)values(?)",new String[]{"Android 开发宝典"});
        db.execSQL("insert into woed(word)values(?)",new String[]{"Android 解密"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.print("版本变化");
    }
}

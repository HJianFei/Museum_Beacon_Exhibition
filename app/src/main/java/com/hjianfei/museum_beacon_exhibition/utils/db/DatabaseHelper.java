package com.hjianfei.museum_beacon_exhibition.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HJianFei on 2016/11/9.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // 类没有实例化,是不能用作父类构造器的参数,必须声明为静态
    private static final String name = "city"; // 数据库名称
    private static final int version = 1; // 数据库版本
    public DatabaseHelper(Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS recent_city (id integer primary key autoincrement, name varchar(40), date INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

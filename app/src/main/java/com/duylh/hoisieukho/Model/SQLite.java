package com.duylh.hoisieukho.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Admin on 18/02/2017.
 */

public class SQLite extends SQLiteOpenHelper {

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void INSERT_RANK(String Player, int Point){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO XepHang_Table VALUES(null, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1, Player);
        statement.bindLong(2, Point);
        statement.executeInsert();
    }
}

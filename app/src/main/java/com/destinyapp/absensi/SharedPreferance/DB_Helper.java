package com.destinyapp.absensi.SharedPreferance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "absensi.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_SESSION = "session";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_DIVISI = "divisi";
    public static final String COLUMN_LEVEL = "level";

    public DB_Helper(Context context){super(
            context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_SESSION+" (" +
                COLUMN_USERNAME+" TEXT PRIMARY KEY, "+
                COLUMN_NAMA+" TEXT NOT NULL, "+
                COLUMN_DIVISI+" TEXT NOT NULL, "+
                COLUMN_LEVEL+" TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        this.onCreate(db);
    }
    public void saveSession(String username,String nama,String divisi,String level){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,username);
        values.put(COLUMN_NAMA,nama);
        values.put(COLUMN_DIVISI,divisi);
        values.put(COLUMN_LEVEL,level);
        db.insert(TABLE_SESSION,null,values);
        db.close();
    }
    public void userLogout(Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_SESSION+"");
        Toast.makeText(context, "Logout Berhasil", Toast.LENGTH_SHORT).show();
    }
    public Cursor checkSession(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_SESSION+"";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}

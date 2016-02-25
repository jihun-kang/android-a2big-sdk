package com.a2big.android.library.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by a2big on 16. 2. 21..
 */
public class DBBasic extends SQLiteOpenHelper {

    private String DATABASE_NAME;
    private int DATABASE_VERSION;
    private String TABLE_NAME;
  //  static String PACKEGE = "com.a2big.sqlitetest";
    private  String mPackageName;

    public void setDATABASE_NAME(String dATABASENAME) {
        DATABASE_NAME = dATABASENAME;
    }

    public void setDATABASE_VERSION(int dATABASEVERSION) {
        DATABASE_VERSION = dATABASEVERSION;
    }

    public void setTABLE_NAME(String tABLENAME) {
        TABLE_NAME = tABLENAME;
    }

    public DBBasic(Context context, String name, CursorFactory factory,
                   int version) {
        super(context, name, factory, version);
        DATABASE_NAME = name;
        mPackageName = context.getPackageName();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            String createSql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(" +
                    "ID              INTEGER," +
                    "SI_CODE         INTEGER," +
                    "KU_CODE         INTEGER," +
                    "STR_SI          VARCHAR(255)," +
                    "STR_KU          VARCHAR(255)," +
                    "STR_DONG        VARCHAR(255)," +
                    "PRIMARY KEY (ID)" +
                    ");";

            db.execSQL(createSql);

        }catch(SQLException e)
        {
            Log.e("DB BASIC", "onCreate of DB Creation");
            Log.e("DB BASIC", e.getMessage());

        }catch(Exception e)
        {
            Log.e("DB BASIC", "onCreate of DB Creation");
            Log.e("DB BASIC", e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try
        {
            String createSql = " DROP TABLE IF EXISTS " + TABLE_NAME;
            Log.e("DB", createSql);

            db.execSQL(createSql);
            onCreate(db);
        }catch(Exception e)
        {
            Log.e("DB BASIC", "onUpgrade of DB Creation");
            Log.e("DB BASIC", e.getMessage());
        }


    }

}

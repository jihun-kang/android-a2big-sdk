package com.a2big.android.library.db;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by a2big on 16. 2. 21..
 */
public class DBWorker extends Activity{

    private DBBasic helper;

    private String DATABASE_NAME ;
    private int DATABASE_VERSION ;
    private String TABLE_NAME ;

    public  final String LIST_TITLE = "FileName";

    public DBWorker(Context conn, String dbNm, String tblNm, int version)
    {
        this.DATABASE_NAME = dbNm;
        this.DATABASE_VERSION = version;
        this.TABLE_NAME = tblNm;

        Log.e("DBWorker", "DBWorker........... "+conn.getPackageName());
        helper = new DBBasic(conn,DATABASE_NAME, null,DATABASE_VERSION);

        helper.setDATABASE_NAME(DATABASE_NAME);
        helper.setDATABASE_VERSION(DATABASE_VERSION);
        helper.setTABLE_NAME(TABLE_NAME);
        //helper.set_ID(Integer.parseInt(_ID));
    }


    public DBWorker open(SQLiteDatabase db, String type) throws SQLException
    {

        if("read".equals(type))
        {
            db = helper.getReadableDatabase();
        }else if("write".equals(type))
        {
            db = helper.getWritableDatabase();
        }


        return this;
    }

    public SQLiteDatabase open(String type) throws SQLException
    {
        SQLiteDatabase sb = null;
        if("read".equals(type))
        {
            sb = helper.getReadableDatabase();
        }else if("write".equals(type))
        {
            sb = helper.getWritableDatabase();
        }
        return sb;
    }

    public void close(SQLiteDatabase db) throws SQLException
    {
        db.close();
    }



    public void insertMethod(SQLiteDatabase db, String sql) throws Exception
    {
        db.beginTransaction(); //트랜잭션 시작

        try
        {
            db.execSQL(sql);
            db.setTransactionSuccessful(); // 트랜잭션 성공 (COMMIT)
        }catch(Exception e)
        {
            Log.e("DB WORKER", " ERROR OF INSERT ");
            Log.e("DB WORKER", e.getMessage());
            throw new Exception ("Error : " + e);
        }finally
        {
            db.endTransaction(); // 트랜잭션 종료
            //setTransactionSuccessful()함수를 실행하지 못하고 endTransaction()를 실행하면 rollback
        }

    }

    public void removeMethod(SQLiteDatabase db, String sql) throws Exception
    {
        db.beginTransaction(); //트랜잭션 시작
        try
        {
            db.execSQL(sql);
            db.setTransactionSuccessful(); // 트랜잭션 성공 (COMMIT)
        }catch(Exception e)
        {
            Log.e("DB WORKER", " ERROR OF DELETE ");
            Log.e("DB WORKER", e.toString());
            throw new Exception ("Error : " + e);
        }finally
        {
            db.endTransaction(); // 트랜잭션 종료
        }
    }


    public void removeAllMethod(SQLiteDatabase db, String sql) throws Exception
    {
        db.beginTransaction(); //트랜잭션 시작

        try
        {
            db.execSQL(sql);
            db.setTransactionSuccessful(); // 트랜잭션 성공 (COMMIT)
        }catch(Exception e)
        {
            Log.e("DB WORKER", " ERROR OF DELETE ALL ");
            Log.e("DB WORKER", e.toString());
            throw new Exception ("Error : " + e);
        }finally
        {
            db.endTransaction(); // 트랜잭션 종료
        }

    }
    public Cursor getAllMethod(SQLiteDatabase db, String sql)
    {

        Log.e("DB WORKER ", "START GET ALL LIST BY SQL");
        return db.rawQuery(sql, null);
    }

    public Cursor getAllMethod(SQLiteDatabase db)
    {
        Log.d("DB WORKER ", "START GET ALL LIST BY FUNC");
        return db.query("TB_MEMO_MEDIA",
                new String[] {"_id","FileName", "FilePath", "FileType", "RecDate", "TextDesc"},
                null, null, null, null, null);
    }


    public Cursor getDetailMethod(SQLiteDatabase db, String sql)
    {
        if(db.rawQuery(sql, null).getCount()==0 || !db.rawQuery(sql, null).moveToFirst())
        {
            Log.e("DB WORKER ", "ERROR OF GET ALL DB DATA ~~!!!!! ");
            throw new SQLException();
        }
        return db.rawQuery(sql, null);
    }


    public void updateMethod(SQLiteDatabase db, String sql) throws Exception
    {
        db.beginTransaction(); //트랜잭션 시작

        try
        {
            db.execSQL(sql);
            db.setTransactionSuccessful(); // 트랜잭션 성공 (COMMIT)
        }catch(Exception e)
        {
            Log.e("DB WORKER", " ERROR OF UPDATE ");
            Log.e("DB WORKER", e.toString());
            throw new Exception ("Error : " + e);
        }finally
        {
            db.endTransaction(); // 트랜잭션 종료
        }

    }





}

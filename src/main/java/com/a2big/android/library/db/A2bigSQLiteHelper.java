package com.a2big.android.library.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2big on 16. 2. 22..
 */
public class A2bigSQLiteHelper {
    private Context mContext;
    private SQLiteDatabase db = null;
    private DBWorker wk = null;
    private Cursor mCursor;

    //ASSET FOLDER
    private final String DB_FILE_IN_ASSET  = "db/addr.db";

    //INSIDE DATABASE
    private final int DATABASE_VERSION = 1;
    private final String DATABASE_NAME  = "DataBase.db";
    private  String TABLE_NAME;

    private final String READ_DB = "read";
    private final String WRITE_DB = "write";


    private String mPackageName;
    public A2bigSQLiteHelper(Context context, String tableName, String packageName) {
        mContext = context;
        TABLE_NAME = tableName;
        //mPackageName = context.getPackageName();
        mPackageName = packageName;

        File file = new File("/data/data/" + mPackageName +"/databases/DataBase.db");
        if (!file.isFile()) {
            Log.e("A2bigSQLiteHelper","Copying datafile from assets");
            copyDbFile();
        }

        init();
    }

    private void init(){
        wk = new DBWorker(mContext, DATABASE_NAME, TABLE_NAME, DATABASE_VERSION);

        //insert("aaa","bbb","cccc");
       // select();

    }


    private String[] word;
    private String[] path;

    public List<String>  select(String addrSiCode, String addrKuCode)
    {
        List<String> arraylist = new ArrayList<String>();

        try
        {
            db = wk.open(READ_DB);
            String sql =
                    "SELECT STR_DONG  FROM " +
                            TABLE_NAME + " WHERE SI_CODE = " + addrSiCode + " AND KU_CODE = "+ addrKuCode + " ORDER BY ID";
            Log.e("[DEBUG] ", sql);

            mCursor =wk.getAllMethod(db,sql);
            Log.e("[DEBUG] cursor ", mCursor.toString());

            word = new String[mCursor.getCount()];
            if(mCursor.getCount()==0)
            {
                Log.e("[DEBUG] ","Nothing Data in DB ");
            }else
            {
                word = new String[mCursor.getCount()];
                path = new String[mCursor.getCount()];
                for( int index=0; index < mCursor.getCount(); index++)
                {
                    mCursor.moveToPosition(index);
                    String Path=  mCursor.getString(mCursor.getColumnIndexOrThrow("STR_DONG")).trim();
                    Log.e("JH","get>>>>"+Path);
                    arraylist.add(Path);
                }
            }
            wk.close(db);
            Log.e("[DEBUG] select  ", "end");

        }catch(SQLException e)
        {
            Log.e("TEXT MEMO", "ERROR OF MAKE LIST ");
            Log.e("TEXT MEMO", e.toString());
        }catch(Exception e)
        {
            Log.e("TEXT MEMO", "ERROR OF MAKE LIST ");
            Log.e("TEXT MEMO", e.toString());
        }

        wk = null;

        return arraylist;
    }

    public void insert(String Word, String Mean, String Syn ) {
        try {
            String date = String.valueOf(System.currentTimeMillis());

            String sql = " INSERT INTO " + TABLE_NAME + " ( ";
            sql += " WMEAN, WNAME, PATH,SOUND_INDEX ";
            sql += " ) VALUES ( ";
            sql += " ' " + Word + "' , '" + Mean + "' , '" + Syn + "' , 0)";

            Log.e("insert...", sql);

            db = wk.open(WRITE_DB);
            wk.insertMethod(db, sql);
            wk.close(db);
        } catch (Exception e) {
            Log.e("TEXT MEMO", "ERROR OF INSERT TEXT");
            Log.e("TEXT MEMO", e.toString());
        }
    }



    // copy database file from assets folder
    public void copyDbFile() {

        // AssetManager am = getAssets();
        AssetManager am =  mContext.getApplicationContext().getAssets();


        File directory = new File(Environment.getDataDirectory()+"/data/" + mPackageName +"/databases");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File toFile = new File(Environment.getDataDirectory()+"/data/" + mPackageName +"/databases/DataBase.db");

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;

        try {
            InputStream is = am.open(DB_FILE_IN_ASSET);
            bis = new BufferedInputStream(is);

            // 만약에 파일이 있다면 지우고 다시 생성
            if (toFile.exists()) {
                toFile.delete();
                toFile.createNewFile();
            } else {
                toFile.createNewFile();
            }
            fos = new FileOutputStream(toFile);
            bos = new BufferedOutputStream(fos);

            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }
            bos.flush();

            fos.close();
            bos.close();
            bis.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JH","Error "+e.toString());
        }
    }

}

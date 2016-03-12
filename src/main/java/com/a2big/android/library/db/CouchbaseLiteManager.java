package com.a2big.android.library.db;

/**
 * Created by a2big on 16. 3. 11..
 */

import com.a2big.android.library.utils.DevLog;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;

public class CouchbaseLiteManager {
    private Manager mManager;
    private Database mDatabase;

    public CouchbaseLiteManager(Manager pManager) {
        mManager = pManager;

        try {
            startCBLite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startCBLite() throws Exception {
        createReservation();
    }


    public Database getDatabase() {
        return mDatabase;
    }

    private void createReservation() {
        if(Manager.isValidDatabaseName(DBConstants.TABLE_NAME_RESERVATION) == false) {
            DevLog.defaultLogging("Bad reservation database name");
            return;
        }

        try {
            mDatabase = mManager.getDatabase(DBConstants.TABLE_NAME_RESERVATION);
        } catch(CouchbaseLiteException e) {
            DevLog.defaultLogging("Error: " + e.toString());
        }
    }

}

package org.nirbo.smsmanipulator.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.model.Target;

import java.io.File;
import java.sql.SQLException;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "SMSManipulator.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Target, Integer> targetDao = null;
    private RuntimeExceptionDao<Target, Integer> targetRuntimeDao = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    public DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, File configFile) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Target.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Target.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Target, Integer> getTargetDao() throws SQLException {
        if (targetDao == null) {
            targetDao = getDao(Target.class);
        }

        return targetDao;
    }

    public RuntimeExceptionDao<Target, Integer> getTargetExceptionDao() {
        if (targetRuntimeDao == null) {
            targetRuntimeDao = getRuntimeExceptionDao(Target.class);
        }

        return targetRuntimeDao;
    }


}

package org.nirbo.smsmanipulator.dao;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final String ORM_CONFIG_FILE = "ormlite_config.txt";

    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile(ORM_CONFIG_FILE);
    }

}

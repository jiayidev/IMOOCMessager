package net.brian.italker.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by with_you on 2017/7/23.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;
}

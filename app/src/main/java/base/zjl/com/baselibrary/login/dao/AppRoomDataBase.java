package base.zjl.com.baselibrary.login.dao;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import base.zjl.com.baselibrary.login.MyApplication;
import base.zjl.com.baselibrary.login.bean.StudentEntry;

/**
 * @author: zjl on 2018-8-4.
 * Class:
 */
@Database(entities = {StudentEntry.class}, version = 1, exportSchema = false)
public abstract class AppRoomDataBase extends RoomDatabase {
    private static volatile AppRoomDataBase mMyRoomDataBase;

    public static AppRoomDataBase getDataBaseInstance() {
        if (mMyRoomDataBase == null) {
            synchronized (AppRoomDataBase.class) {
                if (mMyRoomDataBase == null) {
                    mMyRoomDataBase = Room.databaseBuilder(MyApplication.getApplication().getApplicationContext(), AppRoomDataBase.class, "datasource.db")
                            .addMigrations()//迁移数据库使用
                            .allowMainThreadQueries()//允许在主线程查询数据
                            .fallbackToDestructiveMigration()//迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                            .build();
                }

            }
        }
        return mMyRoomDataBase;
    }

    public abstract StudentDao getStudentDao();
}

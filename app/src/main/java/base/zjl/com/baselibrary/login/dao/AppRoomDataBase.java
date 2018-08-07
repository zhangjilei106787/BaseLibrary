package base.zjl.com.baselibrary.login.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import base.zjl.com.baselibrary.login.bean.StudentEntry;

/**
 * @author: zjl on 2018-8-4.
 * Class:
 */
@Database(entities = {StudentEntry.class},version = 1,exportSchema = false)
public abstract class AppRoomDataBase extends RoomDatabase {

   public abstract  StudentDao getStudentDao();
}

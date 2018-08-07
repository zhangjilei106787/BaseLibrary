package base.zjl.com.baselibrary.login.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import base.zjl.com.baselibrary.login.bean.StudentEntry;
import io.reactivex.Flowable;

@Dao
public interface StudentDao {

    @Query("select * from studententry")
    List<StudentEntry> getAll();
    //配合livedata使用  直接更新ui
    @Query("select * from studententry")
    LiveData<List<StudentEntry>> getAll1();
    //配合rxjava使用
    @Query("select * from studententry")
    Flowable<List<StudentEntry>> getAll2();//背压情况使用  其他不适用 影响性能

    @Query("SELECT * FROM StudentEntry WHERE id IN (:ids)")
    List<StudentEntry> getAllByIds(long[] ids);

    @Insert(onConflict = OnConflictStrategy.REPLACE)//冲突替换旧数据
    void insert(StudentEntry... entities);

    @Delete
    void delete(StudentEntry entity);

    @Update
    void update(StudentEntry entity);
}
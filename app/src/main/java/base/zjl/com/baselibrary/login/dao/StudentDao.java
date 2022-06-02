package base.zjl.com.baselibrary.login.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM StudentEntry WHERE id IN (:ids)")
    List<StudentEntry> getAllByIds(long[] ids);

    @Insert(onConflict = OnConflictStrategy.REPLACE)//冲突替换旧数据
    void insert(StudentEntry... entities);

    @Delete
    void delete(StudentEntry entity);

    @Update
    void update(StudentEntry entity);
}
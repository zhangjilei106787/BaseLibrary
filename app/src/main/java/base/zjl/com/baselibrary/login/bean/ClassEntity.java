package base.zjl.com.baselibrary.login.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ClassEntity")
public class ClassEntity {

    @PrimaryKey
    private long id;


}
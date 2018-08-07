package base.zjl.com.baselibrary.login.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author: zjl on 2018-8-4.
 * Class:
 */
//indices = {@Index(value = "sex",unique = true)},
//        foreignKeys = {@ForeignKey(entity=ClassEntity.class,parentColumns = "id",childColumns ="class_id")}
@Entity(tableName = "StudentEntry"
        )
//索引根据需求添加
public class StudentEntry {

    @PrimaryKey //定义主键
    private long id;
    @ColumnInfo(name = "name")//定义数据表中的字段名   默认不加  是字段名
    private String name;
    @ColumnInfo(name = "sex")
    private int sex;
    @Ignore//指示Room需要忽略的字段或方法
    private String ignoreText;
    @ColumnInfo(name = "class_id")
    private String class_id;

    //@Embedded 嵌入其他对象
    //@Embedded ()如果一个实体具有相同类型的多个内嵌字段
//@Embedded(prefix = "one")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIgnoreText() {
        return ignoreText;
    }

    public void setIgnoreText(String ignoreText) {
        this.ignoreText = ignoreText;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
}

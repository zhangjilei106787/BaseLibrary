package base.zjl.com.baselibrary.login.bean;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author: zjl on 2018-8-4.
 * Class:
 */
@Entity(tableName = "StudentEntry")
//索引根据需求添加
public class StudentEntry {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "name")//定义数据表中的字段名   默认不加  是字段名
    private String name;
    @ColumnInfo(name = "sex")
    private int sex;
    @Ignore//指示Room需要忽略的字段或方法
    private String ignoreText;
    @ColumnInfo(name = "class_id")
    private String class_id;

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

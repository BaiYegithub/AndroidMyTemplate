package app.vp.cn.data.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * author : by
 * date: 2019/4/15 0015  下午 6:42.
 * describe
 */
@Entity
public class Actor {

    @PrimaryKey
    public int id;

    public String firstName;
    public String lastName;

    @Ignore
    Bitmap picture;
}

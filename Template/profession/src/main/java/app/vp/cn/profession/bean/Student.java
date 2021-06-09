package app.vp.cn.profession.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by baiye
 * Date: 2021/3/4
 * Time: 15:52
 * Description:
 */
class Student implements Parcelable {

    private short age;
    private String name;
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);

    }
}

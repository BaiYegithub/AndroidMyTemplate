package app.vp.cn.system.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author : by
 * date: 2019/2/25 0025  上午 10:30.
 * describe
 */

public class Person implements Parcelable {

    private String mName;

    public Person(String mName) {
        this.mName = mName;
    }


    protected Person(Parcel in) {
        mName = in.readString(); //这个是一定要写的
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                '}';
    }
}

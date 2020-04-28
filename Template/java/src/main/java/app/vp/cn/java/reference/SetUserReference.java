package app.vp.cn.java.reference;

import java.lang.ref.WeakReference;

import app.vp.cn.java.bean.User;

/**
 * Created by baiye
 * Date: 2020/4/8
 * Time: 16:53
 * Description:
 */
public class SetUserReference {

    public SetUserReference(WeakReference<User> weakReference) {
        User user = weakReference.get();
        System.out.println("user1是" + user.getUname());
    }
}

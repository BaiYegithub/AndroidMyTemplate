package app.vp.cn.java.reference;

import app.vp.cn.java.bean.User;

/**
 * Created by baiye
 * Date: 2020/4/8
 * Time: 16:36
 * Description:
 */
public class SetUser {
    User user;

    public SetUser(User user) {
        this.user = user;
        System.out.println("user1是"+this.user.getUname());
    }

    public void logUser(){
        System.out.println("user1是"+this.user.getUname());
    }


}

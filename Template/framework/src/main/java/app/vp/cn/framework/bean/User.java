package app.vp.cn.framework.bean;

/**
 * author : by
 * date: 2019/1/30 0030  下午 6:47.
 * describe
 */

public class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

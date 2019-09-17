package app.vp.cn.java.bean;

/**
 * Created by baiye
 * Date: 2019/9/17
 * Time: 16:15
 * Description:
 */
public class BaseBean<T> {
    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

package app.vp.cn.framework.bean;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * author : by
 * date: 2019/1/30 0030  下午 5:02.
 * describe UserModel  类
 */

public class UserModel extends ViewModel {

    MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public void setUser(User user) {
        userMutableLiveData.setValue(user);
    }

    public MutableLiveData<User> getMutable() {
        return userMutableLiveData;
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared() 在此进行资源清理操作
    @Override
    protected void onCleared() {
        super.onCleared();

    }

}

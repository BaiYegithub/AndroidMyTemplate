package app.vp.cn.framework.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.vp.cn.framework.R;
import app.vp.cn.framework.bean.User;
import app.vp.cn.framework.bean.UserModel;

/**
 * author : by
 * date: 2019/1/31 0031  下午 3:15.
 * describe
 */

public class TopFragment extends Fragment {

    private TextView tvFragOne;
    private UserModel userModel;

    //这是一个回调在主线程的looper  ()  只是一个demo
    Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_one, container, false);
        tvFragOne = view.findViewById(R.id.tv_fragOne);

        //如果是在同一个Activity中进行传值，这里的第一个参数，必须是getActivity,不能是this
        userModel = ViewModelProviders.of(getActivity()).get(UserModel.class);

        userModel.getMutable().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                tvFragOne.setText(user.getUserName());
            }
        });

        tvFragOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userModel.getMutable().postValue(new User("小明"));
            }
        });
        return view;
    }
}

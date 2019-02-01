package app.vp.cn.framework.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.vp.cn.common.util.LogUtil;
import app.vp.cn.framework.R;
import app.vp.cn.framework.bean.User;
import app.vp.cn.framework.bean.UserModel;

/**
 * author : by
 * date: 2019/1/31 0031  下午 3:15.
 * describe
 */

public class BottomFragment extends Fragment {

    private TextView tvFragTwo;
    private UserModel userModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_two, container, false);
        tvFragTwo = view.findViewById(R.id.tv_fragtwo);

        userModel = ViewModelProviders.of(getActivity()).get(UserModel.class);
        userModel.getMutable().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                LogUtil.i("user", "userName 是" + user.getUserName());
                tvFragTwo.setText(user.getUserName());
            }
        });

        tvFragTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}

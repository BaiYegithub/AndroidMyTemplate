package app.vp.cn.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

import java.util.List;

import app.vp.cn.common.app.BaseApp;
import app.vp.cn.ui.R;
import app.vp.cn.ui.bean.AlarmsInfo;
import app.vp.cn.ui.utils.DeviceUtil;

/**
 * Created by baiye
 * Date: 2020/6/17
 * Time: 10:01
 * Description:
 */
public class AutoScrollAlarmsView extends ViewFlipper {

    public AutoScrollAlarmsView(Context context) {
        super(context);
    }

    public AutoScrollAlarmsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void addAlarmsInfoList(List<AlarmsInfo> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            MarqueeText marqueeText = new MarqueeText(getContext());
            marqueeText.setTextSize(DeviceUtil.dip2px(BaseApp.getmContext(),12));
            marqueeText.setTextColor(getContext().getResources().getColor(R.color.white));
            marqueeText.setTag(i);
            addView(marqueeText);
        }

        getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}

package app.vp.cn.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.AttributeSet;
import android.widget.RemoteViews;
import android.widget.TextView;

import app.vp.cn.common.app.BaseApp;
import app.vp.cn.ui.R;

/**
 * Created by baiye
 * Date: 2019/6/25
 * Time: 16:53
 * Description:
 */
@RemoteViews.RemoteView
public class ClockTextView extends TextView {
    
    public ClockTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = ResourcesCompat.getFont(context, R.font.clock_text);
        setTypeface(typeface);
    }
  /*  public ClockTextView(Context context) {
        super(context);

        // 字体文件必须是true type font的格式(ttf)；
        // 当使用外部字体却又发现字体没有变化的时候(以 Droid Sans代替)，通常是因为
        // 这个字体android没有支持,而非你的程序发生了错误


    }*/
}

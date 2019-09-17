package app.vp.cn.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by baiye
 * Date: 2019/8/22
 * Time: 14:49
 * Description:
 *
 * @author lenovo
 */
public class PathView extends View {

    private Paint paint;
    private Path path;

    public PathView(Context context) {
        super(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addArc(200, 200, 400, 400, -225, 225);
            path.arcTo(400, 200, 600, 400, -180, 225, false);
            path.lineTo(400, 542);
        }*/
        paint.setStyle(Paint.Style.STROKE);
//        path.moveTo(0,0);
        path.lineTo(100, 100);
        path.arcTo(100, 100, 300, 300, -90, 90, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}

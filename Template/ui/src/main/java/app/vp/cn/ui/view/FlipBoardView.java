package app.vp.cn.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import app.vp.cn.ui.R;

/**
 * Created by baiye
 * Date: 2021/8/12
 * Time: 14:03
 * Description:
 */
public class FlipBoardView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;

    private int degreeY;
    private int fixDegreeY;
    private int degreeZ;
    private Camera camera;

    public FlipBoardView(Context context) {
        super(context);
    }

    public FlipBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlipBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flipboard);
        camera = new Camera();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        camera.setLocation(0, 0, newZ);
    }

    public void setDegreeY(int degreeY) {
        this.degreeY = degreeY;
        invalidate();
    }

    public void setFixDegreeY(int fixDegreeY) {
        this.fixDegreeY = fixDegreeY;
        invalidate();
    }

    public void setDegreeZ(int degreeZ) {
        this.degreeZ = degreeZ;
        invalidate();
    }

    public void reset() {
        degreeY = 0;
        degreeZ = 0;
        fixDegreeY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - bitmap.getWidth() / 2;
        int y = centerY - bitmap.getHeight() / 2;
        //先画右半部分
        canvas.save();
        camera.save();
        //回旋并移动到原来的位置
        canvas.translate(centerX, centerY);
        canvas.rotate(-degreeZ);
        //翻转并裁剪
        camera.rotateY(degreeY);
        camera.applyToCanvas(canvas);
        canvas.clipRect(0, -centerY, centerX, centerY);
        //先移动到原点 并 旋转
        canvas.rotate(degreeZ);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();

        canvas.save();
        camera.save();
        canvas.translate(centerX,centerY);
        canvas.rotate(-degreeZ);
        camera.rotateY(fixDegreeY);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-centerX,-centerY,0,centerY);
        canvas.rotate(degreeZ);
        canvas.translate(-centerX,-centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();

    }
}

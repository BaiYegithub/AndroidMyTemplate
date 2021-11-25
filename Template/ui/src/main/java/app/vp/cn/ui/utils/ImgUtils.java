package app.vp.cn.ui.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by baiye
 * Date: 2021/9/27
 * Time: 14:01
 * Description:
 */
public class ImgUtils {
    /**
     * 调整图片的色相，饱和度，灰度
     *
     * @param srcBitmap
     * @param rotate
     * @param saturation
     * @param scale
     * @return
     */
    public static Bitmap changeImageTheme(Bitmap srcBitmap, float rotate, float saturation, float scale) {

        //调整色相
        ColorMatrix rotateMatrix = new ColorMatrix();
        rotateMatrix.setRotate(0, rotate);
        rotateMatrix.setRotate(1, rotate);
        rotateMatrix.setRotate(2, rotate);

        //调整色彩饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        //调整灰度
        ColorMatrix scaleMatrix = new ColorMatrix();
        scaleMatrix.setScale(scale, scale, scale, 1);

        //叠加效果
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.postConcat(rotateMatrix);
        colorMatrix.postConcat(saturationMatrix);
        colorMatrix.postConcat(scaleMatrix);

        //创建一个大小相同的空白Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //载入Canvas,Paint
        Canvas canvas = new Canvas(dstBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //绘图
        canvas.drawBitmap(srcBitmap, 0, 0, paint);

        return dstBitmap;
    }
}

package app.vp.cn.common.util;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import app.vp.cn.common.app.BaseApp;


/**
 * @author Create by cyx 2018/11/28 0028.
 *         describe:Glide框架的常规操作封装
 */

public class GlideUtil {
    /**
     * 使图片平铺控件
     *
     * @param imageView     需要添加图片的控件
     * @param picUrl        图片地址
     * @param defaultPicUrl 默认图片地址
     */
    public static void setImageTiling(ImageView imageView, String picUrl, int defaultPicUrl) {
        if (defaultPicUrl != 0) {
            Glide.with(BaseApp.getmContext()).load(picUrl).apply(new RequestOptions().placeholder(defaultPicUrl)).into(imageView);
        } else {
            Glide.with(BaseApp.getmContext()).load(picUrl).into(imageView);
        }
    }

    /**
     * 使图片变成圆形
     *
     * @param imageView     需要添加图片的控件
     * @param picUrl        图片地址
     * @param defaultPicUrl 默认图片地址
     */
    public static void setImageCircle(ImageView imageView, String picUrl, int defaultPicUrl) {
        if (defaultPicUrl != 0) {
            Glide.with(BaseApp.getmContext()).load(picUrl).apply(new RequestOptions().placeholder(defaultPicUrl).transform(new CircleCrop())).into(imageView);
        } else {
            Glide.with(BaseApp.getmContext()).load(picUrl).apply(new RequestOptions().centerCrop().transform(new CircleCrop())).into(imageView);
        }
    }

    /**
     * 使图片加圆角
     *
     * @param imageView     需要添加图片的控件
     * @param picUrl        图片地址
     * @param defaultPicUrl 默认图片地址
     * @param angel         角度
     */
    /*public static void setImageAngle(ImageView imageView, String picUrl, int defaultPicUrl, int angel) {
        if (defaultPicUrl != 0) {
            Glide.with(BaseApp.getmContext()).load(picUrl).apply(new RequestOptions().placeholder(defaultPicUrl).transform(new GlideRoundTransform(BaseApp
                    .getmContext(), angel))).into(imageView);
        } else {
            Glide.with(BaseApp.getmContext()).load(picUrl).apply(new RequestOptions().transform(new GlideRoundTransform(BaseApp.getmContext(), angel))).into(imageView);
        }
        *//*
                //设置图片圆角角度
                RoundedCorners roundedCorners= new RoundedCorners(12);
                //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
                RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300,300);
                Glide.with(BaseApp.getmContext()).load(picUrl).apply(options).into(imageView);
        *//*
    }*/

    /**
     * @param imageView     图片控件
     * @param uri           图片的uri
     * @param defaultPicUrl 默认图片
     */
    public static void setImageUriTiling(ImageView imageView, Uri uri, int defaultPicUrl) {
        if (defaultPicUrl != 0) {
            Glide.with(BaseApp.getmContext()).load(uri).apply(new RequestOptions().placeholder(defaultPicUrl)).into(imageView);
        } else {
            Glide.with(BaseApp.getmContext()).load(uri).into(imageView);
        }
    }
}

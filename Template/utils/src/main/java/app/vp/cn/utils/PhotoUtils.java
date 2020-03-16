package app.vp.cn.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import app.vp.cn.common.app.BaseApp;

/**
 * @author baiye
 * @time 2019.6.25
 * @description 图片压缩处理的工具类
 */
public class PhotoUtils {

    /**
     * @param activity
     * @param uri
     * @return 通过uri获取文件的绝对路径
     */
    public static String getRealFilePath(Activity activity, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = activity.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    //压缩处理图片
    public static String compressImage(String filePath, String fileName) {
        //先获取图片被旋转的角度
        int angle = readPictureDegree(filePath);
        //获取一定尺寸的图片
        Bitmap bm = getSmallBitmap(filePath);
        //修复图片被旋转的角度
        bm = rotatingImageView(angle, bm);
        File outputFile = null;
        if (TextUtils.isEmpty(fileName)) {//如果fileName 是空的话，则使用当前时间作为图片名称，否则使用fileName
            outputFile = new File(getSystemFilePath(BaseApp.getmContext()), System.currentTimeMillis() + ".png");
        } else {
            outputFile = new File(getSystemFilePath(BaseApp.getmContext()), fileName + ".png");
        }
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
            } else {
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
        }
        return outputFile.getPath();
    }

    public static String saveBitmap(Bitmap bitmap, String filePath, String fileName) {
        //保存剪切后的图片
        File outputFile = null;
        if (TextUtils.isEmpty(fileName)) {//如果fileName 是空的话，则使用当前时间作为图片名称，否则使用fileName
            outputFile = new File(TextUtils.isEmpty(filePath) ? getSystemFilePath(BaseApp.getmContext()) : filePath, System.currentTimeMillis() + ".png");
        } else {
            outputFile = new File(TextUtils.isEmpty(filePath) ? getSystemFilePath(BaseApp.getmContext()) : filePath, fileName + ".png");
        }
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
            } else {
                outputFile.delete();
            }
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile.getPath();
    }

    /**
     * 根据路径获得图片信息并按比例压缩，返回bitmap
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        // 计算缩放比
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 读取照片旋转角度
     *
     * @param path 照片路径
     * @return 角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle  被旋转角度
     * @param bitmap 图片对象
     * @return 旋转后的图片
     */
    public static Bitmap rotatingImageView(int angle, Bitmap bitmap) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bitmap;
        }
        if (bitmap != returnBm) {
            bitmap.recycle();
        }
        return returnBm;
    }

    /**
     * 更改bitmap 的宽高
     *
     * @param filePath 文件的全路径 包含文件名
     * @return
     */
    public static Bitmap changeBitmapSize(String filePath, int newWidth, int newHeight) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(filePath);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            //计算缩放的比率
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            //获取想要缩放的matrix
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);

            //获取新的bitmap
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 更改bitmap 的宽高
     *
     * @param resourceId drawable 文件夹文件目录下的id
     * @return
     */
    public static Bitmap changeBitmapSize(int resourceId, int newWidth, int newHeight) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeResource(BaseApp.getmContext().getResources(), resourceId);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            //计算压缩的比率
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            //获取想要缩放的matrix
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);

            //获取新的bitmap
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * @param bitmap  原 bitmap
     * @param roundPx 圆角角度
     * @param framePx 边框宽度
     * @return
     */
    public static Bitmap drawBackGround(Bitmap bitmap, int roundPx, int framePx) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        //创建画板
        Canvas canvas = new Canvas(newBitmap);
        //画板drawARGB必须与Bitmap的Config一致
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //左上右下位置
        Rect rect = new Rect(framePx / 2, framePx / 2, bitmap.getWidth() - framePx / 2, bitmap.getHeight() - framePx / 2);
        RectF rectF = new RectF(rect);
        //RectF对象,X矩形圆角大小,Y矩形圆角大小,画笔
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        //设置两张图片相交时的模式为Mode.SRC_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //创建画边框的画笔
        Paint paintFrame = new Paint();
        paintFrame.setAntiAlias(true);
        //设置空心画笔
        paintFrame.setStyle(Paint.Style.STROKE);
        //设置边框颜色
        paintFrame.setColor(Color.WHITE);
        //设置边框宽度
        paintFrame.setStrokeWidth(framePx);
        //画圆角矩形边框
        canvas.drawRoundRect(rectF, roundPx, roundPx, paintFrame);
        return newBitmap;
    }


    private static File mPhotoFile = null;
    public static void setPhotoFile(File photoFile){
        mPhotoFile = photoFile;
    }

    public static File getPhotoFile(){

        return mPhotoFile;
    }


    /**
     * @param bmp 获取的bitmap数据
     * @param picName 自定义的图片名
     */
/*
    public static void saveBmp2Gallery(Context context,Bitmap bmp, String picName) {
//        saveImageToGallery(bmp,picName);
        String fileName = null;
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;
//                + File.separator + "yingtan" + File.separator;

//        String photoName = System.currentTimeMillis() + ".jpg";
        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;
        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, picName + ".jpg");
//            file = new File(galleryPath, photoName);
            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            if (null != outStream) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            }
        }catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                    setPhotoFile(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),fileName,null);
            MediaStore.Images.Media.insertImage(context.getContentResolver(),bmp,fileName,null);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
            Log.i("bai", "saveBmp2Gallery: 图片保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("bai", "saveBmp2Gallery: 图片保存失败");
        }


    }
*/
    public static void saveBmp2Gallery(Context context,Bitmap bmp,String picName){
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;
        File outPutFile = null;

        try {
            outPutFile =new File(galleryPath,picName+".jpg");
            if (!outPutFile.exists()) {
                outPutFile.getParentFile().mkdirs();
            } else {
                outPutFile.delete();
            }
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outPutFile));
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),fileName,null);
            MediaStore.Images.Media.insertImage(context.getContentResolver(),bmp,outPutFile.getName(),null);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(outPutFile);
            intent.setData(uri);
            context.sendBroadcast(intent);
            Log.i("bai", "saveBmp2Gallery: 图片保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("bai", "saveBmp2Gallery: 图片保存失败");
        }
    }


    public static final String SDPATH = Environment.getExternalStorageDirectory() + "/";


    public static String getSystemFilePath(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            if(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!=null){
                cachePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            }else {
                File file = new File(SDPATH +"自定义路径/");
                if(!file.exists()){
                    file.getParentFile().mkdirs();
                }
                cachePath = file.getPath();
            }
            //            cachePath = context.getExternalCacheDir().getPath();//也可以这么写，只是返回的路径不一样，具体打log看
        } else {
            if(context.getFilesDir()!=null){
                cachePath = context.getFilesDir().getAbsolutePath();
            }else {
                File file = new File(SDPATH + "自定义路径/");
                if(!file.exists()){
                    file.getParentFile().mkdirs();
                }
                cachePath = file.getPath();
            }

            //            cachePath = context.getCacheDir().getPath();//也可以这么写，只是返回的路径不一样，具体打log看
        }
        return cachePath;
    }
}

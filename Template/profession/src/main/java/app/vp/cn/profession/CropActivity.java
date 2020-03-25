package app.vp.cn.profession;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.common.util.UIUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class CropActivity extends BaseActivity {


    //拍照并裁剪
    @BindView(R.id.bt_take_photo)
    Button btTakePhoto;
    //imageView
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;

    private int PICK_PHOTO = 10;//打开相册的请求码
    private int PHOTO_REQUEST_CUT = 2; //进入切图的界面的请求码
    Uri uritempFile;

    @Override
    protected int initContentView() {
        return R.layout.activity_crop;
    }

    @Override
    protected void initViewAndData() {

    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }

    @OnClick(R.id.bt_take_photo)
    public void onViewClicked() {
        //读取相册  动态申请权限
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (checkPermissions(permissions)) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, PICK_PHOTO);
        } else {
            requestPermission(permissions, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (resultCode == RESULT_OK && requestCode == PHOTO_REQUEST_CUT) {
            try {
                if (data == null) {
                    UIUtils.showToast("图片加载失败");
                    return;
                }
                Bitmap bitmap;
                if (uritempFile != null) {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                } else {
                    bitmap = data.getParcelableExtra("data");
                }
                //保存剪切后的图片
                File file = new File(UIUtils.getStoragePath(this, false) + "/crop.png");
                //   File file = new File(UIUtils.getSDCardCachePATH("crop"));
                if (!file.exists()) {
                    file.createNewFile();
                }

                try {
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //BAI:2019/3/11 0011  获取到file 了 可以上传图片了
                Bitmap bitmap1 = BitmapFactory.decodeFile(file.getAbsolutePath());
                ivPhoto.setImageBitmap(bitmap1);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * @param uri 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 5);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        if (Build.MODEL.contains("MI")) {
            uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        }
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}

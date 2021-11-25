package app.vp.cn.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import app.vp.cn.ui.utils.ImgUtils;

public class ImageScaleActivity extends AppCompatActivity {

    private ImageView ivAcImage, ivAcImage2, ivAcImage3, ivAcImage4, ivAcImage5, ivAcImage6, ivAcImage7, ivAcImage8, ivAcImage9, ivAcImage10, ivAcImage11, ivAcImage12, ivAcImage13, ivAcImage14, ivAcImage15, ivAcImage16, ivAcImage17, ivAcImage18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale);
        ivAcImage = findViewById(R.id.iv_acImage);
        ivAcImage2 = findViewById(R.id.iv_acImage2);
        ivAcImage3 = findViewById(R.id.iv_acImage3);
        ivAcImage4 = findViewById(R.id.iv_acImage4);
        ivAcImage5 = findViewById(R.id.iv_acImage5);
        ivAcImage6 = findViewById(R.id.iv_acImage6);
        ivAcImage7 = findViewById(R.id.iv_acImage7);
        ivAcImage8 = findViewById(R.id.iv_acImage8);
        ivAcImage9 = findViewById(R.id.iv_acImage9);
        ivAcImage10 = findViewById(R.id.iv_acImage10);
        ivAcImage11 = findViewById(R.id.iv_acImage11);
        ivAcImage12 = findViewById(R.id.iv_acImage12);
        ivAcImage13 = findViewById(R.id.iv_acImage13);
        ivAcImage14 = findViewById(R.id.iv_acImage14);
        ivAcImage15 = findViewById(R.id.iv_acImage15);
        ivAcImage16 = findViewById(R.id.iv_acImage16);
        ivAcImage17 = findViewById(R.id.iv_acImage17);
        ivAcImage18 = findViewById(R.id.iv_acImage18);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ad_splash_tips_desc);
        ivAcImage.setImageBitmap(ImgUtils.changeImageTheme(bitmap, 0, 1, 1));
        ivAcImage2.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -10, 1, 2));
        ivAcImage3.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -20, 1, 3));
        ivAcImage4.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -30, 1, 4));
        ivAcImage5.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -40, 1, 5));
        ivAcImage6.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -50, 1, 6));
        ivAcImage7.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -60, 1, 7));
        ivAcImage8.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -70, 1, 8));
        ivAcImage9.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -80, 1, 9));
        ivAcImage10.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -90, 1, 10));
        ivAcImage11.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -100, 1, 11));
        ivAcImage12.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -110, 1, 12));
        ivAcImage13.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -120, 1, 13));
        ivAcImage14.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -130, 1, 14));
        ivAcImage15.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -140, 1, 15));
        ivAcImage16.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -150, 1, 16));
        ivAcImage17.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -160, 1, 17));
        ivAcImage18.setImageBitmap(ImgUtils.changeImageTheme(bitmap, -180, 1, 18));

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivAcImage, "scaleX", 0.8f, 1.0f);
        animatorX.setDuration(2000);
        animatorX.setRepeatMode(ValueAnimator.REVERSE);
        animatorX.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivAcImage, "scaleY", 0.8f, 1.0f);
        animatorY.setDuration(2000);
        animatorY.setRepeatMode(ValueAnimator.REVERSE);
        animatorY.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.start();

    }
}
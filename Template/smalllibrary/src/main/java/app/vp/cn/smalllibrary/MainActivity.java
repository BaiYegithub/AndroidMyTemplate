package app.vp.cn.smalllibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LottieAnimationView lottieAnimationView = findViewById(R.id.animation_view);
        lottieAnimationView.setProgress(0);
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
    }
}

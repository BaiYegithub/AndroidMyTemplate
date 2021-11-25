package app.vp.cn.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.vp.cn.ui.R;

public class FlipBoardActivity extends AppCompatActivity {

    private FlipBoardView flipBoardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_board);
        flipBoardView = findViewById(R.id.flip_board);

        ObjectAnimator degreeY = ObjectAnimator.ofInt(flipBoardView, "degreeY", 0,-45);
        degreeY.setDuration(1000);
        degreeY.setStartDelay(500);

        ObjectAnimator degreeZ = ObjectAnimator.ofInt(flipBoardView, "degreeZ", 0,270);
        degreeZ.setDuration(800);
        degreeZ.setStartDelay(500);

        ObjectAnimator fixDegreeY = ObjectAnimator.ofInt(flipBoardView, "fixDegreeY", 0,30);
        fixDegreeY.setDuration(500);
        fixDegreeY.setStartDelay(500);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flipBoardView.reset();
                animatorSet.setStartDelay(500);
                animatorSet.start();
            }
        });
        animatorSet.playSequentially(degreeY,degreeZ,fixDegreeY);
        animatorSet.start();
    }
}
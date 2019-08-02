package app.techsol.classtenamdemoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import app.techsol.classtenamdemoapp.R;
import me.wangyuwei.particleview.ParticleView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView image;
    ParticleView particleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image=findViewById(R.id.image);
        particleView=findViewById(R.id.particleView);

        YoYo.with(Techniques.BounceIn).duration(8000).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                final float scale = getBaseContext().getResources().getDisplayMetrics().density;
                int pixels = (int) (300 * scale + 0.5f);
                image.requestLayout();
                image.getLayoutParams().width=pixels;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).playOn(image);
        particleView.startAnim();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



            }

        },8000);

    }
}

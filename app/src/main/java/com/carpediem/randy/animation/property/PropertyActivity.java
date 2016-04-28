package com.carpediem.randy.animation.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.carpediem.randy.animation.R;

public class PropertyActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        findViewById(R.id.button).setOnClickListener(this);
    }
    private void rotateAnimRun(View view) {
        ObjectAnimator.ofFloat(view,"rotationX",0.0f,360.0f)
                    .setDuration(500)
                    .start();
    }
    private void rotate(final View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f,360f);
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setRotationX((Float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setAlpha(0.5f);
            }
        });
    }
    private void rotate2(final View view) {
    }

    private void rotate3(final View view) {
        Keyframe kf0 = Keyframe.ofFloat(0f,180f);
        Keyframe kf1 = Keyframe.ofFloat(200f,360f);
        Keyframe kf2 = Keyframe.ofFloat(360f,180f);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("rotationX",kf0,kf1,kf2);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,holder);
        animator.setDuration(500);
        animator.start();
    }
    private void rotate4(final View view) {
        if (view == null) {
            return;
        }
        view.animate().rotationX(40).setDuration(100).start();
    }
    @Override
    public void onClick(View v) {
        rotateAnimRun(findViewById(R.id.image));
    }
}
class MyEvaluator implements TypeEvaluator{
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        float startFloat = ((Number)startValue).floatValue();
        return startFloat + fraction*(((Number)endValue).floatValue()-startFloat);
    }
}
class MyInterpolator implements TimeInterpolator{
    @Override
    public float getInterpolation(float input) {
        return 0;
    }
}

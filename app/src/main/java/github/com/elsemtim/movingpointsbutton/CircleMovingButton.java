package github.com.elsemtim.movingpointsbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.UiThread;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

import github.com.elsemtim.movingpointsbutton.objects.Circle;

/**
 * @author: timur.mukhortov
 * date: 29.06.2018
 * time: 17:08
 * @LinkedIn: linkedin.com/in/timurmukhortov
 **/


public class CircleMovingButton extends AppCompatButton {
    private static final String TAG = CircleMovingButton.class.getSimpleName();
//    private final Handler handler;

//    private final Runnable dotAnimationRunnable;

    private Params bParams;
    private boolean isAnimation;
    private AnimatorSet animatorSet;

    private Paint paint;
    private float radius;
    private float maxRadius = 20;

    private float targetScale = 0.0F;
    private float scale = targetScale;

    private List<Circle> circleList;

    /**
     * @param context
     */
    public CircleMovingButton(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     */

    public CircleMovingButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CircleMovingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
//        this.handler = new Handler();
//        this.dotAnimationRunnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.i("test33", "start");
//            }
//        };
        init(context, attrs, defStyleAttr, defStyleAttr);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        bParams = new Params();
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleMovingButton,
                0, 0
        );

        /**
         *
         * Parcelable default values for our view.
         *
         **/
        bParams.bBackgroundColor = a.getColor(R.styleable.CircleMovingButton_backgroundColor, 0xff000000);


        final int strokeWidth = 40;

        //default radius circle
        radius = 10;

        circleList = new ArrayList<>();

        try {
            paint = new Paint();
            paint.setColor(bParams.bBackgroundColor);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(strokeWidth);
            circleList.add(new Circle(radius, paint));
            circleList.add(new Circle(radius, paint));
            circleList.add(new Circle(radius, paint));
        } finally {
            a.recycle();
        }
    }

    /**
     * Class with all the params to configure the button.
     */
    private class Params {
        private int bBackgroundColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        int width;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = 200;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = 200;
                break;
            default:
                width = 200;
        }

        int height;
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = 100;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = 100;
                break;
            default:
                height = 100;
        }

        float halfHeight = height / 2;

        float firstCircleX = width / 6;
        float secondCircleX = width / 2;
        float thirdCircleX = width - firstCircleX;

        //First circle
        circleList.get(0).setCy(halfHeight);
        circleList.get(0).setCx(firstCircleX);

        //Second circle
        circleList.get(1).setCy(halfHeight);
        circleList.get(1).setCx(secondCircleX);

        //Third circle
        circleList.get(2).setCy(halfHeight);
        circleList.get(2).setCx(thirdCircleX);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Circle circle : circleList) {
            circle.draw(canvas);
        }

    }


    public void startDotAnimation() {
        stopDotAnimation();
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();
            List<Animator> animations = new ArrayList<>(2);

            ValueAnimator growAnimator = ValueAnimator.ofFloat(targetScale, 1.0F);
            growAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scale = (float) animation.getAnimatedValue();
                    setRadius(scale);
                    invalidate();
                }
            });

            growAnimator.setDuration(1000);
            growAnimator.setInterpolator(new LinearInterpolator());
            growAnimator.setRepeatCount(ValueAnimator.INFINITE);
            animations.add(growAnimator);

            animatorSet.playTogether(animations);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    scale = targetScale;
                    setRadius(scale);
                    invalidate();
                }
            });
        }
        animatorSet.start();
    }


    public void stopDotAnimation() {
        if (isAnimating()) {
            animatorSet.cancel();
        }
    }

    public boolean isAnimating() {
        return animatorSet != null && animatorSet.isStarted();
    }

    public void setRadius(float angle) {
        float phase = (float) -0.2;
        for (int i = 0; i < circleList.size(); i++) {
            float radiusFactor = ((angle + phase * i) + 100) % 1;
            if (radiusFactor > 0.5) {
                radiusFactor = (float) (0.5 - (radiusFactor - 0.5));
            }
            radiusFactor *= 2;
            circleList.get(i).setRadius(maxRadius * (radiusFactor));
        }
    }
}

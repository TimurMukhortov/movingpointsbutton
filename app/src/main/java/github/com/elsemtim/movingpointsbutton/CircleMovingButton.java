package github.com.elsemtim.movingpointsbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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

    private enum State {
        PROGRESS, IDLE, DONE, STOPED
    }

    private State bState;
    private Params bParams;

    private Paint paint;
    private float radius;
    private float maxRadius;

    private List<Circle> circleList;

    /**
     * @param context
     */
    public CircleMovingButton(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    /**
     * @param context
     * @param attrs
     */

    public CircleMovingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        int backgroundColor = a.getColor(R.styleable.CircleMovingButton_backgroundColor, 0xff000000);


        final int strokeWidth = 40;

        //default radius circle
        radius = 10;

        circleList = new ArrayList<>();

        try {
            paint = new Paint();
            paint.setColor(backgroundColor);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(strokeWidth);
            circleList.add(new Circle(250, 500, radius, paint));
            circleList.add(new Circle(500, 500, radius, paint));
            circleList.add(new Circle(750, 500, radius, paint));
        } finally {
            a.recycle();
        }
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CircleMovingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, 0, 0);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        bParams = new Params();
    }

    /**
     * Class with all the params to configure the button.
     */
    private class Params{
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
        float thirdCircleX = secondCircleX + firstCircleX;

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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        Log.i("test", "radius = " + radius);
        if (radius <= maxRadius / 3) {
            circleList.get(0).setRadius(radius);
        } else {
            if (radius <= (maxRadius / 3) * 2) {
                circleList.get(1).setRadius(radius / 2);
            } else {
                if (radius <= maxRadius)
                    circleList.get(2).setRadius(radius / 3);
            }
        }
        this.radius = radius;
    }

    public float getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(float maxRadius) {
        this.maxRadius = maxRadius;
    }
}

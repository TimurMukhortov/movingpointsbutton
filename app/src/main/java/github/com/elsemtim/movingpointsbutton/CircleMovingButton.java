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

    private final Paint paint;
    private float radius;
    private float maxRadius;

    private List<Circle> circleList;

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
                width = 100;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = 100;
                break;
            default:
                width = 100;
        }

        int height;
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = 50;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = 50;
                break;
            default:
                height = 50;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Circle circle : circleList){
            circle.draw(canvas);
        }

    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
            Log.i("test", "radius = "+radius);
        if (radius <= maxRadius / 3) {
            circleList.get(0).setRadius(radius);
        } else {
            if (radius <= (maxRadius / 3) * 2) {
                circleList.get(1).setRadius(radius/2);
            } else {
                if (radius <= maxRadius)
                circleList.get(2).setRadius(radius/3);
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

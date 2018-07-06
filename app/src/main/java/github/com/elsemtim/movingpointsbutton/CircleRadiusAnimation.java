package github.com.elsemtim.movingpointsbutton;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author: timur.mukhortov
 * date: 29.06.2018
 * time: 17:10
 * @LinkedIn: linkedin.com/in/timurmukhortov
 **/


public class CircleRadiusAnimation extends Animation {

    private CircleMovingButton circleMovingButton;

    private float oldRadius;
    private float newRadius;

    public CircleRadiusAnimation(CircleMovingButton circleMovingButton, int newRadius) {
        this.oldRadius = circleMovingButton.getRadius();
        this.newRadius = newRadius;
        this.circleMovingButton = circleMovingButton;
        circleMovingButton.setMaxRadius(newRadius);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
//        float radius = oldRadius + ((newRadius - oldRadius) * interpolatedTime);
        Log.i("Test1", "time: " + interpolatedTime);
//        float doubleInterpolatedTime = interpolatedTime * 2;
//        if (doubleInterpolatedTime > 1) {
//            doubleInterpolatedTime = 1 - (doubleInterpolatedTime - 1);
//
//        }
        circleMovingButton.setRadius(interpolatedTime);
        circleMovingButton.requestLayout();
    }
}
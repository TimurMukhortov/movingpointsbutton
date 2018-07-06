package github.com.elsemtim.movingpointsbutton;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * @author: timur.mukhortov
 * date: 29.06.2018
 * time: 17:10
 * @LinkedIn: linkedin.com/in/timurmukhortov
 **/


public class CircleRadiusAnimation extends Animation {

    private CircleMovingButton circleMovingButton;

    public CircleRadiusAnimation(CircleMovingButton circleMovingButton, int newRadius) {
        this.circleMovingButton = circleMovingButton;
        circleMovingButton.setMaxRadius(newRadius);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        circleMovingButton.setRadius(interpolatedTime);
        circleMovingButton.requestLayout();
    }
}
package github.com.elsemtim.movingpointsbutton;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author: timur.mukhortov
 * date: 29.06.2018
 * time: 17:10
 * @LinkedIn: linkedin.com/in/timurmukhortov
 **/


public class CircleAngleAnimation extends Animation {

    private CircleMovingButton circleMovingButton;

    private float oldRadius;
    private float newRadius;

    public CircleAngleAnimation(CircleMovingButton circleMovingButton, int newRadius) {
        this.oldRadius = circleMovingButton.getRadius();
        this.newRadius = newRadius;
        this.circleMovingButton = circleMovingButton;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float radius = oldRadius + ((newRadius - oldRadius) * interpolatedTime);
        circleMovingButton.setRadius(radius);
        circleMovingButton.requestLayout();
    }
}
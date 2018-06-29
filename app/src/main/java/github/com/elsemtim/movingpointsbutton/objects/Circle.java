package github.com.elsemtim.movingpointsbutton.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author: timur.mukhortov
 * date: 29.06.2018
 * time: 20:32
 * @LinkedIn: linkedin.com/in/timurmukhortov
 **/


public class Circle implements BaseObjectInterface {

    private float cx, cy;
    private float radius;
    private Paint paint;

    public Circle(float cx, float cy, float radius, Paint paint){
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.paint = paint;
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, paint);
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}

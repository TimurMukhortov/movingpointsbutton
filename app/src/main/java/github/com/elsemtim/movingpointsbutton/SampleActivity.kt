package github.com.elsemtim.movingpointsbutton

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        val circle = findViewById<View>(R.id.circle) as Circle
        val animation = CircleAngleAnimation(circle, 360)
        animation.duration = 1000
        circle.startAnimation(animation)
    }
}

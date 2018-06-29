package github.com.elsemtim.movingpointsbutton

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {

    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        val animation = CircleAngleAnimation(circle, 260)
        animation.duration = 1000
        animation.repeatCount = Animation.INFINITE
        circle.setOnClickListener({
            if (!flag) {
                circle.startAnimation(animation)
                flag = !flag
            } else {
                circle.clearAnimation()
                flag = !flag
            }
        })
    }
}
package github.com.elsemtim.movingpointsbutton

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {

    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        val animation = CircleRadiusAnimation(circle, 100)
        animation.duration = 1000
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.REVERSE
        circle.setOnClickListener {
            flag = if (!flag) {
                circle.startAnimation(animation)
                !flag
            } else {
                circle.clearAnimation()
                !flag
            }
        }
    }
}
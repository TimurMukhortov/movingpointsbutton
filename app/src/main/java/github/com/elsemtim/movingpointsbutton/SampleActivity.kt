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
        circle.setOnClickListener {
            flag = if (!flag) {
                circle.startDotAnimation()
                !flag
            } else {
                circle.stopDotAnimation()
                !flag
            }
        }
    }
}
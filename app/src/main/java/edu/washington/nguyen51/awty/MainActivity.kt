package edu.washington.nguyen51.awty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startBool = true
        btnStartStop.isClickable = false
        btnStartStop.setText(setStartOrStopBtn(startBool))


    }
}


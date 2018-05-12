package edu.washington.nguyen51.awty

import android.app.Activity
import android.app.AlarmManager
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            getPermission()
        } else {
            startMain()
        }


    }

    fun getPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        startMain()
    }

    fun startMain() {
        var alarmManager = getSystemService(Activity.ALARM_SERVICE) as AlarmManager
        var startBool = true
        var ready = false
        btnStartStop.setText(setStartOrStopBtn(startBool))

        btnStartStop.setOnClickListener {
            var phone = ToPhoneNumber.text.toString()
            var message = Message.text.toString()
            var minutes = MinuteInterval.text.toString()

            // Handle start btn click when startBool = true
            // Handle stop btn click when startBool = false
            if (startBool) {
                // Check if clickable by making sure all fields are valid
                // Once they are valid switch the startBool boolean to get start/stop swap on btn text
                ready = checkInputFieldsAreValid(this, phone, message, minutes)
                if (ready) {
                    startBool = !startBool
                    var btnText = setStartOrStopBtn(startBool)
                    btnStartStop.setText(btnText)
                    // Run function that repeatedly sends toast messages for every x minute
                    toastMessages(startBool, phone, message, minutes, alarmManager, this)
                }
            } else {
                // End toast message function here
                startBool = !startBool
                var btnText = setStartOrStopBtn(startBool)
                btnStartStop.setText(btnText)
                toastMessages(startBool, phone, message, minutes, alarmManager, this)
            }
        }
    }
}


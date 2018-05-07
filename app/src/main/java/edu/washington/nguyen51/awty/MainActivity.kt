package edu.washington.nguyen51.awty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                ready = checkInputFieldsAreValid(phone, message, minutes)
                if (ready) {
                    startBool = !startBool
                    var btnText = setStartOrStopBtn(startBool)
                    btnStartStop.setText(btnText)
                    // Run function that repeatedly sends toast messages for every x minute

                }
            } else {
                // End toast message function here

            }
        }
    }

    override fun onResume() {
        super.onResume()
        ToPhoneNumber.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        Message.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        MinuteInterval.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}


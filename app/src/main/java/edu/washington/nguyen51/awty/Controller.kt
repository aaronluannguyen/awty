package edu.washington.nguyen51.awty

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.SystemClock
import android.widget.Toast

fun setStartOrStopBtn(start: Boolean): String {
    when (start) {
        true -> return "Start"
        false -> return "Stop"
    }
}

fun checkPhoneNumber(num: String, context: Context?): Boolean {
    if (num.length == 10) {
        return true
    }
    Toast.makeText(context, "Invalid Phone Number", Toast.LENGTH_LONG).show()
    return false
}

fun checkMessage(message: String, context: Context?): Boolean {
    if (message != "") {
        return true
    }
    Toast.makeText(context, "Invalid Message: Must not be empty", Toast.LENGTH_LONG).show()
    return false
}

fun checkMinuteInterval(time: String, context: Context?): Boolean {
    if (time != "") {
        return true
    }
    Toast.makeText(context, "Invalid Minute Interval: Must be an integer", Toast.LENGTH_LONG).show()
    return false
}

fun checkInputFieldsAreValid(context: Context?, phone: String, message: String, time:String): Boolean {
    var phoneInput = checkPhoneNumber(phone, context)
    var messageInput = checkMessage(message, context)
    var timeInput = checkMinuteInterval(time, context)

    if (phoneInput == false || messageInput == false || timeInput == false) {
        return false
    }
    return true
}

fun formatPhoneNumber(num: String): String {
    var firstThree = num.subSequence(0,3)
    var secondThree = num.subSequence(3, 6)
    var lastFour = num.subSequence(6, 10)
    var phoneNum = "(" + firstThree + ")" + " " + secondThree + "-" + lastFour
    return phoneNum
}

fun toastMessages(start: Boolean, phone: String, message: String, minutes: String, alarmManager: AlarmManager, context: Context?) {
    var intent = Intent("edu.washington.nguyen51.toastInterval")
    var pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    var intentFilter = IntentFilter("edu.washington.nguyen51.toastInterval")
    if (!start) {
        var phone = formatPhoneNumber(phone)
        var minuteInteveral = calculateMinute(minutes.toInt()).toLong()
        var toastMessage = phone + ": " + message
        // Implement timing function and showing toast message
        context?.registerReceiver(MyReceiver(toastMessage), intentFilter)
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + minuteInteveral, minuteInteveral, pendingIntent)
    } else {
        // Handle stopping the toast messages
        alarmManager.cancel(pendingIntent)
    }
}

class MyReceiver(message: String): BroadcastReceiver() {
    private var toastMessage = message
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
    }
}

fun calculateMinute(n: Int): Int {
    return n * 60 * 1000
}
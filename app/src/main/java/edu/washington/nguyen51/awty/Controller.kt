package edu.washington.nguyen51.awty

import android.telephony.PhoneNumberUtils

fun setStartOrStopBtn(start: Boolean): String {
    when (start) {
        true -> return "Start"
        false -> return "Stop"
    }
}

fun checkPhoneNumber(num: String): Boolean {
    if (num.length == 10) {
        return true
    }
    return false
}

fun checkMessage(message: String): Boolean {
    if (message != "") {
        return true
    }
    return false
}

fun checkMinuteInterval(time: String): Boolean {
    if (time != "") {
        return true
    }
    return false
}

fun checkInputFieldsAreValid(phone: String, message: String, time:String): Boolean {
    var phoneInput = checkPhoneNumber(phone)
    var messageInput = checkMessage(message)
    var timeInput = checkMinuteInterval(time)

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
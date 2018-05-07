package edu.washington.nguyen51.awty

fun setStartOrStopBtn(start: Boolean): String {
    when (start) {
        true -> return "Start"
        false -> return "Stop"
    }
}
package igor.kuridza.ferit.hr.najboljiucenik.common

import java.util.*

const val ONE_DAY_IN_MILLIS = 86400000

fun dataOlderThanOneDay(pastTime: Long): Boolean{
    val currentTime = Calendar.getInstance().timeInMillis
    if((currentTime - pastTime) > ONE_DAY_IN_MILLIS) return true
    return false
}
package igor.kuridza.ferit.hr.najboljiucenik.prefs

interface SharedPrefsHelper {

    fun storeTime(value: Long)

    fun getTime(): Long
}
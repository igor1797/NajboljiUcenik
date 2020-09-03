package igor.kuridza.ferit.hr.najboljiucenik.prefs

import android.content.SharedPreferences
import igor.kuridza.ferit.hr.najboljiucenik.common.PREFS_TIME_KEY
import javax.inject.Inject

class SharedPrefsHelperImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences
): SharedPrefsHelper{

    override fun storeTime(value: Long) {
        val editor = sharedPrefs.edit()
        editor.putLong(PREFS_TIME_KEY, value).apply()
    }

    override fun getTime(): Long {
        return sharedPrefs.getLong(PREFS_TIME_KEY, -1L)
    }
}
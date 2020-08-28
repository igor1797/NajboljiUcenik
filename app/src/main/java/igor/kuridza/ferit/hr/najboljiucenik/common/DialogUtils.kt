package igor.kuridza.ferit.hr.najboljiucenik.common

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import igor.kuridza.ferit.hr.najboljiucenik.R

fun showDialog(activity: Activity?, title: String, positiveButtonListener: () -> Unit){
    activity?.let {
        AlertDialog.Builder(it)
            .setTitle(title)
            .setIcon(R.drawable.ic_alert)
            .setPositiveButton(R.string.positiveAlertButtonText){ _, _ -> positiveButtonListener()}
            .setNegativeButton(R.string.negativeAlertButtonText){ dialog, _ -> dialog.cancel()}
            .show()
    }
}
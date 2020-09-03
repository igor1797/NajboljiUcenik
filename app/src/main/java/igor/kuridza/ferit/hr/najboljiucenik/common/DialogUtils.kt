package igor.kuridza.ferit.hr.najboljiucenik.common

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.R

fun showAlertDialog(activity: Activity?, title: String, positiveButtonListener: () -> Unit){
    activity?.let {
        AlertDialog.Builder(it)
            .setTitle(title)
            .setIcon(R.drawable.ic_alert)
            .setPositiveButton(R.string.positiveAlertButtonText){_,_ -> positiveButtonListener()}
            .setNegativeButton(R.string.negativeAlertButtonText){dialog,_ -> dialog.cancel()}
            .show()
    }
}

fun showDeleteAlertDialog(activity: FragmentActivity?, documentSnapshot: DocumentSnapshot, positiveButtonListener: (DocumentSnapshot) -> Unit){
    activity?.let {
        AlertDialog.Builder(it)
            .setTitle(R.string.deleteAlertDialogTitleText)
            .setIcon(R.drawable.ic_alert)
            .setPositiveButton(R.string.positiveAlertButtonText){_,_ -> positiveButtonListener(documentSnapshot)}
            .setNegativeButton(R.string.negativeAlertButtonText){dialog,_ -> dialog.cancel()}
            .show()
    }
}
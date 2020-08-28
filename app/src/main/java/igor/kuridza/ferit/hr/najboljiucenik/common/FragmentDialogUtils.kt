package igor.kuridza.ferit.hr.najboljiucenik.common

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.R

fun showDeleteAlertDialog(activity: FragmentActivity?, documentSnapshot: DocumentSnapshot, positiveButtonListener: (DocumentSnapshot) -> Unit){
    activity?.let {
        AlertDialog.Builder(it)
            .setTitle(R.string.deleteAlertDialogTitleText)
            .setIcon(R.drawable.ic_alert)
            .setPositiveButton(R.string.deleteAlertDialogPositiveButtonText){_,_ -> positiveButtonListener(documentSnapshot)}
            .setNegativeButton(R.string.deleteAlertDialogNegativeButtonText){dialog,_ -> dialog.cancel()}
            .show()
    }
}
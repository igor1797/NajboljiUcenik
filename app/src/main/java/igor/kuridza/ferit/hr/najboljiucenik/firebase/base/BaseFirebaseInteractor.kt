package igor.kuridza.ferit.hr.najboljiucenik.firebase.base

import com.google.firebase.firestore.FirebaseFirestore

open class BaseFirebaseInteractor{
    val firestore = FirebaseFirestore.getInstance()
}
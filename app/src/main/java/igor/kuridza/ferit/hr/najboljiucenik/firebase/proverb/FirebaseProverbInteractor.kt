package igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion

interface FirebaseProverbInteractor {
    suspend fun getProverbQuestions()
    fun getProverbFirebasePath(): CollectionReference
    fun deleteProverbQuestion(documentSnapshot: DocumentSnapshot)
    fun addProverbQuestion(proverbQuestion: ProverbQuestion)
    fun editProverbQuestion(proverbQuestion: ProverbQuestion, documentSnapshot: DocumentSnapshot)
}
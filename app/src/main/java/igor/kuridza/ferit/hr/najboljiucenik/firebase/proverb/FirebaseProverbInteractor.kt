package igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion

interface FirebaseProverbInteractor {
    suspend fun getProverbQuestions()
    fun getProverbFirebasePath(): CollectionReference
    suspend fun deleteProverbQuestion(documentSnapshot: DocumentSnapshot)
    suspend fun addProverbQuestion(proverbQuestion: ProverbQuestion)
    suspend fun editProverbQuestion(proverbQuestion: ProverbQuestion, documentSnapshot: DocumentSnapshot)
}
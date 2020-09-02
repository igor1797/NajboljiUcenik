package igor.kuridza.ferit.hr.najboljiucenik.firebase.math

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion


interface FirebaseMathInteractor {
    suspend fun getMathQuestions()
    fun getFirestorePath(): CollectionReference
    suspend fun deleteMathQuestion(documentSnapshot: DocumentSnapshot)
    suspend fun addMathQuestion(mathQuestion: MathQuestion)
    suspend fun editMathQuestion(mathQuestion: MathQuestion, documentSnapshot: DocumentSnapshot)
}
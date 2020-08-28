package igor.kuridza.ferit.hr.najboljiucenik.firebase.math

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion


interface FirebaseMathInteractor {
    suspend fun getMathQuestions()
    fun getFirestorePath(): CollectionReference
    fun deleteMathQuestion(documentSnapshot: DocumentSnapshot)
    fun addMathQuestion(mathQuestion: MathQuestion)
    fun editMathQuestion(mathQuestion: MathQuestion, documentSnapshot: DocumentSnapshot)
}
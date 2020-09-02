package igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse

interface FirebaseTrueFalseInteractor {
    fun getFirestorePath(): CollectionReference
    suspend fun getTrueFalseQuestions()
    suspend fun deleteTrueFalseQuestion(documentSnapshot: DocumentSnapshot)
    suspend fun addTrueFalseQuestion(trueFalseQuestion: QuestionTrueFalse)
    suspend fun editTrueFalseQuestion(trueFalseQuestion: QuestionTrueFalse, documentSnapshot: DocumentSnapshot)
}
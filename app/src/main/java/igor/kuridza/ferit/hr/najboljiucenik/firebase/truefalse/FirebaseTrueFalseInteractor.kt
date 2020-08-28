package igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse

interface FirebaseTrueFalseInteractor {
    fun getFirestorePath(): CollectionReference
    suspend fun getTrueFalseQuestions()
    fun deleteTrueFalseQuestion(documentSnapshot: DocumentSnapshot)
    fun addTrueFalseQuestion(trueFalseQuestion: QuestionTrueFalse)
    fun editTrueFalseQuestion(trueFalseQuestion: QuestionTrueFalse, documentSnapshot: DocumentSnapshot)
}
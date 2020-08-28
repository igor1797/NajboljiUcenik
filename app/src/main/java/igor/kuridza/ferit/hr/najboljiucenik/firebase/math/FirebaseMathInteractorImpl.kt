package igor.kuridza.ferit.hr.najboljiucenik.firebase.math

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import igor.kuridza.ferit.hr.najboljiucenik.common.MATH_COLLECTION_PATH
import igor.kuridza.ferit.hr.najboljiucenik.firebase.base.BaseFirebaseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import igor.kuridza.ferit.hr.najboljiucenik.persistance.math.MathQuestionRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseMathInteractorImpl @Inject constructor(
    private val mathQuestionRepository: MathQuestionRepository
): BaseFirebaseInteractor(), FirebaseMathInteractor{

    private val mathQuestionsPath: CollectionReference =
        firestore
            .collection(MATH_COLLECTION_PATH)



    override suspend fun getMathQuestions() {
        mathQuestionRepository.clearAllMathQuestions()
        val questions: QuerySnapshot = mathQuestionsPath.get().await()
        for (document: QueryDocumentSnapshot in questions) {
            val mathQuestion = document.toObject(MathQuestion::class.java)
            mathQuestionRepository.addMathQuestion(mathQuestion)
        }
    }


    override fun addMathQuestion(mathQuestion: MathQuestion){
        mathQuestionsPath.add(mathQuestion)
    }

    override fun deleteMathQuestion(documentSnapshot: DocumentSnapshot) {
        documentSnapshot.reference.delete()
    }

    override fun editMathQuestion(mathQuestion: MathQuestion, documentSnapshot: DocumentSnapshot) {
        documentSnapshot.reference.set(mathQuestion)
    }

    override fun getFirestorePath(): CollectionReference {
        return mathQuestionsPath
    }
}
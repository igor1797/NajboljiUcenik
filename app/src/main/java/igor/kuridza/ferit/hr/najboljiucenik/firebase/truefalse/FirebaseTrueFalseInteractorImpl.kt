package igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.common.TRUE_FALSE_COLLECTION_PATH
import igor.kuridza.ferit.hr.najboljiucenik.firebase.base.BaseFirebaseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.persistance.truefalse.TrueFalseQuestionRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseTrueFalseInteractorImpl @Inject constructor(
    private val trueFalseQuestionRepository: TrueFalseQuestionRepository
): BaseFirebaseInteractor(), FirebaseTrueFalseInteractor {

    private val trueFalseQuestionsPath =
        firestore
            .collection(TRUE_FALSE_COLLECTION_PATH)

    override suspend fun getTrueFalseQuestions() {
        trueFalseQuestionRepository.clearAllQuestionTrueFalse()
        val trueFalseQuestions = trueFalseQuestionsPath.get().await()
            for (document: QueryDocumentSnapshot in trueFalseQuestions) {
                val trueFalseQuestion = document.toObject(QuestionTrueFalse::class.java)
                trueFalseQuestionRepository.addTrueFalseQuestion(trueFalseQuestion)
        }
    }

    override suspend fun deleteTrueFalseQuestion(documentSnapshot: DocumentSnapshot) {
        documentSnapshot.reference.delete()
    }

    override suspend fun addTrueFalseQuestion(trueFalseQuestion: QuestionTrueFalse) {
        trueFalseQuestionsPath.add(trueFalseQuestion)
    }

    override suspend fun editTrueFalseQuestion(
        trueFalseQuestion: QuestionTrueFalse,
        documentSnapshot: DocumentSnapshot
    ) {
        documentSnapshot.reference.set(trueFalseQuestion)
    }

    override fun getFirestorePath(): CollectionReference {
        return trueFalseQuestionsPath
    }
}
package igor.kuridza.ferit.hr.najboljiucenik.firebase.geography

import com.google.firebase.firestore.QueryDocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.common.FLAGS_DOCUMENT_PATH
import igor.kuridza.ferit.hr.najboljiucenik.firebase.base.BaseFirebaseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.FlagQuestion
import igor.kuridza.ferit.hr.najboljiucenik.persistance.flagsgeography.FlagQuestionRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFlagInteractorImpl @Inject constructor(
    private val flagQuestionRepository: FlagQuestionRepository
): BaseFirebaseInteractor(), FirebaseFlagInteractor {

    private val flagQuestionsPath =
        firestore
            .collection(FLAGS_DOCUMENT_PATH)

    override suspend fun getFlagQuestions() {
        flagQuestionRepository.clearAllFlagQuestions()
        val flagsQuestions = flagQuestionsPath.get().await()
            for (document: QueryDocumentSnapshot in flagsQuestions) {
                val flagQuestion = document.toObject(FlagQuestion::class.java)
                flagQuestionRepository.addFlagQuestion(flagQuestion)
        }
    }
}
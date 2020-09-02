package igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.common.PROVERBS_PATH
import igor.kuridza.ferit.hr.najboljiucenik.firebase.base.BaseFirebaseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.persistance.proverb.ProverbRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseProverbInteractorImpl @Inject constructor(
    private val proverbRepository: ProverbRepository
): BaseFirebaseInteractor(), FirebaseProverbInteractor{

    private val proverbPath = firestore.collection(PROVERBS_PATH)

    override suspend fun getProverbQuestions() {
        proverbRepository.clearAllQuestions()
        val proverbQuestions = proverbPath.get().await()
        for (document: QueryDocumentSnapshot in proverbQuestions) {
            val proverbQuestion = document.toObject(ProverbQuestion::class.java)
            proverbRepository.addProverb(proverbQuestion)
        }
    }

    override fun getProverbFirebasePath(): CollectionReference {
        return proverbPath
    }

    override suspend fun addProverbQuestion(proverbQuestion: ProverbQuestion) {
        proverbPath.add(proverbQuestion)
    }

    override suspend fun editProverbQuestion(
        proverbQuestion: ProverbQuestion,
        documentSnapshot: DocumentSnapshot
    ) {
        documentSnapshot.reference.set(proverbQuestion)
    }

    override suspend fun deleteProverbQuestion(documentSnapshot: DocumentSnapshot) {
        documentSnapshot.reference.delete()
    }
}
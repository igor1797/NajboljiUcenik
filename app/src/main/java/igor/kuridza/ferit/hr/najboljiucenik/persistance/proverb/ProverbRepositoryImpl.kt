package igor.kuridza.ferit.hr.najboljiucenik.persistance.proverb

import igor.kuridza.ferit.hr.najboljiucenik.database.ProverbDao
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import javax.inject.Inject

class ProverbRepositoryImpl @Inject constructor(
    private val proverbDao: ProverbDao
) : ProverbRepository {

    override suspend fun addProverb(proverbQuestion: ProverbQuestion) {
        proverbDao.addQuestion(proverbQuestion)
    }

    override suspend fun clearAllQuestions() {
        proverbDao.clearAllQuestions()
    }

    override fun get10ProverbQuestions(): List<ProverbQuestion> {
        return proverbDao.get10ProverbQuestions()
    }
}
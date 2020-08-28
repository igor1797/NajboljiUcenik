package igor.kuridza.ferit.hr.najboljiucenik.persistance.flagsgeography

import igor.kuridza.ferit.hr.najboljiucenik.database.FlagQuestionDao
import igor.kuridza.ferit.hr.najboljiucenik.model.FlagQuestion
import javax.inject.Inject

class FlagQuestionRepositoryImpl @Inject constructor(
    private val flagQuestionDao: FlagQuestionDao
): FlagQuestionRepository {

    override fun get10FlagQuestions(): List<FlagQuestion> {
        return flagQuestionDao.get10FlagQuestions()
    }

    override suspend fun clearAllFlagQuestions() {
        flagQuestionDao.clearAllFlagQuestions()
    }

    override suspend fun addFlagQuestion(question: FlagQuestion) {
        flagQuestionDao.addFlagQuestion(question)
    }
}
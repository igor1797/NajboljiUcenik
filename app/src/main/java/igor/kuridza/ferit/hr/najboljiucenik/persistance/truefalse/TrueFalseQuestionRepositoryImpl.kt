package igor.kuridza.ferit.hr.najboljiucenik.persistance.truefalse

import igor.kuridza.ferit.hr.najboljiucenik.database.TrueFalseQuestionDao
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import javax.inject.Inject

class TrueFalseQuestionRepositoryImpl @Inject constructor(
    private val trueFalseQuestionDao: TrueFalseQuestionDao
): TrueFalseQuestionRepository {

    override fun get100TrueFalseQuestionsForCategory(categoryName: String): List<QuestionTrueFalse> {
        return trueFalseQuestionDao.get100TrueFalseQuestionsForCategory(categoryName)
    }

    override suspend fun clearAllQuestionTrueFalse() {
        trueFalseQuestionDao.clearAllQuestionTrueFalse()
    }

    override suspend fun addTrueFalseQuestion(question: QuestionTrueFalse) {
        trueFalseQuestionDao.addTrueFalseQuestion(question)
    }
}
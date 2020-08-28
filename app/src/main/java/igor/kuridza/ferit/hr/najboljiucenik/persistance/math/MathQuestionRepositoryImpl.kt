package igor.kuridza.ferit.hr.najboljiucenik.persistance.math

import igor.kuridza.ferit.hr.najboljiucenik.database.MathQuestionDao
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import javax.inject.Inject

class MathQuestionRepositoryImpl @Inject constructor(
    private val mathQuestionDao: MathQuestionDao
): MathQuestionRepository {

    override fun get10MathQuestionsForCategory(categoryName: String): List<MathQuestion> {
        return mathQuestionDao.get10MathQuestionsForCategory(categoryName)
    }

    override suspend fun clearAllMathQuestions() {
        mathQuestionDao.clearAllMathQuestions()
    }

    override suspend fun addMathQuestion(question: MathQuestion) {
        mathQuestionDao.addMathQuestion(question)
    }
}
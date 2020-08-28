package igor.kuridza.ferit.hr.najboljiucenik.persistance.math

import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion

interface MathQuestionRepository {
    fun get10MathQuestionsForCategory(categoryName: String): List<MathQuestion>
    suspend fun clearAllMathQuestions()
    suspend fun addMathQuestion(question: MathQuestion)
}
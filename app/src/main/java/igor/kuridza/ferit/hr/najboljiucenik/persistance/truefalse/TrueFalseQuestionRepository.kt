package igor.kuridza.ferit.hr.najboljiucenik.persistance.truefalse

import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse

interface TrueFalseQuestionRepository {
    fun get100TrueFalseQuestionsForCategory(categoryName: String): List<QuestionTrueFalse>
    suspend fun clearAllQuestionTrueFalse()
    suspend fun addTrueFalseQuestion(question: QuestionTrueFalse)
}
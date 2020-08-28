package igor.kuridza.ferit.hr.najboljiucenik.persistance.flagsgeography

import igor.kuridza.ferit.hr.najboljiucenik.model.FlagQuestion

interface FlagQuestionRepository {
    fun get10FlagQuestions(): List<FlagQuestion>
    suspend fun clearAllFlagQuestions()
    suspend fun addFlagQuestion(question: FlagQuestion)
}
package igor.kuridza.ferit.hr.najboljiucenik.persistance.proverb

import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion

interface ProverbRepository {
    fun get10ProverbQuestions(): List<ProverbQuestion>
    suspend fun addProverb(proverbQuestion: ProverbQuestion)
    suspend fun clearAllQuestions()
}
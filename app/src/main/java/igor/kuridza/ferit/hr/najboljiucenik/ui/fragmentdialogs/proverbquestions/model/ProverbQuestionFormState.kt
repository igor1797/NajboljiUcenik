package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.model

data class ProverbQuestionFormState(
    val questionError: Int? = null,
    val answerError: Int? = null,
    val lettersError: Int? = null,
    val isDataValid: Boolean = false
)
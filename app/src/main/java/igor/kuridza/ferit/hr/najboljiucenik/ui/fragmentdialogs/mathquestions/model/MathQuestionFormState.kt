package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.model

/**
 * Data validation state of the add new math question form.
 **/
data class MathQuestionFormState(
    val questionError: Int? = null,
    val answerError: Int? = null,
    val isDataValid: Boolean = false
)
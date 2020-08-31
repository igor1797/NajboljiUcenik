package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.single.viewmodel

import android.os.CountDownTimer
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.persistance.truefalse.TrueFalseQuestionRepository


class TrueFalseViewModel @ViewModelInject constructor(
    private val trueFalseQuestionRepository: TrueFalseQuestionRepository
): ViewModel(){

    companion object {
        // Time when the game is over
        private const val DONE = 0L
        // Countdown time interval
        private const val ONE_SECOND = 1000L
        // Total time for the game
        private const val COUNTDOWN_TIME = 15900L
    }

    private val _questions = MutableLiveData<List<QuestionTrueFalse>>()
    private val _currentQuestion = MutableLiveData<QuestionTrueFalse>()
    private val _score = MutableLiveData<Long>(0L)
    private val _currentNumberOfQuestion = MutableLiveData(0)
    private val _gameOver = MutableLiveData<Boolean>(false)
    private val _countDown = MutableLiveData<Long>()

    val currentNumberOfQuestion: LiveData<Int> = _currentNumberOfQuestion
    val questions: LiveData<List<QuestionTrueFalse>> = _questions
    val currentQuestion: LiveData<QuestionTrueFalse> = _currentQuestion
    val score: LiveData<Long> = _score
    val gameOver: LiveData<Boolean> = _gameOver
    val countDown: LiveData<Long> = _countDown

    private val timer: CountDownTimer

    init {
        timer = object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _countDown.value = (millisUntilFinished/ ONE_SECOND)
            }

            override fun onFinish() {
                _countDown.value = DONE
                _gameOver.value = true
            }
        }
        timer.start()
    }

    fun getQuestions(categoryType: String){
        _questions.value = trueFalseQuestionRepository.get100TrueFalseQuestionsForCategory(categoryType)
    }

    fun setFirstQuestion(){
        _currentQuestion.value = questions.value?.first()
    }

    private fun nextQuestion(){
        _currentNumberOfQuestion.value = (currentNumberOfQuestion.value)?.plus(1)
        _currentQuestion.value = questions.value?.get(_currentNumberOfQuestion.value!!)
        timer.start()
    }

    fun checkQuestion(selected: Boolean){
        if(selected == _currentQuestion.value?.answer){
            correctAnswer()
        }else{
            wrongAnswer()
        }
    }

    private fun changeQuestion(){
        if(_currentNumberOfQuestion.value?.equals(_questions.value?.size?.minus(1))!!){
            _gameOver.value = true
        }else{
            nextQuestion()
        }
    }

    private fun correctAnswer(){
        _score.value = (_score.value)!!.plus(countDown.value!!)
        changeQuestion()
    }

    private fun wrongAnswer(){
        _gameOver.value = true
    }

    //cancel the timer to avoid memory leaks
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
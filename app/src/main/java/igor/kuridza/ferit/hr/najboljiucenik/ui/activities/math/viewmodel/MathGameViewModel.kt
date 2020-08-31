package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.math.viewmodel

import android.os.CountDownTimer
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import igor.kuridza.ferit.hr.najboljiucenik.persistance.math.MathQuestionRepository

class MathGameViewModel @ViewModelInject constructor(
   private val mathQuestionRepository: MathQuestionRepository
): ViewModel(){

    companion object {
        // Time when the game is over
        private const val DONE = 0L
        // Countdown time interval
        private const val ONE_SECOND = 1000L
        // Total time for the game
        private const val COUNTDOWN_TIME = 10900L
    }

    private val _answer = MutableLiveData<String>("")
    private val _questions = MutableLiveData<List<MathQuestion>>()
    private val _currentQuestion = MutableLiveData<MathQuestion>()
    private val _gameOver = MutableLiveData<Boolean>(false)
    private val _score = MutableLiveData<Long>(0)
    private val _currentNumberOfQuestion = MutableLiveData(0)
    private val _countDown = MutableLiveData<Long>()

    private val questions: LiveData<List<MathQuestion>> = _questions
    val currentNumberOfQuestion: LiveData<Int> = _currentNumberOfQuestion
    val answer: LiveData<String> = _answer
    val score: LiveData<Long> = _score
    val gameOver: LiveData<Boolean> = _gameOver
    val currentQuestion: LiveData<MathQuestion> = _currentQuestion
    val countDown: LiveData<Long> = _countDown

    private val timer: CountDownTimer

    init {
        timer = object :CountDownTimer(
            COUNTDOWN_TIME,
            ONE_SECOND
        ){
            override fun onTick(millisUntilFinished: Long) {
                _countDown.value = (millisUntilFinished/ ONE_SECOND)
            }

            override fun onFinish() {
                _countDown.value = DONE
                changeQuestion()
            }
        }
    }


    fun getQuestions(categoryType: String){
        _questions.value = mathQuestionRepository.get10MathQuestionsForCategory(categoryType)
    }

    fun setFirstQuestion(){
        _currentQuestion.value = _questions.value?.first()
        timer.start()
    }

    fun newNumber(number: Int){
        val stringBuilder = StringBuilder()
        stringBuilder.append(answer.value)
        stringBuilder.append(number)
        val answer = stringBuilder.toString()
        //da se ne moze dodavati stalno brojevi, vec se ogranici na troznamenkaste
        if(answer.length<4) {
            _answer.value = stringBuilder.toString()
        }
        checkAnswer()
    }

    private fun checkAnswer(){
        if(_answer.value.equals(_currentQuestion.value?.answer)){
            correctAnswer()
        }
    }

    private fun correctAnswer(){
        _score.value = (_score.value)!!.plus(countDown.value!!)
    }

    private fun nextQuestion(){
        _currentNumberOfQuestion.value = (currentNumberOfQuestion.value)?.plus(1)
        _answer.value = ""
        _currentQuestion.value = questions.value?.get(currentNumberOfQuestion.value!!)
        timer.start()
    }

    fun changeQuestion(){
        if(_currentNumberOfQuestion.value?.equals(_questions.value?.size?.minus(1))!!){
            _gameOver.value = true
        }else{
            nextQuestion()
        }
    }

    fun cancelTimer(){
        timer.cancel()
    }

    fun startTimer(){
        timer.start()
    }

    fun eraseNumber(){
        if(_answer.value?.length?.equals(0) == false){
            _answer.value = (_answer.value)?.substring(0, (_answer.value?.length?.minus(1))!!)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }

}
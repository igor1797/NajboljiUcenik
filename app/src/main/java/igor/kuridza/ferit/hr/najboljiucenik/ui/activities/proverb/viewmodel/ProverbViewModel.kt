package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.proverb.viewmodel

import android.os.CountDownTimer
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import igor.kuridza.ferit.hr.najboljiucenik.common.connect
import igor.kuridza.ferit.hr.najboljiucenik.common.replaceAt
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.persistance.proverb.ProverbRepository

class ProverbViewModel @ViewModelInject constructor(
    private val proverbRepository: ProverbRepository
): ViewModel(){

    companion object {
        // Time when the game is over
        private const val DONE = 0L
        // Countdown time interval
        private const val ONE_SECOND = 1000L
        // Total time for the game
        private const val COUNTDOWN_TIME = 20900L
    }

    private val _answer = MutableLiveData<String>("")
    private val _questions = MutableLiveData<List<ProverbQuestion>>()
    private val _currentQuestion = MutableLiveData<ProverbQuestion>()
    private val _gameOver = MutableLiveData<Boolean>(false)
    private val _score = MutableLiveData<Long>(0)
    private val _letters = MutableLiveData<String>()
    private val _currentNumberOfQuestion = MutableLiveData(0)
    private val _countDown = MutableLiveData<Long>(20L)

    private val questions: LiveData<List<ProverbQuestion>> = _questions
    val currentNumberOfQuestion: LiveData<Int> = _currentNumberOfQuestion
    val answer: LiveData<String> = _answer
    val score: LiveData<Long> = _score
    val gameOver: LiveData<Boolean> = _gameOver
    val letters: LiveData<String> = _letters
    val currentQuestion: LiveData<ProverbQuestion> = _currentQuestion
    val countDown: LiveData<Long> = _countDown

    private val timer: CountDownTimer
    private val answerLettersButtonPositions = arrayListOf<Int>()

    init {
        timer = object : CountDownTimer(
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
        _questions.value = getQuestions()
        setFirstQuestion()
    }

    private fun getQuestions(): List<ProverbQuestion>{
        return proverbRepository.get10ProverbQuestions()
    }

    private fun setFirstQuestion(){
        _currentQuestion.value = _questions.value?.first()
        _letters.value = _currentQuestion.value?.letters
    }

    fun newLetter(letter: String, position: Int){
        _answer.value = answer.value?.connect(letter)
        _letters.value = _letters.value?.replaceAt(position,' ')
        answerLettersButtonPositions.add(position)
        checkAnswer()
    }

    private fun checkAnswer(){
        if(answer.value.equals(currentQuestion.value?.answer)){
            correctAnswer()
        }
    }

    private fun correctAnswer(){
        _score.value = (score.value)!!.plus(countDown.value!!)
    }

    fun startTimer(){
        timer.start()
    }

    fun cancelTimer(){
        timer.cancel()
    }

    private fun nextQuestion(){
        _currentNumberOfQuestion.value = (currentNumberOfQuestion.value)?.plus(1)
        _answer.value = ""
        _currentQuestion.value = questions.value?.get(currentNumberOfQuestion.value!!)
        _letters.value = _currentQuestion.value?.letters
    }

    fun changeQuestion(){
        if(currentNumberOfQuestion.value?.equals(questions.value?.size?.minus(1))!!){
            _gameOver.value = true
        }else{
            nextQuestion()
        }
    }

    fun eraseLetter(){
        if(answer.value?.length?.equals(0) == false){
            _answer.value = (answer.value)?.substring(0, (answer.value?.length?.minus(1))!!)
            val position = answerLettersButtonPositions.last()
            answerLettersButtonPositions.removeAt(answerLettersButtonPositions.size-1)
            _letters.value = (letters.value)?.replaceAt(position, currentQuestion.value!!.letters[position])
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }
}
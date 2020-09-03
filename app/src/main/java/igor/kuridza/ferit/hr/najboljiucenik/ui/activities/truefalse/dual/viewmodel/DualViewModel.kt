package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.dual.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.persistance.truefalse.TrueFalseQuestionRepository

class DualViewModel @ViewModelInject constructor(
    private val trueFalseQuestionRepository: TrueFalseQuestionRepository
): ViewModel(){

    private val _questions = MutableLiveData<List<QuestionTrueFalse>>()
    private val _currentQuestion = MutableLiveData<QuestionTrueFalse>()
    private val _player1Score = MutableLiveData<Int>(0)
    private val _player1AnsweredWrong = MutableLiveData<Boolean>(false)
    private val _player2Score = MutableLiveData<Int>(0)
    private val _player2AnsweredWrong = MutableLiveData<Boolean>(false)
    private val _currentNumberOfQuestion = MutableLiveData(0)
    private val _gameOver = MutableLiveData<Boolean>(false)

    private val questions: LiveData<List<QuestionTrueFalse>> = _questions
    val currentNumberOfQuestion: LiveData<Int> = _currentNumberOfQuestion
    val currentQuestion: LiveData<QuestionTrueFalse> = _currentQuestion
    val player1Score: LiveData<Int> = _player1Score
    val player1AnsweredWrong: LiveData<Boolean> = _player1AnsweredWrong
    val player2Score: LiveData<Int> = _player2Score
    val player2AnsweredWrong: LiveData<Boolean> = _player2AnsweredWrong
    val gameOver: LiveData<Boolean> = _gameOver

    fun getQuestions(categoryType: String){
        _questions.value = trueFalseQuestionRepository.get10TrueFalseQuestionsForCategory(categoryType)
    }

    fun setFirstQuestion(){
        _currentQuestion.value = questions.value?.first()
    }

    fun player1checkAnswer(answer: Boolean){
        if(answer == currentQuestion.value?.answer && !(player1AnsweredWrong.value!!)){
            _player1Score.value = player1Score.value?.plus(1)
            changeQuestion()
        }else if (!(player1AnsweredWrong.value!!)){
            _player1AnsweredWrong.value = true
            _player1Score.value = player1Score.value?.minus(1)
        }else if(answer == currentQuestion.value?.answer && player1AnsweredWrong.value!!){
            changeQuestion()
        }
    }

    fun player2CheckAnswer(answer: Boolean){
        if(answer == currentQuestion.value?.answer && !(player2AnsweredWrong.value!!)){
            _player2Score.value = player2Score.value?.plus(1)
            changeQuestion()
        } else if (!(player2AnsweredWrong.value!!)){
            _player2AnsweredWrong.value = true
            _player2Score.value = player2Score.value?.minus(1)
        }else if(answer == currentQuestion.value?.answer && player2AnsweredWrong.value!!){
            changeQuestion()
        }
    }

    private fun changeQuestion(){
        if(questions.value?.size?.minus(1)?.equals(currentNumberOfQuestion.value)==true){
            _gameOver.value = true
        }
        else{
            nextQuestion()
        }
    }

    private fun nextQuestion(){
        _currentNumberOfQuestion.value = currentNumberOfQuestion.value?.plus(1)
        _player1AnsweredWrong.value = false
        _player2AnsweredWrong.value = false
        _currentQuestion.value = questions.value?.get(currentNumberOfQuestion.value!!)
    }
}
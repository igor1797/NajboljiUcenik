package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.math.view

import android.content.Intent
import android.os.Handler
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.CATEGORY_TYPE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.databinding.ActivityMathGameBinding
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.view.SubmitScoreActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.math.viewmodel.MathGameViewModel
import kotlinx.android.synthetic.main.activity_flags.answer
import kotlinx.android.synthetic.main.activity_math_game.*

@AndroidEntryPoint
class MathGameActivity : BaseActivity() {

    private lateinit var categoryType: String

    private val mathGameViewModel: MathGameViewModel by lazy {
        ViewModelProvider(this).get(MathGameViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_math_game

    override fun setUpUi() {
        categoryType = intent.getStringExtra(CATEGORY_TYPE_KEY)!!

        mathGameViewModel.apply {
            getQuestions(categoryType)
            setFirstQuestion()
        }

        val binding: ActivityMathGameBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_math_game)
        // Bind layout with ViewModel
        binding.viewmodel = mathGameViewModel
        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this

        observeGameOver()
        observeAnswer()
        observeCurrentQuestion()
    }

    private fun observeGameOver(){
        mathGameViewModel.gameOver.observe(this, Observer { gameOver ->
            if(gameOver){
               startScoreActivity()
            }
        })
    }

    private fun observeAnswer(){
        mathGameViewModel.answer.observe(this, Observer {answer->
            if(answer == mathGameViewModel.currentQuestion.value?.answer){
                correctAnswer()
            }else{
                wrongAnswer()
            }
        })
    }

    private fun observeCurrentQuestion(){
        mathGameViewModel.currentQuestion.observe(this, Observer {
            enableButtons()
        })
    }

    private fun correctAnswer(){
        answer.setTextColor(ContextCompat.getColor(answer.context, R.color.colorGreen))
        mathGameViewModel.cancelTimer()
        disableButtons()
        delayChangingQuestion()
    }

    private fun wrongAnswer(){
        answer.setTextColor(ContextCompat.getColor(answer.context, R.color.colorRed))
    }

    private fun delayChangingQuestion(){
        val handler = Handler()
        handler.postDelayed({
            mathGameViewModel.changeQuestion()
        }, 1000)
    }

    private fun disableButtons(){
        val buttons = listOf<Button>(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11)
        for(button: Button in buttons){
            button.isEnabled = false
        }
    }

    private fun enableButtons(){
        val buttons = listOf<Button>(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11)
        for(button: Button in buttons){
            button.isEnabled = true
        }
    }

    private fun startScoreActivity(){
        val intent = Intent(this, SubmitScoreActivity::class.java)
        intent.apply {
            putExtra(SCORE_KEY, mathGameViewModel.score.value)
            putExtra(CATEGORY_TYPE_KEY, categoryType)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }
}
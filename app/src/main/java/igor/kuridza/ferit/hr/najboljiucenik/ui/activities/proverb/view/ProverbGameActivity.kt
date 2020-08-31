package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.proverb.view

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.databinding.ActivityFlagsBinding
import igor.kuridza.ferit.hr.najboljiucenik.databinding.ActivityProverbGameBinding
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.proverb.viewmodel.ProverbViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.view.SubmitScoreActivity
import kotlinx.android.synthetic.main.activity_flags.*
import kotlinx.android.synthetic.main.activity_proverb_game.*
import kotlinx.android.synthetic.main.activity_proverb_game.answer
import kotlinx.android.synthetic.main.activity_proverb_game.b1
import kotlinx.android.synthetic.main.activity_proverb_game.b10
import kotlinx.android.synthetic.main.activity_proverb_game.b11
import kotlinx.android.synthetic.main.activity_proverb_game.b12
import kotlinx.android.synthetic.main.activity_proverb_game.b2
import kotlinx.android.synthetic.main.activity_proverb_game.b3
import kotlinx.android.synthetic.main.activity_proverb_game.b4
import kotlinx.android.synthetic.main.activity_proverb_game.b5
import kotlinx.android.synthetic.main.activity_proverb_game.b6
import kotlinx.android.synthetic.main.activity_proverb_game.b7
import kotlinx.android.synthetic.main.activity_proverb_game.b8
import kotlinx.android.synthetic.main.activity_proverb_game.b9

@AndroidEntryPoint
class ProverbGameActivity : BaseActivity() {

    private val proverbViewModel: ProverbViewModel by lazy {
        ViewModelProvider(this).get(ProverbViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_proverb_game

    override fun setUpUi() {
        // Obtain binding
        val binding: ActivityProverbGameBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_proverb_game)
        // Bind layout with ViewModel
        binding.viewmodel = proverbViewModel
        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this

        observeGameOver()
        observeAnswer()
        observeCurrentQuestion()
        setButtonsOnClickListener()
    }

    private fun observeCurrentQuestion(){
        proverbViewModel.currentQuestion.observe(this, Observer {
            enableButtons()
            proverbViewModel.startTimer()
        })
    }

    private fun observeGameOver(){
        proverbViewModel.gameOver.observe(this, Observer { gameOver ->
            if(gameOver){
                startSubmitScoreActivity()
            }
        })
    }

    private fun startSubmitScoreActivity(){
        val intent = Intent(this, SubmitScoreActivity::class.java)
        intent.apply {
            putExtra(SCORE_KEY, proverbViewModel.score.value)
            putExtra(CATEGORY_TYPE_KEY, CROATIAN_PROVERB)
        }
        startActivity(intent)
        finish()
    }

    private fun observeAnswer(){
        proverbViewModel.answer.observe(this, Observer {
            if(proverbViewModel.currentQuestion.value?.answer == it){
                correctAnswer()
            }else{
                wrongAnswer()
            }
        })
    }

    private fun correctAnswer(){
        answer.setTextColor(ContextCompat.getColor(answer.context, R.color.colorGreen))
        proverbViewModel.cancelTimer()
        disableButtons()
        delayChangingQuestion()
    }

    private fun wrongAnswer(){
        answer.setTextColor(ContextCompat.getColor(answer.context, R.color.colorRed))
    }

    private fun delayChangingQuestion(){
        val handler = Handler()
        handler.postDelayed({
            proverbViewModel.changeQuestion()
        }, 1000)
    }

    private fun disableButtons(){
        val buttons = listOf<Button>(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12)
        for(button: Button in buttons){
            button.isEnabled = false
        }
    }

    private fun enableButtons(){
        val buttons = listOf<Button>(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12)
        for(button: Button in buttons){
            button.isEnabled = true
        }
    }

    private fun setButtonsOnClickListener(){
        b1.onClick {
            onButtonClick(b1, 0)
        }

        b2.onClick {
            onButtonClick(b2, 1)
        }

        b3.onClick {
            onButtonClick(b3, 2)
        }

        b4.onClick {
            onButtonClick(b4, 3)
        }

        b5.onClick {
            onButtonClick(b5, 4)
        }

        b6.onClick {
            onButtonClick(b6, 5)
        }

        b7.onClick {
            onButtonClick(b7, 6)
        }

        b8.onClick {
            onButtonClick(b8, 7)
        }

        b9.onClick {
            onButtonClick(b9, 8)
        }

        b10.onClick {
            onButtonClick(b10, 9)
        }

        b11.onClick {
            onButtonClick(b11, 10)
        }
    }

    private fun onButtonClick(button: Button, position: Int){
        proverbViewModel.newLetter(button.text.toString(), position)
    }

    private fun showAlertDialog(){
        showAlertDialog(this, getString(R.string.progressWillNotBeSavedText)){ positiveButtonListener() }
    }

    private fun positiveButtonListener(){
        finish()
    }

    override fun onBackPressed() {
        showAlertDialog()
    }
}
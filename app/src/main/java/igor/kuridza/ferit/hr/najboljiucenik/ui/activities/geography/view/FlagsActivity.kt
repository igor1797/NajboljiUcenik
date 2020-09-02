package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.geography.view


import android.animation.Animator
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.CATEGORY_TYPE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.GEOGRAPHY_FLAGS
import igor.kuridza.ferit.hr.najboljiucenik.common.SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.databinding.ActivityFlagsBinding
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.geography.viewmodel.FlagsViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.view.SubmitScoreActivity
import kotlinx.android.synthetic.main.activity_flags.*
import igor.kuridza.ferit.hr.najboljiucenik.common.showAlertDialog

@AndroidEntryPoint
class FlagsActivity : BaseActivity(){

    private val flagsViewModel: FlagsViewModel by lazy {
        ViewModelProvider(this).get(FlagsViewModel::class.java)
    }

    override fun getLayoutResourceId() = R.layout.activity_flags

    override fun setUpUi() {
        // Obtain binding
        val binding: ActivityFlagsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_flags)
        // Bind layout with ViewModel
        binding.viewmodel = flagsViewModel
        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this

        observeGameOver()
        observeAnswer()
        observeCurrentQuestion()
        setButtonsOnClickListener()
    }

    private fun observeCurrentQuestion(){
        flagsViewModel.currentQuestion.observe(this, Observer { flagQuestion->
            GlideToVectorYou
                .init()
                .with(flag.context)
                .withListener(object: GlideToVectorYouListener{
                    override fun onLoadFailed() {
                        Log.d("TAG", "fail load image ${flagQuestion.answer}")
                    }
                    override fun onResourceReady() {
                        animateChangingQuestion(1F)
                    }
                })
                .load(Uri.parse(flagQuestion.imageUrl), flag)
        })
    }

    private fun observeGameOver(){
        flagsViewModel.gameOver.observe(this, Observer { gameOver ->
            if(gameOver){
                startSubmitScoreActivity()
            }
        })
    }

    private fun startSubmitScoreActivity(){
        val intent = Intent(this, SubmitScoreActivity::class.java)
        intent.apply {
            putExtra(SCORE_KEY, flagsViewModel.score.value)
            putExtra(CATEGORY_TYPE_KEY, GEOGRAPHY_FLAGS)
        }
        startActivity(intent)
        finish()
    }

    private fun observeAnswer(){
        flagsViewModel.answer.observe(this, Observer {
            if(flagsViewModel.currentQuestion.value?.answer == it){
                correctAnswer()
            }else{
                wrongAnswer()
            }
        })
    }

    private fun correctAnswer(){
        answer.setTextColor(ContextCompat.getColor(answer.context, R.color.colorGreen))
        flagsViewModel.cancelTimer()
        animateChangingQuestion(0F)
        disableButtons()
        delayChangingQuestion()
    }

    private fun wrongAnswer(){
        answer.setTextColor(ContextCompat.getColor(answer.context, R.color.colorRed))
    }

    private fun delayChangingQuestion(){
        val handler = Handler()
        handler.postDelayed({
            flagsViewModel.changeQuestion()
        }, 1000)
    }

    private fun animateChangingQuestion(value: Float){
        val animation = flag.animate().alpha(value).scaleX(value).scaleY(value)
        animation.apply {
            duration = 500
            startDelay = 100
            interpolator = DecelerateInterpolator()
            setListener(object: Animator.AnimatorListener{
                override fun onAnimationEnd(animation: Animator?) {
                    if(value == 1F){
                        flagsViewModel.startTimer()
                        enableButtons()
                    }
                }
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?){}
                override fun onAnimationStart(animation: Animator?) {}
            })
        }
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
        flagsViewModel.newLetter(button.text.toString(), position)
    }

    private fun showAlertDialog(){
        showAlertDialog(
            this,
            getString(R.string.progressWillNotBeSavedText)
        ) { positiveButtonListener() }
    }

    private fun positiveButtonListener(){
        finish()
    }

    override fun onBackPressed() {
        showAlertDialog()
    }

}
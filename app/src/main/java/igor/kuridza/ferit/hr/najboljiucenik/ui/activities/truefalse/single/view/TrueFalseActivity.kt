package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.single.view

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.CATEGORY_TYPE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.databinding.ActivityTrueFalseBinding
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.view.SubmitScoreActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.single.viewmodel.TrueFalseViewModel
import igor.kuridza.ferit.hr.najboljiucenik.common.showAlertDialog


@AndroidEntryPoint
class TrueFalseActivity : BaseActivity() {

    private val trueFalseViewModel: TrueFalseViewModel by lazy {
        ViewModelProvider(this).get(TrueFalseViewModel::class.java)
    }

    private lateinit var categoryType: String

    override fun getLayoutResourceId() = R.layout.activity_true_false

    override fun setUpUi() {
        categoryType = intent.getStringExtra(CATEGORY_TYPE_KEY)!!
        trueFalseViewModel.apply {
            getQuestions(categoryType)
            setFirstQuestion()
        }
        // Obtain binding
        val binding =
            DataBindingUtil.setContentView<ActivityTrueFalseBinding>( this, R.layout.activity_true_false)
        // Bind layout with ViewModel
        binding.viewmodel = trueFalseViewModel
        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this

        observeGameOver()
    }

    private fun observeGameOver(){
        trueFalseViewModel.gameOver.observe(this, Observer { gameOver->
            if(gameOver){
                startScoreBoardActivity()
            }
        })
    }

    private fun startScoreBoardActivity(){
        val intent = Intent(this, SubmitScoreActivity::class.java)
        intent.apply {
            putExtra(SCORE_KEY, trueFalseViewModel.score.value)
            putExtra(CATEGORY_TYPE_KEY, categoryType)
        }
        startActivity(intent)
        finish()
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
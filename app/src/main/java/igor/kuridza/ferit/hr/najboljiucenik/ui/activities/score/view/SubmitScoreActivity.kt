package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.view

import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.CATEGORY_TYPE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.viewmodel.SubmitScoreViewModel
import kotlinx.android.synthetic.main.activity_submit_score.*
import igor.kuridza.ferit.hr.najboljiucenik.common.showAlertDialog

@AndroidEntryPoint
class SubmitScoreActivity : BaseActivity() {

    private  var mScore: Long = 0L
    private lateinit var categoryType: String

    private val submitScoreViewModel: SubmitScoreViewModel by lazy {
        ViewModelProvider(this).get(SubmitScoreViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int =  R.layout.activity_submit_score

    override fun setUpUi() {
        categoryType = intent.getStringExtra(CATEGORY_TYPE_KEY)!!
        mScore = intent.getLongExtra(SCORE_KEY, 0L)
        score.text = mScore.toString()

        btnSaveScore.onClick {
            saveScore()
        }
    }

    private fun saveScore(){
        val mNickname = nickname.text.toString()

        if(mNickname.isEmpty()){
            nickname.error = getString(R.string.nicknameFieldCantBeEmptyText)
            return
        }
        submitScoreViewModel.submitScore(mNickname, categoryType, mScore)
        finish()
    }

    private fun showAlertDialog() {
        showAlertDialog(this, getString(R.string.scoreWillNotBeSavedText)){ positiveButtonListener() }
    }

    private fun positiveButtonListener(){
        finish()
    }

    override fun onBackPressed() {
       showAlertDialog()
    }
}
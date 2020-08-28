package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.CATEGORY_TYPE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.main.view.MainActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.viewmodel.SubmitScoreViewModel
import kotlinx.android.synthetic.main.activity_submit_score.*

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
            startMainActivity()
        }
    }

    private fun saveScore(){
        val mNickname = nickname.text.toString()

        if(mNickname.isEmpty()){
            nickname.error = getString(R.string.nicknameFieldCantBeEmptyText)
            return
        }
        submitScoreViewModel.submitScore(mNickname, categoryType, mScore)
    }

    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        //implementirati dijalog želite li stvarno izaći
        startMainActivity()
        super.onBackPressed()
    }
}
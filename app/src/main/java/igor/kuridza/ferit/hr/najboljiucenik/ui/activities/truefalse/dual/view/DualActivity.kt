package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.dual.view


import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.CATEGORY_TYPE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.PLAYER1_SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.PLAYER2_SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.dual.DualScoreActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.dual.viewmodel.DualViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalseplayers.Player1Fragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalseplayers.Player2Fragment
import igor.kuridza.ferit.hr.najboljiucenik.common.showAlertDialog


@AndroidEntryPoint
class DualActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int  = R.layout.activity_dual

    private val dualViewModel: DualViewModel by lazy {
        ViewModelProvider(this).get(DualViewModel::class.java)
    }

    override fun setUpUi() {
        val categoryType = intent.getStringExtra(CATEGORY_TYPE_KEY)!!

        supportFragmentManager.beginTransaction()
            .add(R.id.player1Container, Player1Fragment.newInstance())
            .add(R.id.player2Container, Player2Fragment.newInstance())
            .commit()

        dualViewModel.apply {
            getQuestions(categoryType)
            setFirstQuestion()
        }

        dualViewModel.gameOver.observe(this, Observer {gameOver->
            if(gameOver){
                startDualScoreActivity()
            }
        })
    }

    private fun startDualScoreActivity(){
        val intent = Intent(this, DualScoreActivity::class.java)
        intent.apply {
            putExtra(PLAYER1_SCORE_KEY, dualViewModel.player1Score.value)
            putExtra(PLAYER2_SCORE_KEY, dualViewModel.player2Score.value)
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
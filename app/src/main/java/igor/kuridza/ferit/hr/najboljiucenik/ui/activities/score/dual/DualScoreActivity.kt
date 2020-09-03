package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.dual

import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.PLAYER1_SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.PLAYER2_SCORE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_dual_score.*

class DualScoreActivity : BaseActivity() {

    private var mPlayer1Score: Int = 0
    private var mPlayer2Score: Int = 0

    override fun getLayoutResourceId(): Int = R.layout.activity_dual_score

    override fun setUpUi() {
        mPlayer1Score = intent.getIntExtra(PLAYER1_SCORE_KEY, 0)
        mPlayer2Score = intent.getIntExtra(PLAYER2_SCORE_KEY, 0)

        player2Score.text = mPlayer2Score.toString()
        player1Score.text = mPlayer1Score.toString()

        exit.onClick {
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
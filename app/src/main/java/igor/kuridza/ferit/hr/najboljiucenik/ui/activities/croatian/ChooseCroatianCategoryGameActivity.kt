package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.croatian

import android.content.Intent
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.proverb.view.ProverbGameActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.ScoreBoardActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.dual.view.DualActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.single.view.TrueFalseActivity
import kotlinx.android.synthetic.main.activity_choose_croatian_category_game.*

class ChooseCroatianCategoryGameActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_choose_croatian_category_game

    override fun setUpUi() {
        trueFalseSingle.onClick {
           startTrueFalseGameActivity()
        }

        trueFalseDual.onClick {
            startTrueFalseDualGameActivity()
        }

        scoreIcon.onClick {
            startScoreBoardActivity()
        }

        proverbsCategorySingle.onClick {
            startProverbGameActivity()
        }
    }

    private fun startProverbGameActivity(){
        val intent = Intent(this, ProverbGameActivity::class.java)
        startActivity(intent)
    }

    private fun startTrueFalseGameActivity(){
        val intent = Intent(this, TrueFalseActivity::class.java)
        intent.putExtra(CATEGORY_TYPE_KEY, CROATIAN_TRUE_FALSE)
        startActivity(intent)
    }

    private fun startTrueFalseDualGameActivity(){
        val intent = Intent(this, DualActivity::class.java)
        intent.putExtra(CATEGORY_TYPE_KEY, CROATIAN_TRUE_FALSE)
        startActivity(intent)
    }

    private fun startScoreBoardActivity(){
        val intent = Intent(this, ScoreBoardActivity::class.java)
        intent.putExtra(SUBJECT_KEY, CROATIAN_SUBJECT)
        startActivity(intent)
    }
}
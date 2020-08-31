package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.geography.choosecategory

import android.content.Intent
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.geography.view.FlagsActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.ScoreBoardActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.dual.view.DualActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.single.view.TrueFalseActivity
import kotlinx.android.synthetic.main.activity_choose_geography_game_category.*

class ChooseGeographyGameCategoryActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_choose_geography_game_category

    override fun setUpUi() {
        flagsCategorySingle.onClick {
            startFlagActivity()
        }

        trueFalseSingle.onClick {
            startTrueFalseActivity()
        }

        scoreIcon.onClick {
            startScoreBoardActivity()
        }

        trueFalseDual.onClick {
            startDualModeActivity()
        }
    }

    private fun startFlagActivity(){
        val intent = Intent(this, FlagsActivity::class.java)
        startActivity(intent)
    }

    private fun startTrueFalseActivity(){
        val intent = Intent(this, TrueFalseActivity::class.java)
        intent.putExtra(CATEGORY_TYPE_KEY, GEOGRAPHY_TRUE_FALSE)
        startActivity(intent)
    }

    private fun startScoreBoardActivity(){
        val intent = Intent(this, ScoreBoardActivity::class.java)
        intent.putExtra(SUBJECT_KEY, GEOGRAPHY_SUBJECT)
        startActivity(intent)
    }

    private fun startDualModeActivity(){
        val intent = Intent(this, DualActivity::class.java)
        intent.putExtra(CATEGORY_TYPE_KEY, GEOGRAPHY_TRUE_FALSE)
        startActivity(intent)
    }
}
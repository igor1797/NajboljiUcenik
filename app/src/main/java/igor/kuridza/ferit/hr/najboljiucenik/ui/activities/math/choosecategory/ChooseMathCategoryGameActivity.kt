package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.math.choosecategory

import android.content.Intent
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.math.view.MathGameActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.ScoreBoardActivity
import kotlinx.android.synthetic.main.activity_choose_math_category_game.*

class ChooseMathCategoryGameActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_choose_math_category_game

    override fun setUpUi() {
        additionSubtractionCategorySingle.onClick {
            startMathGame(ADDITION_SUBTRACTION)
        }

        multiplicationDivisionCategorySingle.onClick {
            startMathGame(DIVISION_MULTIPLICATION)
        }

        scoreIcon.onClick {
            startScoreBoardActivity()
        }
    }

    private fun startMathGame(categoryType: String){
        val intent = Intent(this, MathGameActivity::class.java)
        intent.putExtra(CATEGORY_TYPE_KEY, categoryType)
        startActivity(intent)
    }

    private fun startScoreBoardActivity(){
        val intent = Intent(this, ScoreBoardActivity::class.java)
        intent.putExtra(SUBJECT_KEY, MATH_SUBJECT)
        startActivity(intent)
    }
}
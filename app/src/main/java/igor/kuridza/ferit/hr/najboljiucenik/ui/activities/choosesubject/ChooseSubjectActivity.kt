package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.choosesubject

import android.content.Intent
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.math.choosecategory.ChooseMathCategoryGameActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.croatian.ChooseCroatianCategoryGameActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.geography.choosecategory.ChooseGeographyGameCategoryActivity
import kotlinx.android.synthetic.main.activity_chose_subject.*

class ChooseSubjectActivity : BaseActivity(){

    override fun getLayoutResourceId() = R.layout.activity_chose_subject

    override fun setUpUi() {
        math.onClick {
            startChooseMathCategoryGameActivity()
        }

        croatian.onClick {
            startChooseCroatianCategoryGameActivity()
        }

        geography.onClick {
            startChooseGeographyCategoryGameActivity()
        }
    }

    private fun startChooseMathCategoryGameActivity(){
        val intent = Intent(this, ChooseMathCategoryGameActivity::class.java)
        startActivity(intent)
    }

    private fun startChooseCroatianCategoryGameActivity(){
        val intent = Intent(this, ChooseCroatianCategoryGameActivity::class.java)
        startActivity(intent)
    }

    private fun startChooseGeographyCategoryGameActivity(){
        val intent = Intent(this, ChooseGeographyGameCategoryActivity::class.java)
        startActivity(intent)
    }
}
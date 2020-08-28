package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.admin

import android.view.MenuItem
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.showFragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.mathquestions.view.MathQuestionsFragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.proverbs.view.ProverbQuestionsFragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalsequestions.view.TrueFalseQuestionsFragment
import kotlinx.android.synthetic.main.activity_admin.*

@AndroidEntryPoint
class AdminActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_admin

    override fun setUpUi() {
        bottomNavigation.selectedItemId = R.id.item_math
        showFragment(R.id.fragmentContainer, MathQuestionsFragment.newInstance())
        bottomNavigation.setOnNavigationItemSelectedListener { openFragment(it) }
    }

    private fun openFragment(menuItem: MenuItem): Boolean {
        var selected : Fragment? = null
        when(menuItem.itemId){
            R.id.item_math -> selected = MathQuestionsFragment.newInstance()
            R.id.item_true_false -> selected = TrueFalseQuestionsFragment.newInstance()
            R.id.item_proverb -> selected = ProverbQuestionsFragment.newInstance()
        }
        if(selected!=null)
            showFragment(R.id.fragmentContainer, selected)
        return true
    }
}
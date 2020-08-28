package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score

import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.adapters.ScorePagerAdapter
import kotlinx.android.synthetic.main.activity_score_board.*

@AndroidEntryPoint
class ScoreBoardActivity : BaseActivity() {

    private lateinit var scorePagerAdapter: ScorePagerAdapter
    private var subjectName: String? = null

    override fun getLayoutResourceId(): Int = R.layout.activity_score_board

    override fun setUpUi() {
        subjectName = intent.getStringExtra(SUBJECT_KEY)
        scorePagerAdapter = ScorePagerAdapter(supportFragmentManager)
        checkSubjectName(subjectName!!)
        setUpViewPager()
    }

    private fun setUpViewPager(){
        viewPager.adapter = scorePagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun checkSubjectName(subjectName: String){
        val tabTitles = arrayListOf<String>()
        val categoryTypes = arrayListOf<String>()
        when(subjectName){
            MATH_SUBJECT -> {
                tabTitles.add("Zbrajanje/Oduzimanje")
                tabTitles.add("Množenje/Djeljenje")
                categoryTypes.add(ADDITION_SUBTRACTION)
                categoryTypes.add(DIVISION_MULTIPLICATION)
            }
            CROATIAN_SUBJECT ->{
                tabTitles.add("Poslovice")
                tabTitles.add("Točno/Netočno")
                categoryTypes.add(CROATIAN_PROVERB)
                categoryTypes.add(CROATIAN_TRUE_FALSE)
            }
            GEOGRAPHY_SUBJECT->{
                tabTitles.add("Zastave")
                tabTitles.add("Točno/Netočno")
                categoryTypes.add(GEOGRAPHY_FLAGS)
                categoryTypes.add(GEOGRAPHY_TRUE_FALSE)
            }
        }
        scorePagerAdapter.apply {
            setCategoryTypeList(categoryTypes)
            setTabTitles(tabTitles)
        }
    }
}
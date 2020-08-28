package igor.kuridza.ferit.hr.najboljiucenik.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.score.view.ScoreListFragment

class ScorePagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){

    private val categoryTypeList = arrayListOf<String>()
    private val tabTitles = arrayListOf<String>()

    override fun getCount(): Int {
        return categoryTypeList.size
    }

    override fun getItem(position: Int): Fragment {
        return ScoreListFragment.newInstance(categoryTypeList[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    fun setCategoryTypeList(list: List<String>){
        categoryTypeList.clear()
        categoryTypeList.addAll(list)
        notifyDataSetChanged()
    }

    fun setTabTitles(list: List<String>){
        tabTitles.clear()
        tabTitles.addAll(list)
        notifyDataSetChanged()
    }
}
package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.score.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.CATEGORY_TYPE_KEY
import igor.kuridza.ferit.hr.najboljiucenik.common.setDivider
import igor.kuridza.ferit.hr.najboljiucenik.model.PlayerScore
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.base.BaseFragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.score.viewmodel.ScoreListViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters.ScoreAdapter
import kotlinx.android.synthetic.main.fragment_score_list.*


@AndroidEntryPoint
class ScoreListFragment : BaseFragment(){

    private var categoryType: String? = null
    private lateinit var scoreAdapter: ScoreAdapter

    private val scoreListViewModel: ScoreListViewModel by lazy {
        ViewModelProvider(this).get(ScoreListViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_score_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryType = it.getString(CATEGORY_TYPE_KEY)
        }
    }

    override fun setUpUi() {
        setUpRecycler()
    }

    private fun setUpRecycler(){
        val query = scoreListViewModel.getScoreQueryForCategory(categoryType!!)

        val options = FirestoreRecyclerOptions.Builder<PlayerScore>()
            .setQuery(query, PlayerScore::class.java)
            .build()

        scoreAdapter = ScoreAdapter(options)

        scoreRecyclerView.apply {
            adapter = scoreAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            setDivider()
        }
        scoreAdapter.startListening()
    }

    companion object {
        @JvmStatic
        fun newInstance(categoryType: String) =
            ScoreListFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY_TYPE_KEY, categoryType)
                }
            }
    }
}
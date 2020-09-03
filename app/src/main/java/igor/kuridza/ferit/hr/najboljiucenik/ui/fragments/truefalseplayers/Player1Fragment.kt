package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalseplayers

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.databinding.FragmentPlayer1Binding
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.dual.viewmodel.DualViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.base.BaseFragment

@AndroidEntryPoint
class Player1Fragment : BaseFragment() {

    private lateinit var dualViewModel: DualViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_player1

    override fun setUpUi(){}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dualViewModel = ViewModelProvider(requireActivity()).get(DualViewModel::class.java)
        val binding: FragmentPlayer1Binding = DataBindingUtil.bind<FragmentPlayer1Binding>(view!!)!!
        // Bind layout with ViewModel
        binding.viewmodel = dualViewModel
        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = activity
    }

    companion object {
        @JvmStatic
        fun newInstance() = Player1Fragment()
    }
}
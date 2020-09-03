package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalseplayers

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.databinding.FragmentPlayer2Binding
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.truefalse.dual.viewmodel.DualViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.base.BaseFragment

@AndroidEntryPoint
class Player2Fragment : BaseFragment() {

    private lateinit var dualViewModel: DualViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_player2

    override fun setUpUi() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dualViewModel = ViewModelProvider(requireActivity()).get(DualViewModel::class.java)
        val binding: FragmentPlayer2Binding = DataBindingUtil.bind<FragmentPlayer2Binding>(view!!)!!
        // Bind layout with ViewModel
        binding.viewmodel = dualViewModel
        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = activity
    }

    companion object {
        @JvmStatic
        fun newInstance() = Player2Fragment()
    }

}
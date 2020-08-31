package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalsequestions.view

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.editquestion.view.EditTrueFalseQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.addnewquestion.view.AddNewTrueFalseQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.base.BaseFragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalsequestions.viewmodel.TrueFalseQuestionsViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters.TrueFalseQuestionAdapter
import kotlinx.android.synthetic.main.fragment_true_false_questions.*

@AndroidEntryPoint
class TrueFalseQuestionsFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    private lateinit var trueFalseQuestionAdapter: TrueFalseQuestionAdapter

    private val trueFalseQuestionsViewModel: TrueFalseQuestionsViewModel by lazy {
        ViewModelProvider(this).get(TrueFalseQuestionsViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_true_false_questions

    override fun setUpUi() {
        setSpinnerOptions()
        setUpRecycler(GEOGRAPHY_TRUE_FALSE)
        btnAddNewTrueFalseQuestion.onClick {
            showAddNewTrueFalseQuestionDialog()
        }
    }

    private fun setSpinnerOptions(){
        trueFalseCategoryTypeSpinner.setOptions(context!!, GEOGRAPHY_TRUE_FALSE, CROATIAN_TRUE_FALSE)
        trueFalseCategoryTypeSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position){
            0-> setUpRecycler(GEOGRAPHY_TRUE_FALSE)
            else -> setUpRecycler(CROATIAN_TRUE_FALSE)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun showAddNewTrueFalseQuestionDialog(){
        val dialog = AddNewTrueFalseQuestionFragmentDialog.newInstance()
        dialog.show(childFragmentManager, NEW_TRUE_FALSE_QUESTION_TAG)
    }

    private fun showEditTrueFalseQuestionDialog(documentSnapshot: DocumentSnapshot){
        val dialog = EditTrueFalseQuestionFragmentDialog.newInstance()
        dialog.setDocumentSnapshot(documentSnapshot)
        dialog.show(childFragmentManager, EDIT_TRUE_FALSE_QUESTION_TAG)

    }

    private fun setUpRecycler(categoryType: String){
        val query = trueFalseQuestionsViewModel.getFirebaseQuery().whereEqualTo("categoryGame", categoryType)

        val options = FirestoreRecyclerOptions.Builder<QuestionTrueFalse>()
            .setQuery(query, QuestionTrueFalse::class.java)
            .build()

        trueFalseQuestionAdapter = TrueFalseQuestionAdapter(
            options,
            {documentSnapshot -> onDeleteIconClicked(documentSnapshot)},
            {documentSnapshot -> onEditIconClicked(documentSnapshot) }
        )

        trueFalseQuestionsRecyclerView.apply {
            adapter = trueFalseQuestionAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        trueFalseQuestionAdapter.startListening()
    }

    private fun onDeleteIconClicked(documentSnapshot: DocumentSnapshot){
        showDeleteAlertDialog(activity, documentSnapshot){onPositiveButtonClicked(documentSnapshot)}
    }

    private fun onEditIconClicked(documentSnapshot: DocumentSnapshot){
        showEditTrueFalseQuestionDialog(documentSnapshot)
    }

    private fun onPositiveButtonClicked(documentSnapshot: DocumentSnapshot){
        trueFalseQuestionsViewModel.deleteTrueFalseQuestion(documentSnapshot)
    }

    companion object {
        fun newInstance() = TrueFalseQuestionsFragment()
    }
}
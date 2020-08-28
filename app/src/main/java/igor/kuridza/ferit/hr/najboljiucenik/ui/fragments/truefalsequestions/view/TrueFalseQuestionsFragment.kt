package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalsequestions.view

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.EDIT_TRUE_FALSE_QUESTION_TAG
import igor.kuridza.ferit.hr.najboljiucenik.common.NEW_TRUE_FALSE_QUESTION_TAG
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.common.showDeleteAlertDialog
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.editquestion.view.EditTrueFalseQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.addnewquestion.view.AddNewTrueFalseQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.base.BaseFragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalsequestions.viewmodel.TrueFalseQuestionsViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters.TrueFalseQuestionAdapter
import kotlinx.android.synthetic.main.fragment_true_false_questions.*

@AndroidEntryPoint
class TrueFalseQuestionsFragment : BaseFragment() {

    private lateinit var trueFalseQuestionAdapter: TrueFalseQuestionAdapter

    private val trueFalseQuestionsViewModel: TrueFalseQuestionsViewModel by lazy {
        ViewModelProvider(this).get(TrueFalseQuestionsViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_true_false_questions

    override fun setUpUi() {
        setUpRecycler()
        btnAddNewTrueFalseQuestion.onClick {
            showAddNewTrueFalseQuestionDialog()
        }
    }

    private fun showAddNewTrueFalseQuestionDialog(){
        val dialog = AddNewTrueFalseQuestionFragmentDialog.newInstance()
        dialog.show(childFragmentManager, NEW_TRUE_FALSE_QUESTION_TAG)
    }

    private fun showEditTrueFalseQuestionDialog(documentSnapshot: DocumentSnapshot){
        val dialog = EditTrueFalseQuestionFragmentDialog.newInstance()
        dialog.setDocumentSnapshot(documentSnapshot)
        dialog.show(childFragmentManager, EDIT_TRUE_FALSE_QUESTION_TAG)

    }

    private fun setUpRecycler(){
        val query = trueFalseQuestionsViewModel.getFirebaseQuery()

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
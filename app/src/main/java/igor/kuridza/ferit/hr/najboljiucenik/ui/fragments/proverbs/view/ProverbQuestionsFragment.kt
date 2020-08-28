package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.proverbs.view

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.addnewquestion.view.AddNewMathQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.editquestion.view.EditMathQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.addnewquestion.view.AddProverbQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.editquestion.view.EditProverbQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.base.BaseFragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.proverbs.viewmodel.ProverbQuestionsViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters.ProverbQuestionsAdapter
import kotlinx.android.synthetic.main.fragment_proverb_questions.*

@AndroidEntryPoint
class ProverbQuestionsFragment : BaseFragment() {

    private val proverbQuestionsViewModel: ProverbQuestionsViewModel by lazy {
        ViewModelProvider(this).get(ProverbQuestionsViewModel::class.java)
    }

    private lateinit var proverbQuestionAdapter: ProverbQuestionsAdapter

    override fun getLayoutResourceId(): Int = R.layout.fragment_proverb_questions

    override fun setUpUi() {
        setUpRecycler()

        btnAddProverbQuestion.onClick {
            showAddProverbQuestionDialog()
        }
    }

    private fun setUpRecycler(){
        val query = proverbQuestionsViewModel.getProverbFirebaseQuery()

        val options = FirestoreRecyclerOptions.Builder<ProverbQuestion>()
            .setQuery(query, ProverbQuestion::class.java)
            .build()

        proverbQuestionAdapter = ProverbQuestionsAdapter(
            options,
            {documentSnapshot -> onDeleteIconClicked(documentSnapshot)},
            {documentSnapshot -> onEditIconClicked(documentSnapshot) }
        )

        proverbQuestionsRecycler.apply {
            adapter = proverbQuestionAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        proverbQuestionAdapter.startListening()
    }

    private fun showAddProverbQuestionDialog() {
        val dialog = AddProverbQuestionFragmentDialog.newInstance()
        dialog.show(childFragmentManager, NEW_PROVERB_QUESTION_TAG)
    }

    private fun onDeleteIconClicked(documentSnapshot: DocumentSnapshot){
        showDeleteAlertDialog(activity, documentSnapshot){onPositiveButtonClicked(documentSnapshot)}
    }

    private fun onEditIconClicked(documentSnapshot: DocumentSnapshot){
        showEditProverbQuestionDialog(documentSnapshot)
    }

    private fun showEditProverbQuestionDialog(documentSnapshot: DocumentSnapshot){
        val dialog = EditProverbQuestionFragmentDialog.newInstance()
        dialog.setDocumentSnapshot(documentSnapshot)
        dialog.show(childFragmentManager, EDIT_PROVERB_QUESTION_TAG)

    }

    private fun onPositiveButtonClicked(documentSnapshot: DocumentSnapshot){
        proverbQuestionsViewModel.deleteProverbQuestion(documentSnapshot)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProverbQuestionsFragment()
    }
}
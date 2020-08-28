package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.mathquestions.view

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.addnewquestion.view.AddNewMathQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.editquestion.view.EditMathQuestionFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.base.BaseFragment
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.mathquestions.viewmodel.MathQuestionsViewModel
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters.MathQuestionAdapter
import kotlinx.android.synthetic.main.fragment_math_questions.*

@AndroidEntryPoint
class MathQuestionsFragment : BaseFragment() {

    private lateinit var mathQuestionAdapter: MathQuestionAdapter

    private val mathQuestionViewModel: MathQuestionsViewModel by lazy {
        ViewModelProvider(this).get(MathQuestionsViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_math_questions

    override fun setUpUi() {
        setUpRecycler(DIVISION_MULTIPLICATION)

        additionQuestions.onClick {
            setUpRecycler(ADDITION_SUBTRACTION)
        }

        btnAddNewMathQuestion.onClick {
            showAddNewMathQuestionDialog()
        }
    }

    private fun showAddNewMathQuestionDialog(){
        val dialog = AddNewMathQuestionFragmentDialog.newInstance()
        dialog.show(childFragmentManager, NEW_MATH_QUESTION_TAG)
    }

    private fun showEditMathQuestionDialog(documentSnapshot: DocumentSnapshot){
        val dialog = EditMathQuestionFragmentDialog.newInstance()
        dialog.setDocumentSnapshot(documentSnapshot)
        dialog.show(childFragmentManager, EDIT_MATH_QUESTION_TAG)

    }

    private fun setUpRecycler(categoryType: String){
        val query = mathQuestionViewModel.getFirebaseQuery().whereEqualTo("categoryGame", categoryType)

        val options = FirestoreRecyclerOptions.Builder<MathQuestion>()
            .setQuery(query, MathQuestion::class.java)
            .build()

        mathQuestionAdapter = MathQuestionAdapter(
            options,
            {documentSnapshot -> onDeleteIconClicked(documentSnapshot)},
            {documentSnapshot -> onEditIconClicked(documentSnapshot) }
        )

        mathQuestionsRecyclerView.apply {
            adapter = mathQuestionAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        mathQuestionAdapter.startListening()
    }

    private fun onDeleteIconClicked(documentSnapshot: DocumentSnapshot){
        showDeleteAlertDialog(activity, documentSnapshot){onPositiveButtonClicked(documentSnapshot)}
    }

    private fun onEditIconClicked(documentSnapshot: DocumentSnapshot){
        showEditMathQuestionDialog(documentSnapshot)
    }

    private fun onPositiveButtonClicked(documentSnapshot: DocumentSnapshot){
        mathQuestionViewModel.deleteMathQuestion(documentSnapshot)
    }

    companion object {
        fun newInstance() = MathQuestionsFragment()
    }
}
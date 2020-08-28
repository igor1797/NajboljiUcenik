package igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.math_question_item.*

class TrueFalseQuestionAdapter (
    options: FirestoreRecyclerOptions<QuestionTrueFalse>,
    private val onDeleteIconClicked: (DocumentSnapshot) -> Unit,
    private val onEditIconClicked: (DocumentSnapshot) -> Unit
): FirestoreRecyclerAdapter<QuestionTrueFalse, TrueFalseQuestionAdapter.TrueFalseQuestionHolder>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrueFalseQuestionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.true_false_question_item, parent, false)
        return TrueFalseQuestionHolder(view, onDeleteIconClicked, onEditIconClicked)
    }

    override fun onBindViewHolder(holder: TrueFalseQuestionHolder, position: Int, model: QuestionTrueFalse) {
        holder.bindItem(model)
    }

    inner class TrueFalseQuestionHolder(
        containerView: View,
        private val onIconDeleteClicked: (DocumentSnapshot) -> Unit,
        private val onIconEditClicked: (DocumentSnapshot) -> Unit
    ): BaseViewHolder<QuestionTrueFalse>(containerView){

        override fun bindItem(item: QuestionTrueFalse) {
            question.text = item.question
            answer.text = item.answer.toString()
            categoryType.text = item.categoryGame

            delete.onClick {
                onIconDeleteClicked(snapshots.getSnapshot(adapterPosition))
            }
            edit.onClick {
                onIconEditClicked(snapshots.getSnapshot(adapterPosition))
            }
        }
    }
}
package igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.math_question_item.*

class MathQuestionAdapter(
    options: FirestoreRecyclerOptions<MathQuestion>,
    private val onDeleteIconClicked: (DocumentSnapshot) -> Unit,
    private val onEditIconClicked: (DocumentSnapshot) -> Unit
): FirestoreRecyclerAdapter<MathQuestion, MathQuestionAdapter.MathQuestionHolder>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MathQuestionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.math_question_item, parent, false)
        return MathQuestionHolder(view, onDeleteIconClicked, onEditIconClicked)
    }

    override fun onBindViewHolder(holder: MathQuestionHolder, position: Int, model: MathQuestion) {
        holder.bindItem(model)
    }

    inner class MathQuestionHolder(
        containerView: View,
        private val onIconDeleteClicked: (DocumentSnapshot) -> Unit,
        private val onIconEditClicked: ( DocumentSnapshot) -> Unit
    ): BaseViewHolder<MathQuestion>(containerView){

        override fun bindItem(item: MathQuestion) {
            question.text = item.question
            answer.text = item.answer
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
package igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.proverb_question_item.*


class ProverbQuestionsAdapter (
    options: FirestoreRecyclerOptions<ProverbQuestion>,
    private val onDeleteIconClicked: (DocumentSnapshot) -> Unit,
    private val onEditIconClicked: (DocumentSnapshot) -> Unit
): FirestoreRecyclerAdapter<ProverbQuestion, ProverbQuestionsAdapter.ProverbQuestionHolder>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProverbQuestionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.proverb_question_item, parent, false)
        return ProverbQuestionHolder(view, onDeleteIconClicked, onEditIconClicked)
    }

    override fun onBindViewHolder(holder: ProverbQuestionHolder, position: Int, model: ProverbQuestion) {
        holder.bindItem(model)
    }

    inner class ProverbQuestionHolder(
        containerView: View,
        private val onIconDeleteClicked: (DocumentSnapshot) -> Unit,
        private val onIconEditClicked: (DocumentSnapshot) -> Unit
    ): BaseViewHolder<ProverbQuestion>(containerView){

        override fun bindItem(item: ProverbQuestion) {
            question.text = item.question
            answer.text = item.answer
            letters.text = item.letters

            delete.onClick {
                onIconDeleteClicked(snapshots.getSnapshot(adapterPosition))
            }
            edit.onClick {
                onIconEditClicked(snapshots.getSnapshot(adapterPosition))
            }
        }
    }
}
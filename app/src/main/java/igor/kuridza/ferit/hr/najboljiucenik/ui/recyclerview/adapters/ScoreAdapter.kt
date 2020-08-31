package igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.connect
import igor.kuridza.ferit.hr.najboljiucenik.common.visible
import igor.kuridza.ferit.hr.najboljiucenik.model.PlayerScore
import igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_score_list.*
import kotlinx.android.synthetic.main.score_item.*

class ScoreAdapter(
    options: FirestoreRecyclerOptions<PlayerScore>
): FirestoreRecyclerAdapter<PlayerScore, ScoreAdapter.ScoreHolder>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.score_item, parent, false)
        return ScoreHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreHolder, position: Int, model: PlayerScore) {
        holder.bindItem(model)
    }

    inner class ScoreHolder(
        containerView: View
    ): BaseViewHolder<PlayerScore>(containerView){

        override fun bindItem(item: PlayerScore) {
            nickname.text = item.nickname
            score.text = item.score.toString()
            scorePosition.text = (adapterPosition + 1).toString().connect(". Mjesto")
        }
    }
}
package igor.kuridza.ferit.hr.najboljiucenik.common

import android.view.View

fun View.onClick(onClick: () -> Unit){
    setOnClickListener { onClick() }
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

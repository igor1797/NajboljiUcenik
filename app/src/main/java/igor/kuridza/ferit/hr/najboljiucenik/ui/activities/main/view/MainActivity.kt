package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.main.view


import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.admin.AdminLoginActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.choosesubject.ChooseSubjectActivity
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.main.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import android.util.Pair as UtilPair

@AndroidEntryPoint
class MainActivity : BaseActivity(){

    private val mainViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        CoroutineScope(IO).launch {
            mainViewModel.getQuestions()
        }
        setAdminIconOnClickListener()
        setPlayIconOnClickListener()
        animatePlayButton()
    }

    private fun animatePlayButton(){
        play.apply {
            alpha = 0F
            animate().alpha(1F).duration = 2000;
        }

    }

    private fun setAdminIconOnClickListener(){
        adminIcon.onClick {
            startAdminLoginActivity()
        }
    }

    private fun setPlayIconOnClickListener(){
        play.onClick {
            startChooseSubjectActivity()
        }
    }

    private fun startAdminLoginActivity(){
        val intent = Intent(this, AdminLoginActivity::class.java)
        val option = ActivityOptions.makeSceneTransitionAnimation(this, adminIcon, "admin")
        startActivity(intent, option.toBundle())
    }

    private fun startChooseSubjectActivity(){
        val intent = Intent(this, ChooseSubjectActivity::class.java)
        val upEyePair = UtilPair.create<View?, String?>(upEye, "upEyeTransition")
        val downEyePair = UtilPair.create<View, String>(downEye, "downEyeTransition")
        val options = ActivityOptions.makeSceneTransitionAnimation(this, upEyePair, downEyePair)
        startActivity(intent, options.toBundle())
    }
}
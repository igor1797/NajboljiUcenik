package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.admin

import android.content.Intent
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_admin_login.*

class AdminLoginActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_admin_login

    override fun setUpUi() {
        login.onClick {
            val mNickname = nickname.text.toString()
            val mPassword = password.text.toString()

            if(!inputsAreEmpty(mNickname, mPassword)){
                login(mNickname, mPassword)
            }
        }
    }

    private fun inputsAreEmpty(mNickname: String, mPassword: String): Boolean{
        if(mNickname.isEmpty()){
            nickname.error = getString(R.string.errorForEmptyFiels)
            return true
        }

        if(mPassword.isEmpty()){
            password.error = getString(R.string.errorForEmptyFiels)
            return true
        }

        return false
    }

    private fun login(nickname: String, password: String){
        if(nickname == "Admin" && password == "Ferit1978admiN"){
            startAdminActivity()
            finish()
        }
    }

    private fun startAdminActivity(){
        val intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
    }
}
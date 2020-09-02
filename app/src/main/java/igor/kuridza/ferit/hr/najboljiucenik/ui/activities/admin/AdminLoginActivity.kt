package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.admin

import android.content.Intent
import android.widget.Toast
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_admin_login.*
import java.io.FileInputStream
import java.util.*

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

    private fun getPropertiesFile(): Properties{
        val file = assets.open("admin.properties")
        val properties = Properties()
        properties.load(file)
        return properties
    }

    private fun getAdminPassword(): String{
        val prop = getPropertiesFile()
        return prop.getProperty("ADMIN_PASSWORD")
    }

    private fun getAdminNickname(): String{
        val prop = getPropertiesFile()
        return prop.getProperty("ADMIN_USER_NAME")
    }

    private fun login(nickname: String, password: String){
        if(nickname == getAdminNickname()  && password == getAdminPassword()){
            startAdminActivity()
            finish()
        }else{
            Toast.makeText(this, getString(R.string.wrongAdminInputsInfo), Toast.LENGTH_SHORT).show()
        }
    }

    private fun startAdminActivity(){
        val intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
    }
}
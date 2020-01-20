package com.example.weathercheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.account_delete_dialog.*
import kotlinx.android.synthetic.main.activity_account_setting.*

class AccountSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_setting)

        setupListener()
    }

    private fun setupListener(){
        account_setting_back.setOnClickListener {onBackPressed()} // 뒤로가기 기능
        account_setting_logout.setOnClickListener {logoutAccount()}
        account_setting_delete.setOnClickListener {showDeleteDialog()}

    }
        private fun logoutAccount() {
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener{
                    moveToMainActivity()
                    Toast.makeText(this,"로그아웃 성공",Toast.LENGTH_SHORT).show()

                }
    }

    private fun deleteAccount(){
            AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener{
                    moveToMainActivity()
                    Toast.makeText(this,"탈퇴 성공",Toast.LENGTH_SHORT).show()
                }
    }
    private fun showDeleteDialog(){
        AccountDeleteCheckDialog().apply {
            addAccountDeleteDialogInterface(object :AccountDeleteCheckDialog.AccountDeleteDialogInterface{
                override fun deleteYes() {
                     deleteAccount()
                }
                override fun deleteNo() {}
            })
        }.show(supportFragmentManager,"")
    }
    private fun moveToMainActivity(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}

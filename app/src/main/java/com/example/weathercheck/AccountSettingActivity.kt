package com.example.weathercheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_account_setting.*

class AccountSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_setting)
        account_setting_back.setOnClickListener {onBackPressed()} // 뒤로가기 기능
        account_setting_logout.setOnClickListener {logoutAccount()}
        account_setting_delete.setOnClickListener {deleteAccount()}

    }
    private fun logoutAccount() {
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener{}
    }

    private fun deleteAccount(){
            AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener{}
    }
}

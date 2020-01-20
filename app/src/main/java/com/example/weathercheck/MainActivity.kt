package com.example.weathercheck

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN=1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPreviousLogin()
    }

    private fun checkPreviousLogin(){
        val user=FirebaseAuth.getInstance().currentUser
        if(user==null) showLoginWindow()
        else moveToOpenWeatherActivity()
    }

    private fun moveToOpenWeatherActivity(){
        startActivity(Intent(this,OpenWeatherActivity::class.java))
    }
    private fun showLoginWindow(){
        val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build())

// Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.GreenTheme)
                .setLogo(R.drawable.main_icon)
                .build(),
            RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                //로그인 성공 시
                startActivity(Intent(this,OpenWeatherActivity::class.java))
                // ...
            } else {
                Toast.makeText(this,"로그인을 다시 시도해 주세요.", Toast.LENGTH_LONG).show()
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}

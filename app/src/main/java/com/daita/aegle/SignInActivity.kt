package com.daita.aegle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cotter.app.IdentityManager
import org.json.JSONObject

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        if(intent != null){
            Log.i("Cotter response", "not null")
//            var resp: String = IdentityManager.handleResponse(this.intent);
            var resp: String = "";
            if (this.intent.hasExtra("TOKEN_RESPONSE")) {
                resp =  this.intent.getStringExtra("TOKEN_RESPONSE");
                Log.i("Login response: ", resp)
            }
            if(!resp.equals("")){
                Log.i("Login response: ", resp)
                var obj = JSONObject(resp);
                var uid = obj.getJSONObject("user").getString("ID")
                var accessToken = obj.getJSONObject("oauth_token").getString("access_token")
            }

            var error: String = "";
            if (this.intent.hasExtra("TOKEN_ERROR")) {
                var error: String =  this.intent.getStringExtra("TOKEN_ERROR");
                Log.i("Login error: ", resp)
            }
        } else {
            Log.i("Cotter response: ", "nothing")
        }

        startActivity(Intent(this, UserHomepage::class.java))

    }
}
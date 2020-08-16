package com.daita.aegle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cotter.app.Cotter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select_patient.setOnClickListener {
            Cotter.init(this.getApplicationContext(),
                "https://www.cotter.app/api/v0",
                "9628578b-d02d-413d-a185-83526763bdf5");
            Cotter.newIdentity(this, "com.daita.aegle://auth_callback").login("EMAIL", this, SignInActivity::class.java)
        }
    }
}
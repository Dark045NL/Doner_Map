package com.example.doner_map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivityProfile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profile)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this , gso)

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")

        findViewById<TextView>(R.id.textView).text = email + "\n" + displayName

        findViewById<Button>(R.id.SIgnOutBtn).setOnClickListener{
            googleSignInClient.signOut()
            startActivity(Intent(this , MainActivityLogin::class.java))
        }
        val ivan = findViewById<Button>(R.id.ivan)

        ivan.setOnClickListener {
            // Define the action to perform when the button is clicked.
            // For example, you can start another activity.
            val intent = Intent(this, Smaakprofiel::class.java)
            startActivity(intent)
        }
    }
}
package com.example.doner_map

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase

class Smaakprofiel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smaakprofiel)


        // Initialize Firebase Database and get the current user's UID
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid
        val databaseReference = FirebaseDatabase.getInstance("https://donermap-default-rtdb.europe-west1.firebasedatabase.app").reference.child("users").child(uid!!)

        val buttonSave = findViewById<Button>(R.id.buttonSave)

        buttonSave.setOnClickListener {


            // Retrieve the state of each checkbox and store it in the list
            for (i in 1..16) {
                val checkboxId = "checkBox$i"
                val checkBox = findViewById<CheckBox>(resources.getIdentifier(checkboxId, "id", packageName))
                val checkBoxText = checkBox.text.toString()
                //upload checkbox states as checkboxdata and the text of said checkboxes
                databaseReference.child("Smaakprofiel").child(checkboxId).child("state").setValue(checkBox.isChecked)
                databaseReference.child("Smaakprofiel").child(checkboxId).child("checkboxText").setValue(checkBoxText)
            }

            // Start the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optionally, you can finish the current activity to prevent going back to it with the back button
        }

        // Attach a ValueEventListener to listen for changes in Firebase
        databaseReference.child("Smaakprofiel").addValueEventListener(object : ValueEventListener {
            val databaseError = FirebaseDatabase.getInstance("https://donermap-default-rtdb.europe-west1.firebasedatabase.app").reference.child("users").child("Errors")
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Loop through the data and update checkboxes
                    for (i in 1..16) {
                        val checkBoxId = "checkBox$i" // Use checkBoxId instead of checkboxId
                        val checkBox = findViewById<CheckBox>(resources.getIdentifier(checkBoxId, "id", packageName))

                        // Check if the checkbox exists in the Firebase data
                        if (snapshot.child(checkBoxId).exists()) {
                            val isChecked = snapshot.child(checkBoxId).child("state").getValue(Boolean::class.java)
                            checkBox.isChecked = isChecked ?: false
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                databaseError.child("ErrorInsert").setValue("Error inserting into database.")
            }
        })



    }
}

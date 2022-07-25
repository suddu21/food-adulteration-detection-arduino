package com.example.firebasetest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

public lateinit var txtdata : TextView
class Getdatapage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getdatapage)

        var user : String? = getIntent().getStringExtra("user")
        val database = FirebaseDatabase.getInstance("your firebase rtdb url")
        val myRef = database.getReference("/Users")

        var btnUpdate : Button = findViewById(R.id.btnUpdate)
        txtdata= findViewById(R.id.txtDataup)

        btnUpdate.setOnClickListener {

            var key=myRef.push().key
            if (key != null) {
                if (user != null) {
                    myRef.child(key).child(user).setValue("Changed")
                }
            }

        }

    }
}
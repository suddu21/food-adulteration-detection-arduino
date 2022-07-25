package com.example.firebasetest

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var username : EditText=findViewById(R.id.username)
        var password : EditText=findViewById(R.id.password)
        var upload: Button = findViewById(R.id.btnUpload)
        var datapage: Button = findViewById(R.id.btnDatapage)
        val database = FirebaseDatabase.getInstance("your firebase rtdb url")
        database.goOnline()


        upload.setOnClickListener {
            //myRef.setValue(username.text.toString())
            //myRef.push().setValue(username.text.toString())
            var txtuser=username.text.toString()
            var txtpass=password.text.toString()
            var data = mapOf("Food" to txtuser, "Adulterant" to txtpass)
            val currentDateTime = LocalDateTime.now()
            //val myRef = database.getReference(("/Inventory/"+txtuser+"/"))
            val myRef = database.getReference().child("Inventory").child(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).child(txtuser)
            //val defRef = database.getReference()
            //var passwords= database.getReference(("User-"+username.text.toString()))
            //passwords.push().setValue(username.text.toString())
            //passwords.push().setValue(password.text.toString())
            myRef.setValue(data)
            //defRef.push().setValue(100)
        }

        //val storage=FirebaseStorage.getInstance()
        //val storageRef = storage.reference

        datapage.setOnClickListener {
            var username=username.text.toString()
            var intent= Intent(this, Getdatapage::class.java).putExtra("user",username)
            startActivity(intent)
        }
    }
}
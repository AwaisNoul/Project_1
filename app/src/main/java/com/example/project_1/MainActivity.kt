package com.example.project_1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var edittext :EditText
    lateinit var btn: Button
    lateinit var ref: DatabaseReference
    lateinit var notesList: ArrayList<NoteModel>

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ref = FirebaseDatabase.getInstance().reference.child("user")
        edittext = findViewById(R.id.edittext)
        btn = findViewById(R.id.button)
        notesList = ArrayList()
        btn.setOnClickListener {
            val txt = edittext.text.toString()
            val key = ref.push().key
                    ref.child(key!!).setValue(NoteModel("title" , txt , key))
        }
        FirebaseDatabase.getInstance().reference.child("user").get().addOnSuccessListener {
            for (noteSnap in it.children){
                val note = noteSnap.getValue(NoteModel::class.java)
                notesList.add(note!!)
            }

        }

    }
}














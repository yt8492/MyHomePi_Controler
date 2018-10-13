package com.yt8492.androidthingscontroler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var switchStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()
        switch1.setOnCheckedChangeListener { _, isChecked ->
            switchStatus = isChecked
        }
        button.setOnClickListener { _ ->
            val segmentNum = segmentValue.text.toString().toIntOrNull()
            val status = mapOf<String, Any?>(
                    "led" to switchStatus,
                    "segment" to segmentNum
            )
            db.collection("status").document(Date().time.toString())
                    .set(status)
                    .addOnSuccessListener {
                        toast("Success!")
                    }
                    .addOnFailureListener {
                        toast("Failed")
                    }
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}

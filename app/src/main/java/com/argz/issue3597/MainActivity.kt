package com.argz.issue3597

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addData(view: View){
        val database = Firebase.database
        val testRef = database.getReference("tests")
        testRef.setValue(mapOf(
            "yo" to mapOf("search" to 2),
            "so" to mapOf("search" to 3),
            "to" to mapOf("search" to 4),
            "fu" to mapOf("search" to 1),
            "bo" to mapOf("search" to 5),
        ))
        Toast.makeText(this, "Update complete", Toast.LENGTH_SHORT).show()
    }

    fun test(view: View){
        val database = Firebase.database
        val testRef = database.getReference("tests")

        testRef.orderByChild("search").startAfter((3).toDouble()).get().addOnSuccessListener { snapshot ->
            Toast.makeText(this, "$snapshot", Toast.LENGTH_SHORT).show()
            snapshot.children.forEach { child ->
                Log.d(TAG, "test: $child")
            }
        }
        .addOnFailureListener{
            Log.d(TAG, "exception: $it")
        }
    }
}
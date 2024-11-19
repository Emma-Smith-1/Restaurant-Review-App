package com.example.restaurantreviewapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantreviewapp.databinding.ActivitySignInBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.notRegistered.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.signInButton.setOnClickListener {
            val emailAddress = binding.email.text.toString()
            val password = binding.pass.text.toString()

            if (emailAddress.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            val errorMessage = it.exception?.message ?:
                            R.string.unknown_error.toString()
                            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                        }
                    }
            } else {
                Snackbar.make(binding.root, R.string.empty_fields, Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.guestModeButton.setOnClickListener {
            val guestModeIntent = Intent(this, GuestMainActivity::class.java)
            startActivity(guestModeIntent)
        }
        Log.i("mySignIn", "in onCreate")
    }

    override fun onStart() {
        super.onStart()

        firebaseAuth.currentUser?.let {
            startActivity(Intent(this, MainActivity::class.java))
        }
        Log.i("mySignIn", "in onStart")
    }

    fun guestModeClick() {
        val guestModeIntent = Intent(this, GuestMainActivity::class.java)
        startActivity(guestModeIntent)
    }



}

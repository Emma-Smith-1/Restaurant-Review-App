package com.example.restaurantreviewapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantreviewapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity :AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.alreadyRegistered.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.signUpButton.setOnClickListener {
            val emailAddress = binding.email.text.toString()
            val password = binding.pass.text.toString()
            val confirmPassword = binding.confirmPass.text.toString()

            if (emailAddress.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {

                    firebaseAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show()
            }
        }
        Log.i("mySignUp", "in onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("mySignUp", "in onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("mySignUp", "in onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("mySignUp", "in onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("mySignUp", "in onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("mySignUp", "in onDestroy")
    }
}
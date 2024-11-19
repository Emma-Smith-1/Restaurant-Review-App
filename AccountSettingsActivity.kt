package com.example.restaurantreviewapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AccountSettingsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.account_settings_layout)

        Log.i("myAccount", "in onCreate")

        val logOutButton = findViewById<Button>(R.id.logOutButton)
        logOutButton.setOnClickListener{v -> logOutClick(v)}

        val deleteAccountButton = findViewById<Button>(R.id.deleteAccountButton)
        deleteAccountButton.setOnClickListener{ v -> deleteAccountClick(v)}

    }

    fun logOutClick(view : View?) {
        Log.i("myAccount", "in logOutClick")
        try {
            auth = Firebase.auth
            Firebase.auth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.i("myAccount", "null input")
        }
    }

    fun deleteAccountClick(view: View?) {
        Log.i("myAccount", "in deleteAccountClick")
        try {
            auth = Firebase.auth
            val user = auth.currentUser

            if (user != null) {
                user.delete().addOnCompleteListener {task ->
                    if (task.isSuccessful) {
                        Log.i("myAccount", "Account deleted successfully")
                        val intent = Intent(this, SignInActivity::class.java)
                        Snackbar.make(view!!, "Account successfully deleted", Snackbar.LENGTH_LONG).show()
                        startActivity(intent)
                        finish()
                    } else {
                        Snackbar.make(view!!, "Failed to delete account", Snackbar.LENGTH_LONG).show()
                        Log.i("myAccount", "Failed to delete account")
                    }
                }
            } else {
                Snackbar.make(view!!, "User null", Snackbar.LENGTH_LONG).show()
                Log.i("myAccount", "User is null")
            }
        } catch (e : Exception) {
            Snackbar.make(view!!, "Exception ${e.message}", Snackbar.LENGTH_LONG).show()
            Log.i("myAccount", "Exception: ${e.message}")
        }
    }

    /*fun showChangePasswordDialog(view: View?) {
    val dialogView = LayoutInflater.from(view.context).inflate(R.layout.change_password, null)
    val newPasswordEditText = dialogView.findViewById<EditText>(R.id.newPasswordEditText)
    val changePasswordButton = dialogView.findViewById<Button>(R.id.changePasswordButton)

    val dialog = AlertDialog.Builder(this).setView(dialogView).setTitle("Change Password")
        .setCancelable(true).create()

    changePasswordButton.setOnClickListener {
        val newPassword = newPasswordEditText.text.toString()
        if (newPassword.isNotEmpty()) {
            //changePassword(newPassword)
            dialog.dismiss()
        } else {
            Snackbar.make(view, "Please enter a new password", Snackbar.LENGTH_LONG).show()
        }
    }
    dialog.show()
}*/

}
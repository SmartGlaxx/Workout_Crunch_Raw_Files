package com.smart_egbuchulem.workout_crunch
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.GoogleAuthProvider
//import com.google.firebase.database.FirebaseDatabase
//import com.smart_egbuchulem.workout_crunch.models.User
//
//class SignUpActivity : AppCompatActivity() {
//
//    private lateinit var googleSignInClient: GoogleSignInClient
//    private lateinit var auth: FirebaseAuth
//    private val RC_SIGN_IN = 40
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)
//
//        auth = FirebaseAuth.getInstance()
//
//        // Configure Google Sign-In
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        googleSignInClient = GoogleSignIn.getClient(this, gso)
//
//        // Button to initiate sign-in
//        findViewById<Button>(R.id.button2).setOnClickListener {
//            Log.d("SignUpActivity", "Authentication button click")
//            signIn()
//        }
//    }
//
//    private fun signIn() {
//        Log.d("SignUpActivity", "Authentication Sign in")
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//    }
//
////    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////        super.onActivityResult(requestCode, resultCode, data)
////
////        if (requestCode == RC_SIGN_IN) {
////            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
////            try {
////                Log.d("SignUpActivity", "Authentication try")
////                val account = task.getResult(ApiException::class.java)!!
////                firebaseAuthWithGoogle(account.idToken!!)
////            } catch (e: ApiException) {
////                Log.w("SignUpActivity", "Google sign-in failed", e)
////            }
////        }
////    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        Log.d("SignUpActivity", "Authentication func")
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    Log.d("SignUpActivity", "Authentication Success")
//                    // Sign-in success
//                    val user = auth.currentUser
//                    val userRecord = user?.let {
//                        User(it.uid, it.displayName ?: "Anonymous", it.photoUrl?.toString() ?: "")
//                    }
//                    FirebaseDatabase.getInstance().getReference("User")
//                        .child(user!!.uid).setValue(userRecord)
//                    // Navigate to Main Activity
//                    startActivity(Intent(this, MainActivity::class.java))
//                    finish()
//                } else {
//                    // Sign-in failed
//                    Log.d("SignUpActivity", "Authentication Failed: ${task.exception}")
//                }
//            }
//    }
//}


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.smart_egbuchulem.workout_crunch.models.User

class SignUpActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    // ActivityResultLauncher for handling the Google Sign-In intent result
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("SignUpActivity", "Google sign-in failed", e)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Button to initiate sign-in
        findViewById<Button>(R.id.button2).setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userRecord = user?.let {
                        User(it.uid, it.displayName ?: "Anonymous", it.photoUrl?.toString() ?: "")
                    }
                    FirebaseDatabase.getInstance().getReference("User")
                        .child(user!!.uid).setValue(userRecord)

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.w("SignUpActivity", "Authentication failed: ${task.exception}")
                }
            }
    }
}

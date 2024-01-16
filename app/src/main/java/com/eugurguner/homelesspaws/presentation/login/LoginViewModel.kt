package com.eugurguner.homelesspaws.presentation.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {
    fun signInUser(email: String, password: String, onResponse: (Boolean) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
                if (it.result.signInMethods.isNullOrEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { signTask ->
                                onResponse.invoke(signTask.isSuccessful)
                            }
                        } else {
                            onResponse.invoke(false)
                        }
                    }.addOnFailureListener {
                        onResponse.invoke(false)
                    }
                } else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        onResponse.invoke(task.isSuccessful)
                    }
                }
            }.addOnFailureListener {
                onResponse.invoke(false)
            }
        }
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}
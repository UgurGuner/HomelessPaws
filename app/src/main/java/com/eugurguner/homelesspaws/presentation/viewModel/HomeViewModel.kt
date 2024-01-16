package com.eugurguner.homelesspaws.presentation.viewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.eugurguner.homelesspaws.data.model.Dog
import com.eugurguner.homelesspaws.presentation.splash.ActivitySplash
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val db: FirebaseFirestore, private val firebaseAuth: FirebaseAuth) : ViewModel() {
    private val refreshTrigger = MutableLiveData<Unit>()

    val dogs =
        liveData {
            val snapshot = db.collection("dogs").get().await()
            val dogs = snapshot.toObjects(Dog::class.java)
            emit(dogs.shuffled().take(5))
        }

    fun logOut(context: Context) {
        firebaseAuth.signOut()
        Intent(context, ActivitySplash::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(this)
        }
        (context as? Activity)?.finish()
    }

    fun refreshDogs() {
        refreshTrigger.value = Unit
    }
}
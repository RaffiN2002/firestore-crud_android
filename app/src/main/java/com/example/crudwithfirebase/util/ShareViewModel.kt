package com.example.crudwithfirebase.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.navigation.NavController


class ShareViewModel(): ViewModel() {

    fun saveData(
        User: User,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(User.userID)

        try {

            fireStoreRef.set(User)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully Saved Data", Toast.LENGTH_SHORT).show()

                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        userID: String,
        context: Context,
        data: (User) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {

            fireStoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val User = it.toObject<User>()!!
                        data(User)
                    } else{
                        Toast.makeText(context,"No User Data Available",Toast.LENGTH_SHORT).show()
                    }

                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun deleteData(
        userID: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {

            fireStoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully Deleted Data", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()

                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
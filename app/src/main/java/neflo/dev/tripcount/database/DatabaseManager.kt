package neflo.dev.tripcount.database

import android.content.res.Resources
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import neflo.dev.tripcount.R
import neflo.dev.tripcount.exceptions.EntityExistsException
import neflo.dev.tripcount.exceptions.UnavailableInformationException
import neflo.dev.tripcount.exceptions.UnexpectedError
import neflo.dev.tripcount.model.UserModel
import neflo.dev.tripcount.util.userCollection
import java.security.InvalidParameterException

class DatabaseManager {

    val db = Firebase.firestore
    val emailField = "email"

    fun getUserByEmail(email: String, resources : Resources) : UserModel? {
        var user: UserModel? = null
        db.collection(userCollection)
            .whereEqualTo(emailField, email)
            .get()
            .addOnSuccessListener { result ->
                val userResult = result.toObjects(UserModel::class.java)
                if (userResult.size > 1){
                    throw UnavailableInformationException(resources)
                }
                user = userResult[0]
                user?.let { Log.d("DB-RETRIEVED", "${it.name} => ${it.email}") }
            }
            .addOnFailureListener { exception ->
                Log.e("DB-ERROR", "Error retrieving user.", exception)
                throw UnavailableInformationException(resources)
            }
        return user
    }

    fun addUser(user: UserModel, resources : Resources) {
        if (user.name == null || user.email == null){
            throw InvalidParameterException(resources.getString(R.string.name_email_required))
        }

        db.collection(userCollection)
            .whereEqualTo(emailField, user.email)
            .get()
            .addOnSuccessListener { result ->
                if (result.size() > 0){
                    throw EntityExistsException(resources.getString(R.string.email_in_use))
                } else{
                    saveUser(user, resources)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("DB-ERROR", "Error retrieving existing user.", exception)
            }

    }

    private fun saveUser(user: UserModel, resources: Resources) {
        db.collection(userCollection).add(user.userToHashMap())
            .addOnSuccessListener { documentReference ->
                Log.d("DB-SAVED", "User added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { exception ->
                Log.e("DB-ERROR", "Error saving user.", exception)
                throw UnexpectedError(resources.getString(R.string.sign_up_error))
            }
    }

    fun updateUser(user: UserModel, resources: Resources){
        val userResult = db.collection(userCollection)
            .whereEqualTo(emailField, user.email)
            .get()
            .getResult()

        if (userResult.documents.size > 1 || userResult.documents[0] == null){
            throw UnavailableInformationException(resources)
        }
        db.collection(userCollection)
            .document(userResult.documents[0].id)
            .update(user.userToHashMap())
            .addOnSuccessListener {
                Log.d("DB-UPDATED", "User with email \"${user.email}\" updated.")
            }
            .addOnFailureListener { exception ->
                Log.e("DB-ERROR", "Error updating user.", exception)
                throw UnexpectedError(resources.getString(R.string.update_error))
            }
    }

    fun removeUser(email: String, resources: Resources){
        val userResult = db.collection(userCollection)
            .whereEqualTo(emailField, email)
            .get()
            .getResult()

        if (userResult.documents.size > 1 || userResult.documents[0] == null){
            throw UnavailableInformationException(resources)
        }
        db.collection(userCollection)
            .document(userResult.documents[0].id)
            .delete()
            .addOnSuccessListener {
                Log.d("DB-REMOVED", "User with email \"${email}\" removed")
            }
            .addOnFailureListener { exception ->
                Log.e("DB-ERROR", "Error removing user.", exception)
                throw UnexpectedError(resources.getString(R.string.update_error))
            }
    }

}
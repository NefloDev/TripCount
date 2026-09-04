package neflo.dev.tripcount.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
fun encrypt256(password: String): String {
    val crypt = MessageDigest.getInstance("SHA-256")
    crypt.reset()
    crypt.update(password.toByteArray(charset("UTF-8")))
    return BigInteger(1, crypt.digest()).toString(16)
}

fun base64ToBitmap(base64: String): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(base64, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(
            decodedBytes,
            0,
            decodedBytes.size
        )
    } catch (e: IllegalArgumentException) {
        null
    }
}
package neflo.dev.tripcount.util

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
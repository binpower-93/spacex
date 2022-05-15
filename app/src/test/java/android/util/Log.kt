@file:JvmName("Log")

package android.util

fun e(tag: String, msg: String, throwable: Throwable): Int {
    println("ERROR: $tag: $msg")
    throwable.printStackTrace()

    return 0
}

fun e(tag: String, msg: String): Int {
    println("ERROR: $tag: $msg")
    return 0
}
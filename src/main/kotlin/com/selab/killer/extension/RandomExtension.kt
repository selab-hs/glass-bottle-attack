package com.selab.killer.extension

val STRING_CHARSET = ('0'..'9') + ('a'..'z') + ('A'..'Z')

val NUMBER_CHARSET = ('0'..'9')

fun randomString(length: Int): String {
    return List(length) { STRING_CHARSET.random() }.joinToString()
}

fun randomNumber(length: Int): Long {
    return List(length) { NUMBER_CHARSET.random() }.joinToString().toLong()
}

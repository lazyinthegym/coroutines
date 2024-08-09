package org.example

import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun foo() {
    println("Before")

    // Suspend this coroutine, creating a suspend point
    suspendCoroutine<Unit> { continuation ->
        // This block will run right before suspension
        println("Inside suspendCoroutine")

        // continuation is like a "save" in a game
        // It can be used to resume the suspended coroutine, without calling resume, the coroutine will never resume
        continuation.resume(Unit)
    }

    println("After")
}

fun main() {

    runBlocking {
        foo()
    }

}
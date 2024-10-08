import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Shows how to suspend a coroutine, and then continue it
 */
suspend fun suspendAndContinue() {
    println("Before")

    // Suspend this coroutine, and captures the continuation object
    suspendCoroutine<Unit> { continuation ->
        // This block will run right before suspension
        println("Inside suspendCoroutine")

        // continuation is like a "save" in a game
        // It captures the current state of the coroutine
        // It can be used to resume the suspended coroutine. Without calling resume, the coroutine will never resume
        continuation.resume(Unit)
    }

    println("After")
}


/**
 * Shows how to resume a coroutine with a value
 */
suspend fun resumeWithValue() {
    println("Before")

    // Suspend this coroutine, and then resume with a value
    val resumeValue: Int = suspendCoroutine<Int> { continuation ->
        println("Inside suspendCoroutine")

        // We can resume the coroutine with a value, to be returned after resuming from suspension
        continuation.resume(42)
    }

    println("After: Resumed with value $resumeValue")
}

/**
 * The caller of this function will suspend until the user is returned. And then return it as a value
 */
suspend fun requestUser(): String = suspendCoroutine<String> { continuation ->
    // Simulate a network request that returns a user
    continuation.resume("John Doe");
}

/**
 * Shows how to resume a coroutine with an exception
 */
suspend fun resumeWithException() {
    println("Before")

    // Suspend this coroutine, and then resume with an exception
    suspendCoroutine<Unit> { continuation ->
        println("Inside suspendCoroutine")

        // We can resume the coroutine with an exception
        continuation.resumeWithException(Exception("Something went wrong"))
    }
}

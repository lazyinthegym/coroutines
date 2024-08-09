import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

var continuation: Continuation<Unit>? = null

/**
 * When you suspend, you don't suspend a function, you suspend a coroutine.
 */

private suspend fun foo() {
    println("Before")

    suspendCoroutine<Unit> { c ->
        continuation = c
    }

    println("After")
}

suspend fun callerWontWork() {

    // This will suspend the coroutine, and block here
    foo()

    // This is won't be called, because when foo is suspended, it suspends the whole coroutine, including this caller.
    continuation?.resume(Unit)
}

suspend fun callerWorks() {
    thread {
        Thread.sleep(2000)
        continuation?.resume(Unit)
    }

    // This will work. The coroutine is suspended, but the thread will resume it using the continuation.
    foo()
}



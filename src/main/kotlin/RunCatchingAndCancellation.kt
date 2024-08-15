import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Shows that runCatching will catch the cancellation exception thrown by the coroutine
 *
 * **Explanation**:
 *
 * **Job Launch**: The coroutine is launched and starts working, printing a message every 500 milliseconds.
 *
 * **Cancellation**: After 1.5 seconds (delay(1500)), the main function cancels the job using job.cancelAndJoin().
 *
 * **runCatching**: The coroutine is wrapped inside runCatching, which catches all exceptions, including CancellationException.
 *
 * **Swallowed Exception**: Since CancellationException is caught by runCatching and not rethrown,
 * the coroutine continues to the next lines of code instead of terminating.
 * This is demonstrated by the message "This will still print even if the job is cancelled.",
 * which will still be printed even after the job is canceled.
 */
fun runCatchingDontCancel() = runBlocking {
    val job = launch {
        runCatching {
            repeat(1000) { i ->
                println("Job is working $i ...")
                delay(500)
            }
        }.onFailure {
            println("onFailure: e.message=${it.message}")
            // No rethrowing of CancellationException
        }
        println("This will still print even if the job is cancelled.")
    }

    delay(1500) // Let the job run for some time
    println("Cancelling the job...")
    job.cancelAndJoin() // Cancel the job and wait for its completion
    println("Job is cancelled.")
}
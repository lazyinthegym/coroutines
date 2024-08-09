import kotlinx.coroutines.*

// This can never be cancelled because it doesn't have any suspension points
suspend fun longRunningWrong() {
    while(true) {
        println("I'm still running")
    }
}

// This can be cancelled because it has suspension points
suspend fun longRunningSupportsCancellation() {

    withContext(Dispatchers.IO) { // This is a suspension point
        while(true) {
            ensureActive() // This is a suspension point

            // Other suspension points:
            // yield()
            // delay(1)
            // ensureActive()

            println("I'm still running")
        }
    }
}

fun testCancellationSupport(): Unit = runBlocking {

    var job: Job = launch {
        longRunningSupportsCancellation()
        // longRunningWrong()
    }

    delay(1000)
    job.cancelAndJoin()
}
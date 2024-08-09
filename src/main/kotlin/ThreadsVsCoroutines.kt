import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

/**
 * Threads vs Coroutines
 * Threads and coroutines are both used for concurrency but differ in how they operate:
 *
 * `Threads:`
 *
 * **System-managed**: Threads are managed by the operating system.
 *
 * **Preemptive multitasking**: The OS can interrupt threads at any time to switch between them, ensuring that each thread gets a chance to execute.
 *
 * **Heavyweight**: Threads have their own stack, and context switching between threads is more resource-intensive.
 * **Parallel execution**: Threads can run in parallel on multiple CPU cores.
 *
 * `Coroutines:`
 *
 * **Program-managed**: Coroutines are managed by the programmer or a specific runtime.
 *
 * **Cooperative multitasking**: Coroutines yield control voluntarily, allowing the program to determine when to switch between them.
 *
 * **Lightweight**: Coroutines share the same stack and have lower overhead than threads.
 *
 * **Single-threaded concurrency**: Coroutines typically run on a single thread, offering concurrency without parallelism.
 *
 * In summary, threads are OS-level, can run in parallel, and are more resource-intensive, while coroutines are user-level, cooperative, and more lightweight.
 *
 */

fun exampleCoroutine() = runBlocking {
    repeat(100_000) {
        launch {
            delay(1000L)
            print(".")
        }
    }
}

// No! Don't do it! Very bad idea on threads
fun exampleThreads() {
    repeat(100_000) {
        thread {
            Thread.sleep(1000L)
            print(".")
        }
    }
}

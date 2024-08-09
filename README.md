# Kotlin Coroutines
A personal repository to learn and practice Kotlin Coroutines.

## Main-Safety convention
- All suspending functions should be main-safe.
- Main-safe means that the function can be called from the main thread without blocking it.
- If a suspending function needs to perform a blocking operation (like a network call), it the responsibility of the function to use Dispatchers.IO 
or Dispatchers.Default to perform the blocking operation. The caller should not have to worry about this.

## Threads vs Coroutines
Threads and coroutines are both used for concurrency but differ in how they operate:

`Threads:`
- **System-managed**: Threads are managed by the operating system.
- **Preemptive multitasking**: The OS can interrupt threads at any time to switch between them, ensuring that each thread gets a chance to execute.
- **Heavyweight**: Threads have their own stack, and context switching between threads is more resource-intensive.
- **Parallel execution**: Threads can run in parallel on multiple CPU cores.

`Coroutines:`
- **Program-managed**: Coroutines are managed by the programmer or a specific runtime.
- **Cooperative multitasking**: Coroutines yield control voluntarily, allowing the program to determine when to switch between them.
- **Lightweight**: Coroutines share the same stack and have lower overhead than threads.
- **Single-threaded concurrency**: Coroutines typically run on a single thread, offering concurrency without parallelism.

In summary, threads are OS-level, can run in parallel, and are more resource-intensive, while coroutines are user-level, cooperative, and more lightweight.


## Suspension Points
- A suspension point is a point in a coroutine where it can be paused and resumed later.
- Calls to suspending functions of the Kotlin coroutine standard library are suspension points.
- Examples:
  - `delay()`
  - `withContext()`
  - `suspendCoroutine()`
  - `suspendCancellableCoroutine()`
  - `yield()`


# Resources
- [How Suspension Works in Kotlin Coroutines?](https://www.youtube.com/watch?v=5hR42Fkz2Ms)
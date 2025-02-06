# Extensions pack for coroutines

## Install

```kotlin
implementation("net.arlantech:coroutineext:$version'")
```

## Why is it necessary to use these extensions?

Kotlin coroutines provide powerful tools for asynchronous programming, but managing errors and working with `Deferred` results can sometimes lead to verbose and repetitive code. 

This extensions pack addresses these challenges by providing:
*   **Simplified Error Handling:**  The `suspendRunCatching` function and the `Result` type streamline error handling, reducing the need for repetitive `try-catch` blocks.
*   **Consistent Result Management:** The `Result` type provides a unified way to represent the outcome of suspending operations, whether they succeed or fail.
*   **Convenient `Deferred` Handling:** Extensions like `awaitResult` and `awaitAllResult` simplify working with `Deferred` objects, making it easier to get results and handle potential exceptions.
*   **Reduced Boilerplate:** These extensions abstract away common patterns, leading to cleaner and more concise coroutine code.
*   **Improved Readability:** By reducing boilerplate and providing clear error handling, these extensions make your coroutine code easier to read and understand.

## Samples

**1. Using `suspendRunCatching` for error handling:**
```kotlin
import kotlinx.coroutines.*
suspend fun fetchData(): Result<String> { 
    return suspendRunCatching { 
        delay(500) 
        throw RuntimeException("Failed to fetch data") 
    } 
}
fun main() = runBlocking {
    val result = fetchData() 
    result
        .onSuccess { data -> println("Data: $data") }
        .onFailure { exception -> println("Error: ${exception.message}") } 
}
```

**2. Using `awaitResult` with a `Deferred`:**
```kotlin
import kotlinx.coroutines.* 
fun main() = runBlocking {
    val deferred: Deferred<String> = async {
        delay(500)
        "Data fetched successfully"
    }
    val result = deferred.awaitResult()
    result
        .onSuccess { data -> println("Data: $data") }
        .onFailure { exception -> println("Error: ${exception.message}") }
}
```
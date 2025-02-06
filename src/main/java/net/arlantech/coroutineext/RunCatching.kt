package net.arlantech.coroutineext

import kotlin.coroutines.cancellation.CancellationException

/**
 * Executes a suspending [block] and returns its result as a [Result].
 *
 * Catches exceptions during [block] execution:
 * - Re-throws [CancellationException].
 * - Returns [Result.failure] for other exceptions.
 * - Returns [Result.success] with the block's result on success.
 *
 * Example usage:
 * ```
 * suspend fun fetchData(): String {
 *     delay(1000) // Simulate some work
 *     return "Data fetched successfully"
 * }
 *
 * fun main() = runBlocking {
 *     val result = suspendRunCatching { fetchData() }
 *
 *     result.onSuccess { data ->
 *         println("Success: $data")
 *     }.onFailure { exception ->
 *         println("Failure: ${exception.message}")
 *     }
 * }
 * ```
 *
 * @param R The result type.
 * @param block The suspending function to execute.
 * @return A [Result] containing the result or an exception.
 */
public suspend inline fun <R> suspendRunCatching(crossinline block: suspend () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
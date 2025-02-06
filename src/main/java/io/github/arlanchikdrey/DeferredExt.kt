package io.github.arlanchikdrey

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll

/**
 * Awaits the result of a [Deferred] and returns it as a [Result].
 *
 * Example usage:
 * ```
 * val deferred: Deferred<String> = async { "Hello" }
 * val result: Result<String> = deferred.awaitResult( )
 * result.onSuccess { println(it) } // Prints "Hello"
 * ```
 *
 * @return A [Result] containing the result or an exception.
 */
public suspend fun <T> Deferred<T>.awaitResult(): Result<T> = suspendRunCatching { await() }

/**
 * Awaits all [Deferred] results and returns them as a [Result] containing a [List].
 *
 * Example usage:
 * ```
 * val deferred1 = async { "One" }
 * val deferred2 = async { "Two" }
 * val result = awaitAllResult(deferred1,  deferred2)
 * result.onSuccess { println(it) } // Prints "[One, Two]"
 * result.onFailure { println("An error occurred") }
 * ```
 *
 * @param deferreds The [Deferred] instances to await.
 * @return A [Result] containing a [List] of the awaited values or an exception.
 */
public suspend fun <T> awaitAllResult(vararg deferreds: Deferred<T>): Result<List<T>> =
    suspendRunCatching { awaitAll(*deferreds) }

/**
 * Awaits all [Deferred] results in this collection and returns them as a [Result]
 * containing a [List].
 *
 * Example usage:
 * ```
 * val deferreds = listOf(async { "One" }, async { "Two" })
 * val result = deferreds.awaitAllResult( )
 * result.onSuccess { println(it) } // Prints "[One, Two]"
 * result.onFailure { println("An error occurred") }
 * ```
 *
 * @return A [Result] containing a [List] of the awaited values or an exception.
 */
public suspend fun <T> Collection<Deferred<T>>.awaitAllResult(): Result<List<T>> =
    suspendRunCatching { awaitAll() }
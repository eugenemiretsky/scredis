/*
 * Copyright (c) 2013 Livestream LLC. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. See accompanying LICENSE file.
 */
package scredis.commands.async

import akka.dispatch.Future
import akka.util.{ Duration, FiniteDuration }

import scredis.{ CommandOptions, Condition }
import scredis.parsing._

/**
 * This trait implements asynchronous strings commands.
 * 
 * @define e [[scredis.exceptions.RedisCommandException]]
 * @define none `None`
 */
trait StringsCommands extends Async {

  /**
   * Appends a value to a key.
   *
   * @param key the key to be appended
   * @param value the value to append
   * @return the length of the string after the append operation
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.0.0
   */
  def append(key: String, value: Any)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.append(key, value))

  /**
   * Counts the number of bits set to 1 in a string.
   *
   * @note Non-existent keys are treated as empty strings, so the command will return zero.
   *
   * @param key the key for which the bitcount should be returned
   * @return the number of bits set to 1
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.6.0
   */
  def bitCount(key: String)(implicit opts: CommandOptions = DefaultCommandOptions): Future[Long] =
    async(_.bitCount(key))

  /**
   * Counts the number of bits set to 1 in a string from start offset to end offset.
   *
   * @note Non-existent keys are treated as empty strings, so the command will return zero.
   *
   * @param key the key for which the bitcount should be returned
   * @param start start offset in bytes
   * @param end end offset in bytes
   * @return the number of bits set to 1 in the specified interval
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.6.0
   */
  def bitCountInRange(key: String, start: Long, end: Long)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.bitCountInRange(key, start, end))

  /**
   * Performs bitwise AND operation between two strings.
   *
   * @note When an operation is performed between strings having different lengths, all the strings
   * shorter than the longest string in the set are treated as if they were zero-padded up to the
   * length of the longest string. The same holds true for non-existent keys, that are considered
   * as a stream of zero bytes up to the length of the longest string.
   *
   * @param key1	left operand
   * @param key2	right operand
   * @param destKey	key where the result of the operation will be stored
   * @return the size of the string stored in the destination key, that is equal to the size of
   * the longest input string
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.6.0
   */
  def bitOpAnd(key1: String, key2: String, destKey: String)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.bitOpAnd(key1, key2, destKey))

  /**
   * Performs bitwise OR operation between two strings.
   *
   * @note When an operation is performed between strings having different lengths, all the strings
   * shorter than the longest string in the set are treated as if they were zero-padded up to the
   * length of the longest string. The same holds true for non-existent keys, that are considered
   * as a stream of zero bytes up to the length of the longest string.
   *
   * @param key1	left operand
   * @param key2	right operand
   * @param destKey	key where the result of the operation will be stored
   * @return the size of the string stored in the destination key, that is equal to the size of
   * the longest input string
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.6.0
   */
  def bitOpOr(key1: String, key2: String, destKey: String)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.bitOpOr(key1, key2, destKey))

  /**
   * Performs bitwise XOR operation between two strings.
   *
   * @note When an operation is performed between strings having different lengths, all the strings
   * shorter than the longest string in the set are treated as if they were zero-padded up to the
   * length of the longest string. The same holds true for non-existent keys, that are considered
   * as a stream of zero bytes up to the length of the longest string.
   *
   * @param key1	left operand
   * @param key2	right operand
   * @param destKey	key where the result of the operation will be stored
   * @return the size of the string stored in the destination key, that is equal to the size of
   * the longest input string
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.6.0
   */
  def bitOpXor(key1: String, key2: String, destKey: String)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.bitOpXor(key1, key2, destKey))

  /**
   * Performs bitwise NOT operation on a given string.
   *
   * @param key the source key
   * @param destKey	key where the result of the operation will be stored
   * @return the size of the string stored in the destination key, that is equal to the size of
   * the longest input string
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.6.0
   */
  def bitOpNot(key: String, destKey: String)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.bitOpNot(key, destKey))

  /**
   * Decrements the integer value of a key by one.
   *
   * @note If the key does not exist, it is set to 0 before performing the operation.
   *
   * @param key the key to decrement
   * @return the value of key after the decrement
   * @throws $e if the key contains a value of the wrong type or contains a string that cannot be
   * represented as integer
   *
   * @since 1.0.0
   */
  def decr(key: String)(implicit opts: CommandOptions = DefaultCommandOptions): Future[Long] =
    async(_.decr(key))

  /**
   * Decrements the integer value of a key by the given amount.
   *
   * @note If the key does not exist, it is set to 0 before performing the operation.
   *
   * @param key the key to decrement
   * @param count the decrement
   * @return the value of key after the decrement
   * @throws $e if the key contains a value of the wrong type or contains
   * a string that cannot be represented as integer
   *
   * @since 1.0.0
   */
  def decrBy(key: String, count: Long)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.decrBy(key, count))

  /**
   * Returns the value stored at key.
   *
   * @param key the target key
   * @return value stored at key, or $none if the key does not exist
   * @throws $e if the value stored at key is not of type string
   *
   * @since 1.0.0
   */
  def get[A](key: String)(
    implicit opts: CommandOptions = DefaultCommandOptions,
    parser: Parser[A] = StringParser
  ): Future[Option[A]] = async(_.get(key))

  /**
   * Returns the bit value at offset in the string value stored at key.
   *
   * @param key the target key
   * @param offset the position in the string
   * @return true if the bit is set to 1, false otherwise
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.2.0
   */
  def getBit(key: String, offset: Long)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Boolean] = async(_.getBit(key, offset))

  /**
   * Returns a substring of the string stored at a key.
   *
   * @note Both offsets are inclusive, i.e. [start, end]. The function handles out of range
   * requests by limiting the resulting range to the actual length of the string.
   *
   * @param key the target key
   * @param start the start offset (inclusive)
   * @param end the end offset (inclusive)
   * @return the substring determined by the specified offsets
   * @throws $e if the value stored at key is not of type string
   *
   * @since 1.0.0
   */
  @deprecated("SUBSTR has been renamed to GETRANGE in Redis versions > 2.0.0", "2.0.1")
  def substr[A](key: String, start: Long, end: Long)(
    implicit opts: CommandOptions = DefaultCommandOptions,
    parser: Parser[A] = StringParser
  ): Future[A] = async(_.substr(key, start, end))

  /**
   * Returns a substring of the string stored at a key.
   *
   * @note Both offsets are inclusive, i.e. [start, end]. The function handles out of range
   * requests by limiting the resulting range to the actual length of the string.
   *
   * @param key the target key
   * @param start the start offset (inclusive)
   * @param end the end offset (inclusive)
   * @return the substring determined by the specified offsets
   * @throws $e if the value stored at key is not of type string
   *
   * @since 2.4.0
   */
  def getRange[A](key: String, start: Long, end: Long)(
    implicit opts: CommandOptions = DefaultCommandOptions,
    parser: Parser[A] = StringParser
  ): Future[A] = async(_.getRange(key, start, end))

  /**
   * Sets the string value of a key and return its old value.
   *
   * @param key the target key
   * @param value the value to set key to
   * @return the old value, or $none if the latter did not exist
   * @throws $e if the value stored at key is not of type string
   *
   * @since 1.0.0
   */
  def getSet[A](key: String, value: Any)(
    implicit opts: CommandOptions = DefaultCommandOptions,
    parser: Parser[A] = StringParser
  ): Future[Option[A]] = async(_.getSet(key, value))

  /**
   * Increments the integer value of a key by one.
   *
   * @note If the key does not exist, it is set to 0 before performing the operation.
   *
   * @param key the key to increment
   * @return the value of key after the increment
   * @throws $e if the key contains a value of the wrong type or contains a string that cannot be
   * represented as integer
   *
   * @since 1.0.0
   */
  def incr(key: String)(implicit opts: CommandOptions = DefaultCommandOptions): Future[Long] =
    async(_.incr(key))

  /**
   * Increments the integer value of a key by the given amount.
   *
   * @note If the key does not exist, it is set to 0 before performing the operation.
   *
   * @param key the key to increment
   * @param count the increment
   * @return the value of key after the decrement
   * @throws $e if the key contains a value of the wrong type or contains
   * a string that cannot be represented as integer
   *
   * @since 1.0.0
   */
  def incrBy(key: String, count: Long)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.incrBy(key, count))

  /**
   * Increment the float value of a key by the given amount.
   *
   * @note If the key does not exist, it is set to 0 before performing the operation.
   *
   * @param key the key to increment
   * @param count the increment
   * @return the value of key after the decrement
   * @throws $e if the key contains a value of the wrong type, the current key content or the
   * specified increment are not parseable as a double precision floating point number
   *
   * @since 2.6.0
   */
  def incrByFloat(key: String, count: Double)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Double] = async(_.incrByFloat(key, count))

  /**
   * Returns the values of all specified keys.
   *
   * @note For every key that does not hold a string value or does not exist, $none is returned.
   * Because of this, the operation never fails.
   *
   * @param key the target key
   * @param keys additional keys
   * @return list of value(s) stored at the specified key(s)
   *
   * @since 1.0.0
   */
  def mGet[A](key: String, keys: String*)(
    implicit opts: CommandOptions = DefaultCommandOptions,
    parser: Parser[A] = StringParser
  ): Future[List[Option[A]]] = async(_.mGet(key, keys: _*))

  /**
   * Returns a `Map` containing the specified key(s) paired to their respective value(s).
   *
   * @note Every key that does not hold a string value or does not exist will be removed from the
   * resulting `Map`.
   *
   * @param key the target key
   * @param keys additional keys
   * @return map of key-value pairs
   *
   * @since 1.0.0
   */
  def mGetAsMap[A](key: String, keys: String*)(
    implicit opts: CommandOptions = DefaultCommandOptions,
    parser: Parser[A] = StringParser
  ): Future[Map[String, A]] = async(_.mGetAsMap(key, keys: _*))

  /**
   * Atomically sets multiple keys to multiple values.
   *
   * @note MSET replaces existing values with new values, just as regular SET.
   *
   * @param keyValueMap key-value pairs to be set
   * @throws $e if the provided keyValueMap is empty
   *
   * @since 1.0.1
   */
  def mSetFromMap(keyValueMap: Map[String, Any])(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Unit] = async(_.mSetFromMap(keyValueMap))

  /**
   * Atomically sets multiple keys to multiple values.
   *
   * @note MSET replaces existing values with new values, just as regular SET.
   *
   * @param keyValuePair key-value pair to be set
   * @param keyValuePairs additional key-value pairs to be set
   *
   * @since 1.0.1
   */
  def mSet(keyValuePair: (String, Any), keyValuePairs: (String, Any)*)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Unit] = async(_.mSet(keyValuePair, keyValuePairs: _*))

  /**
   * Atomically sets multiple keys to multiple values, only if none of the keys exist.
   *
   * @note MSETNX will not perform any operation at all even if just a single key already exists.
   *
   * @param keyValueMap key-value pairs to be set
   * @throws $e if the provided keyValueMap is empty
   *
   * @since 1.0.1
   */
  def mSetNXFromMap(keyValueMap: Map[String, Any])(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Boolean] = async(_.mSetNXFromMap(keyValueMap))

  /**
   * Atomically sets multiple keys to multiple values, only if none of the keys exist.
   *
   * @note MSETNX will not perform any operation at all even if just a single key already exists.
   *
   * @param keyValuePair key-value pair to be set
   * @param keyValuePairs additional key-value pairs to be set
   *
   * @since 1.0.1
   */
  def mSetNX(keyValuePair: (String, Any), keyValuePairs: (String, Any)*)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Boolean] = async(_.mSetNX(keyValuePair, keyValuePairs: _*))

  /**
   * Sets the string value of a key.
   *
   * @note If key already holds a value, it is overwritten, regardless of its type. Any previous
   * time to live associated with the key is discarded on successful SET operation.
   *
   * @param key target key to set
   * @param value value to be stored at key
   *
   * @since 1.0.0
   */
  def set(key: String, value: Any)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Unit] = async(_.set(key, value))
  
  /**
   * Sets the string value of a key, optionally using a condition and/or expiring it.
   *
   * @note If key already holds a value, it is overwritten, regardless of its type. Any previous
   * time to live associated with the key is discarded on successful SET operation.
   *
   * @param key target key to set
   * @param value value to be stored at key
   * @param expireAfter when defined, sets an expiration time on the value
   * @param condition when defined, only set the value if the condition is verified
   * @return `true` if the value was set, `false` if the operation was not performed because the
   * provided condition was not met
   *
   * @since 2.6.12
   */
  def setWithOptions(
    key: String,
    value: Any,
    expireAfter: Option[FiniteDuration] = None,
    condition: Option[Condition] = None
  )(implicit opts: CommandOptions = DefaultCommandOptions): Future[Boolean] = async(
    _.setWithOptions(key, value, expireAfter, condition)
  )

  /**
   * Sets or clears the bit at offset in the string value stored at key.
   *
   * @note When key does not exist, a new string value is created. The string is grown to make sure
   * it can hold a bit at offset. When the string at key is grown, added bits are set to 0.
   *
   * @param key key for which the bit should be set
   * @param offset position where the bit should be set
   * @param bit true sets the bit to 1, false sets it to 0
   * @throws $e if the key contains a value of the wrong type
   *
   * @since 2.2.0
   */
  def setBit(key: String, offset: Long, bit: Boolean)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Boolean] = async(_.setBit(key, offset, bit))

  /**
   * Sets the value and expiration of a key.
   *
   * @note If key already holds a value, it is overwritten, regardless of its type.
   *
   * @param key	target key to set
   * @param value value to be stored at key
   * @param ttl time-to-live, up to milliseconds precision
   *
   * @since 2.6.0
   */
  def setEXDuration(key: String, value: Any, ttl: FiniteDuration)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Unit] = async(_.setEXDuration(key, value, ttl))

  /**
   * Sets the value and expiration in seconds of a key.
   *
   * @note If key already holds a value, it is overwritten, regardless of its type.
   *
   * @param key target key to set
   * @param value value to be stored at key
   * @param ttlSeconds time-to-live in seconds
   *
   * @since 2.0.0
   */
  def setEX(key: String, value: Any, ttlSeconds: Int)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Unit] = async(_.setEX(key, value, ttlSeconds))

  /**
   * Sets the value and expiration in milliseconds of a key.
   *
   * @note If key already holds a value, it is overwritten, regardless of its type.
   *
   * @param key target key to set
   * @param value value to be stored at key
   * @param ttlMillis time-to-live in milliseconds
   *
   * @since 2.6.0
   */
  def pSetEX(key: String, value: Any, ttlMillis: Long)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Unit] = async(_.pSetEX(key, value, ttlMillis))

  /**
   * Sets the value of a key, only if the key does not exist.
   *
   * @param key target key to set
   * @param value value to be stored at key
   * @return true if the key was set, false otherwise
   *
   * @since 1.0.0
   */
  def setNX(key: String, value: Any)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Boolean] = async(_.setNX(key, value))

  /**
   * Overwrites part of a string at key starting at the specified offset.
   *
   * @note If the offset is larger than the current length of the string at key, the string is
   * padded with zero-bytes to make offset fit. Non-existing keys are considered as empty strings,
   * so this command will make sure it holds a string large enough to be able to set value at
   * offset.
   *
   * @param key	target key
   * @param offset position from which the string must be overwritten
   * @param value string value to be set at given offset
   * @return the length of the string after it was modified by the command
   * @throws $e if the key contains a value of the wrong type
   *
   * @since 2.2.0
   */
  def setRange(key: String, offset: Long, value: Any)(
    implicit opts: CommandOptions = DefaultCommandOptions
  ): Future[Long] = async(_.setRange(key, offset, value))

  /**
   * Returns the length of the string value stored in a key.
   *
   * @param key target key
   * @return the length of the string stored at key, or 0 when the key does not exist
   * @throws $e if the key contains a value of the wrong type
   *
   * @since 2.2.0
   */
  def strLen(key: String)(implicit opts: CommandOptions = DefaultCommandOptions): Future[Long] =
    async(_.strLen(key))

}
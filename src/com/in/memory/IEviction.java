package com.in.memory;

/**
 * // TO have different Eviction policies
 * different eviction policy classes can
 * implement this interface & define own
 * eviction policy.
 */
public interface IEviction<K, V> {

  void evict();

  void update(K key, V value);
}

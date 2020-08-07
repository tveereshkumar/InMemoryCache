package com.in.memory;

import java.io.IOException;

/**
 * // TODO Comment
 */
public interface ICache<K, V> {

  V get(K key) throws IOException;

  boolean put(K key, V value) throws IOException;
}

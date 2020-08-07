package com.in.memory;

import java.io.IOException;

/**
 * // TODO Comment
 */
public interface CacheClient<K, V> {

  V get(K key) throws IOException;

  boolean put(K key, V value) throws IOException;

  boolean delete(K key) throws IOException;
}

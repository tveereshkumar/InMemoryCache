package com.in.memory;

/**
 * // TODO Comment
 */
public interface ICacheClientBuilder<K, V> {

  CacheClient<K, V> build();

  ICacheClientBuilder<K, V> addInMemoryCacheLayer(ICache<K, V> cache);
}

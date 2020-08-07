package com.in.memory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache<K, V> implements ICache<K, V> {
  private Node<K, V> head;
  private Node<K, V> tail;
  //thread safe access of the cache.
  private final ConcurrentHashMap<K, Node> map;
  private final int capacity;
  private final IEviction eviction;

  public InMemoryCache(int capacity) {
    head = new Node(); tail = new Node();
    head.next = tail;
    tail.prev = head;
    this.map = new ConcurrentHashMap<>();
    this.capacity = capacity;
    this.eviction = new LRUEviction(head, tail, map);
  }

  @Override
  public V get(K key) throws IOException {
    if(map.containsKey(key)) {
      Node node = map.get(key);
      remove(key);
      return (V) node.value;
    }
    else
      throw new IllegalStateException("Key not found");
  }

  @Override
  public boolean put(K key, V value) throws IOException {
    if(map.containsKey(key)) {
      eviction.update(key, value);
    } else {
      //evict some keys if the size == capacity
      if(map.size() == capacity)
        eviction.evict();
      insert(key, value);
    }
    return true;
  }

  private void remove(K key) {
    if (map.containsKey(key)){
      Node<K, V> node = (Node<K, V>) map.get(key);
      node.prev.next = node.next;
      node.next.prev = node.prev;
      map.remove(node.key);
    }
  }

  private void insert(K key, V value){
    Node<K, V> node = new Node<>(key, value);
    Node headNext = head.next;
    head.next = node;
    node.prev = head;
    headNext.prev = node;
    node.next = headNext;
    map.put(key, node);
  }
}
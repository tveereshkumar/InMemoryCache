package com.in.memory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * // TODO Comment
 */
public class LRUEviction<K, V> implements IEviction<K, V> {
  private final Node<K, V> head, tail;
  private final Map<K, Node> map;

  public LRUEviction(Node<K, V> head, Node<K, V> tail, ConcurrentHashMap<K, Node> map) {
    this.head = head;
    this.tail = tail;
    this.map = map;
  }

  @Override
  public void evict() {
    remove((K) tail.prev.key);
  }

  @Override
  public void update(K key, V value) {
    remove(key);
    updateKey(key, value);
  }

  private void remove(K key) {
    if (map.containsKey(key)){
      Node<K, V> node = (Node<K, V>) map.get(key);
      node.prev.next = node.next;
      node.next.prev = node.prev;
      map.remove(node.key);
    }
  }

  private void updateKey(K key, V value){
    Node<K, V> node = new Node<>(key, value);
    Node headNext = head.next;
    head.next = node;
    node.prev = head;
    headNext.prev = node;
    node.next = headNext;
    map.put(key, node);
  }
}

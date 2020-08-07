package com.in.memory;

public class Node<K, V> {
  public K key;
  public V value;
  public Node prev;
  public Node next;

  public Node() {

  }

  public Node(K key, V value) {
    this.key = key;
    this.value = value;
  }
}
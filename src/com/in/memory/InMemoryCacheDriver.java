package com.in.memory;

import java.io.IOException;

/**
 * // TODO Comment
 */
public class InMemoryCacheDriver {

  public static void main(String[] args) throws IOException {
    ICache<Integer, String> cache = new InMemoryCache<>(5);
    System.out.println(cache.put(1, "One"));
    System.out.println(cache.put(2, "Two"));
    System.out.println(cache.put(3, "Three"));
    System.out.println(cache.put(4, "Four"));
    System.out.println(cache.put(5, "Five"));

    //Updating the value with the same key
    System.out.println(cache.put(1, "Six"));

    System.out.println(cache.get(1));

  }
}

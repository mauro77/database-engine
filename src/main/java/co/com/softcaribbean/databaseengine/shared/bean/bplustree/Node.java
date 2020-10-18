package co.com.softcaribbean.databaseengine.shared.bean.bplustree;

import java.util.List;

public abstract class Node<K extends Comparable<? super K>, V> {
  List<K> keys;

  int keyNumber() {
    return keys.size();
  }

  abstract Object getValue(K key);

  abstract Node deleteValue(K key, Node root, int dimension);

  abstract Node insertValue(K key, V value, Node root, int dimension);

  abstract K getFirstLeafKey();

  abstract void merge(Node<?, ?> sibling);

  abstract Node<?, ?> split();

  abstract boolean isOverflow(int dimension);

  abstract boolean isUnderflow(int dimension);

  public String toString() {
    return keys.toString();
  }
}

package co.com.softcaribbean.databaseengine.shared.bean.bplustree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * <p>
 * BtrePlus leaf node configuration and methods
 * </p>
 *
 * @author Mauricio Hincapié Monsalve.
 *
 */
public class LeafNode<K extends Comparable<? super K>, V> extends Node<K, V> {
  List<V> values;
  LeafNode next;

  LeafNode() {
    keys = new ArrayList<K>();
    values = new ArrayList<V>();
  }

  @Override
   V getValue(K key) {
    int loc = Collections.binarySearch(keys, key);
    return loc >= 0 ? values.get(loc) : null;
  }

  @Override
  Node deleteValue(K key, Node root, int dimension) {
    int loc = Collections.binarySearch(keys, key);
    if (loc >= 0) {
      keys.remove(loc);
      values.remove(loc);
    }
    return root;
  }

  @Override
  Node insertValue(K key, V value, Node root, int dimension) {
    int loc = Collections.binarySearch(keys, key);
    int valueIndex = loc >= 0 ? loc : -loc - 1;
    if (loc >= 0) {
      values.set(valueIndex, value);
    } else {
      keys.add(valueIndex, key);
      values.add(valueIndex, value);
    }
    if (root.isOverflow(dimension)) {
      Node sibling = split();
      InternalNode newRoot = new InternalNode();
      newRoot.keys.add(sibling.getFirstLeafKey());
      newRoot.children.add(this);
      newRoot.children.add(sibling);
      root = newRoot;
    }
    return root;
  }

  @Override
  K getFirstLeafKey() {
    return keys.get(0);
  }

  @Override
  void merge(Node sibling) {
    LeafNode node = (LeafNode) sibling;
    keys.addAll(node.keys);
    values.addAll(node.values);
    next = node.next;
  }

  @Override
  Node split() {
    LeafNode sibling = new LeafNode();
    int from = (keyNumber() + 1) / 2, to = keyNumber();
    sibling.keys.addAll(keys.subList(from, to));
    sibling.values.addAll(values.subList(from, to));

    keys.subList(from, to).clear();
    values.subList(from, to).clear();

    sibling.next = next;
    next = sibling;
    return sibling;
  }

  @Override
  boolean isOverflow(int dimension) {
    return values.size() > dimension - 1;
  }

  @Override
  boolean isUnderflow(int dimension) {
    return values.size() < dimension / 2;
  }
}

package co.com.softcaribbean.databaseengine.shared.bean.bplustree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
*
* <p>
*   BtrePlus Internal node configuration and methods
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
public class InternalNode <K extends Comparable<? super K>, V> extends Node<K, V> {
  List<Node> children;

  InternalNode() {
    this.keys = new ArrayList<K>();
    this.children = new ArrayList<Node>();
  }

  @Override
  Object getValue(K key) {
    return getChild(key).getValue(key);
  }

  @Override
  Node deleteValue(K key, Node root, int dimension) {
    var child = getChild(key);
    child.deleteValue(key, root, dimension);
    if (child.isUnderflow(dimension)) {
      var childLeftSibling = getChildLeftSibling(key);
      var childRightSibling = getChildRightSibling(key);
      var left = childLeftSibling != null ? childLeftSibling : child;
      var right = childLeftSibling != null ? child
          : childRightSibling;
      left.merge(right);
      deleteChild((K) right.getFirstLeafKey());
      if (left.isOverflow(dimension)) {
        var sibling = left.split();
        insertChild((K) sibling.getFirstLeafKey(), sibling);
      }
      if (root.keyNumber() == 0)
        root = left;
    }
    return root;
  }

  @Override
  Node insertValue(K key, V value, Node root, int dimension) {
    var child = getChild(key);
    child.insertValue(key, value, root, dimension);
    if (child.isOverflow(dimension)) {
      var sibling = child.split();
      insertChild((K) sibling.getFirstLeafKey(), sibling);
    }
    if (root.isOverflow(dimension)) {
      var sibling = split();
      var newRoot = new InternalNode();
      newRoot.keys.add(sibling.getFirstLeafKey());
      newRoot.children.add(this);
      newRoot.children.add(sibling);
      root = newRoot;
    }
    return root;
  }

  @Override
  K getFirstLeafKey() {
    return (K) children.get(0).getFirstLeafKey();
  }

  @Override
  void merge(Node sibling) {
    var node = (InternalNode) sibling;
    keys.add((K) node.getFirstLeafKey());
    keys.addAll(node.keys);
    children.addAll(node.children);

  }

  @Override
  Node split() {
    int from = keyNumber() / 2 + 1, to = keyNumber();
    var sibling = new InternalNode();
    sibling.keys.addAll(keys.subList(from, to));
    sibling.children.addAll(children.subList(from, to + 1));

    keys.subList(from - 1, to).clear();
    children.subList(from, to + 1).clear();

    return sibling;
  }

  @Override
  boolean isOverflow(int dimension) {
    return children.size() > dimension;
  }

  @Override
  boolean isUnderflow(int dimension) {
    return children.size() < (dimension + 1) / 2;
  }

  private Node getChild(K key) {
    int loc = Collections.binarySearch(keys, key);
    int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
    return children.get(childIndex);
  }

  private void deleteChild(K key) {
    var loc = Collections.binarySearch(keys, key);
    if (loc >= 0) {
      keys.remove(loc);
      children.remove(loc + 1);
    }
  }

  private void insertChild(K key, Node child) {
    var loc = Collections.binarySearch(keys, key);
    var childIndex = loc >= 0 ? loc + 1 : -loc - 1;
    if (loc >= 0) {
      children.set(childIndex, child);
    } else {
      keys.add(childIndex, key);
      children.add(childIndex + 1, child);
    }
  }

  private Node getChildLeftSibling(K key) {
    var loc = Collections.binarySearch(keys, key);
    var childIndex = loc >= 0 ? loc + 1 : -loc - 1;
    if (childIndex > 0)
      return children.get(childIndex - 1);

    return null;
  }

  private Node getChildRightSibling(K key) {
    var loc = Collections.binarySearch(keys, key);
    var childIndex = loc >= 0 ? loc + 1 : -loc - 1;
    if (childIndex < keyNumber())
      return children.get(childIndex + 1);

    return null;
  }
}


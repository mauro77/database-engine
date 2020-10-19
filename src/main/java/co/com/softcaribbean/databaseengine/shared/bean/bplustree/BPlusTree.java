package co.com.softcaribbean.databaseengine.shared.bean.bplustree;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
*
* <p>
* BPlusTree implementation
* </p>
*
*
*/
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BPlusTree<K extends Comparable<? super K>, V> {

  static final int DEFAULT_DIMENSION = 4;

  Node root;
  
  int dimension;

  public BPlusTree() {
    this(DEFAULT_DIMENSION);
  }

  public BPlusTree(int dimension) {
    if (dimension <= 2)
      throw new IllegalArgumentException("Illegal tree dimension: "
          + dimension);
    this.dimension = dimension;
    root = new LeafNode();
  }

  /**
   * Search node associated value by its key
   */
  public Object search(K key) {
    return root.getValue(key);
  }

  /**
   * Insert Key, value pair in BPlus tree
   */
  public void insert(K key, V value) {
    this.root = root.insertValue(key, value, root, dimension);
  }

  /**
   * Removes associated node to the given key if exists
   * 
   */
  public void delete(K key) {
    this.root = root.deleteValue(key, root, dimension);
  }

  /**
   * Return BPlus tree structure as string
   */
  public String toString() {
    var queue = new LinkedList<List<Node>>();
    queue.add(Arrays.asList(root));
    var sb = new StringBuilder();
    while (!queue.isEmpty()) {
      var nextQueue = new LinkedList<List<Node>>();
      while (!queue.isEmpty()) {
        var nodes = queue.remove();
        sb.append('{');
        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
          var node = it.next();
          sb.append(node.toString());
          if (it.hasNext())
            sb.append(", ");
          if (node instanceof InternalNode)
            nextQueue.add(((InternalNode) node).children);
        }
        sb.append('}');
        if (!queue.isEmpty())
          sb.append(", ");
        else
          sb.append('\n');
      }
      queue = nextQueue;
    }

    return sb.toString();
  }


  

  
}

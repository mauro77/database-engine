package co.com.softcaribbean.databaseengine.shared.util;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTree;
import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeValueObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
/**
*
* <p>
*   BPlustree process utilities class
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
@AllArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BPlusTreeUtil {
  
  static final Logger log = LoggerFactory.getLogger(FileUtil.class);
  
  /**
   * Insert tree values list into tree structure
   * @param values,
   *          treeDimension
   * @return BPlusTree<String, BPlusTreeValueObject> 
   * 
   * @author Mauricio Hincapié Monsalve.
   */
  public static BPlusTree<String, BPlusTreeValueObject> laodTreeFromFileValues(List<BPlusTreeValueObject> values, int treeDimension) throws IOException {
    BPlusTree<String, BPlusTreeValueObject> bPlusTree = new BPlusTree<String, BPlusTreeValueObject>(treeDimension);
    values.stream().forEach(object -> {
      bPlusTree.insert(object.getKey(), object);
      log.info(bPlusTree.toString());
    });
    return bPlusTree;
  }
  
 

}

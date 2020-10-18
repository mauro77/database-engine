package co.com.softcaribbean.databaseengine.shared.bean.bplustree;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <p>
 * BtrePlus stance manager singleton class
 * </p>
 *
 * @author Mauricio Hincapié Monsalve.
 *
 */
@Getter
@Setter
public class BPlusTreeSingleton {

  private BPlusTree<String,BPlusTreeValueObject> bPlusTreeService;
  private static final BPlusTreeSingleton instance = new BPlusTreeSingleton();
  
  
  /**
  *
  * @param bPlusTreeService
  * @return
  * 
  * Private constructor for singleton class implementation
  *
  * @author Mauricio Hincapié Monsalve.
  */
  private BPlusTreeSingleton(){
  
  }
  
  
  /**
  *
  * @param bPlusTreeService
  * @return
  * 
  * Private constructor for singleton class implementation
  *
  * @author Mauricio Hincapié Monsalve.
  */
  public static BPlusTreeSingleton getInstance() {
    return instance;
  }
  
}

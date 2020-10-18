package co.com.softcaribbean.databaseengine.service.bplustree.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.softcaribbean.databaseengine.config.btreeplus.properties.BPlusTreeProperties;
import co.com.softcaribbean.databaseengine.config.file.properties.FileProperties;
import co.com.softcaribbean.databaseengine.service.bplustree.BPlusTreeService;
import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeSingleton;
import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeValueObject;
import co.com.softcaribbean.databaseengine.shared.util.BPlusTreeUtil;
import co.com.softcaribbean.databaseengine.shared.util.FileUtil;

/**
*
* <p>
*   Implementation of @BPlusTreeService interface for user management methods
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
@Service
public class BPlusTreeServiceImpl implements BPlusTreeService {
  
  private static final Logger log = LoggerFactory.getLogger(BPlusTreeServiceImpl.class);
  
  @Autowired
  FileProperties fileProperties;

  @Autowired
  BPlusTreeProperties bPlusProperties;

  @Override
  public void loadBPlusTreeFromFile() {
    List<BPlusTreeValueObject> list;
    try {
      list = FileUtil.readFileLines(fileProperties.getFileName(), fileProperties.getKeySeparator());
      if (list.isEmpty()) {
        log.info("empty list");
      }
      var btree = BPlusTreeUtil.laodTreeFromFileValues(list, bPlusProperties.getDimension());
      BPlusTreeSingleton.getInstance().setBPlusTreeService(btree);

    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }

}

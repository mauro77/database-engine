package co.com.softcaribbean.databaseengine;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.softcaribbean.databaseengine.config.btreeplus.properties.BPlusTreeProperties;
import co.com.softcaribbean.databaseengine.config.file.properties.FileProperties;
import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeSingleton;
import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeValueObject;
import co.com.softcaribbean.databaseengine.shared.util.BPlusTreeUtil;
import co.com.softcaribbean.databaseengine.shared.util.FileUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
class BPlusTreeTest {
  
  private static final Logger log = LoggerFactory.getLogger(BPlusTreeTest.class);

  @Autowired
  FileProperties fileProperties;

  @Autowired
  BPlusTreeProperties bPlusProperties;

  String objectToSave = "CAR1266;;\"{\\\"license\\\":\\\"CAR1266\\\",\\\"modelYear\\\":2000,\\\"color\\\":\\\"red\\\",\\\"owner\\\":\\\"mauricio1234243234266666666666666666666666344\\\"}\"";

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {
    List<BPlusTreeValueObject> list;
    try {
      list = FileUtil.readFileLines(fileProperties.getFileName(), fileProperties.getKeySeparator());
      if (list.isEmpty()) {
        log.info("empty list");
      }
      var btree = BPlusTreeUtil.laodTreeFromFileValues(list, bPlusProperties.getDimension());
      Assert.assertFalse((btree == null));
      BPlusTreeSingleton.getInstance().setBPlusTreeService(btree);
      var objectToInsert = FileUtil.generateTreeObject(fileProperties.getFileName(), fileProperties.getKeySeparator(), objectToSave);
      Assert.assertFalse((btree == null));
      BPlusTreeSingleton.getInstance().getBPlusTreeService().insert(objectToInsert.getKey(), objectToInsert);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}

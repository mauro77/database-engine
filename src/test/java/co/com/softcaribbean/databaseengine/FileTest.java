package co.com.softcaribbean.databaseengine;

import java.io.IOException;

import org.junit.After;
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
import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeValueObject;
import co.com.softcaribbean.databaseengine.shared.util.BPlusTreeUtil;
import co.com.softcaribbean.databaseengine.shared.util.FileUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
class FileTest {
  
  private static final Logger log = LoggerFactory.getLogger(FileTest.class);

  @Autowired
  FileProperties fileProperties;
  
  @Autowired
  BPlusTreeProperties bPlusProperties;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {
    try {
      var list = FileUtil.readFileLines(fileProperties.getFileName(), fileProperties.getKeySeparator());
     
      var btree = BPlusTreeUtil.laodTreeFromFileValues(list, bPlusProperties.getDimension());
      var object = btree.search("CAR129");
      var line = FileUtil.readLineFromFile(((BPlusTreeValueObject)  object).getInitialPosition(), ((BPlusTreeValueObject) object).getObjetLength(), fileProperties.getFileName());
      log.info(line);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

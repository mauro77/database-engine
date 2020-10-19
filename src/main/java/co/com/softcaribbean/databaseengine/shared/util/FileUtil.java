package co.com.softcaribbean.databaseengine.shared.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.io.CountingInputStream;

import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeValueObject;
import lombok.AllArgsConstructor;

/**
 *
 * <p>
 * Class for file management utilities
 * </p>
 *
 * @author Mauricio Hincapié Monsalve.
 *
 */
@AllArgsConstructor
@Component
public class FileUtil {


  /**
   * Read all file lines for initial Bplus tree load
   * @param fileName,
   *          separator
   * @return List<BPlusTreeValueObject>
   * 
   * @author Mauricio Hincapié Monsalve.
   */
  @SuppressWarnings("resource")
  public static List<BPlusTreeValueObject> readFileLines(String fileName, String separator) throws IOException {

    var responseList = new ArrayList<BPlusTreeValueObject>();
    var file = new File(fileName);
    String line;
    var startByte = 0;

    var cis = new CountingInputStream(new FileInputStream(file));
    if (cis.read() == -1) {
      return responseList;
    }
    var br = new BufferedReader(new InputStreamReader(cis, "UTF-8"), 8192);
    while ((line = br.readLine()) != null) {
      line = line.trim();
      if (line.length() != 0) {
        var keyValue = getValueKeyFromLine(line, separator);
        var lineSize = line.length();
        var object = BPlusTreeValueObject.builder().initialPosition(startByte)
            .objetLength((int) lineSize).value(keyValue[1]).key(keyValue[0]).build();
        startByte += lineSize + 1;
        responseList.add(object);
      }

    }
    br.close();
    return responseList;

  }

  /**
   * Reads a single file line
   * 
   * @param startByte,
   *          length, line
   * @return String
   * 
   * @author Mauricio Hincapié Monsalve.
   */
  public static String readLineFromFile(int startByte, int length, String fileName) throws IOException {
    RandomAccessFile raf = new RandomAccessFile(fileName, "r");
    raf.seek(startByte);
    byte[] bytes = new byte[length];
    raf.read(bytes);
    raf.close();
    return new String(bytes);
  }
  
  /**
   * Deletes a single file line
   *  
   * @param initialByte,
   *          length
   * @return BPlusTreeValueObject
   * 
   * @author Mauricio Hincapié Monsalve.
   * @throws IOException 
   */
  public static void deleteFileLine(String fileName, int initialByte, int length) throws IOException {
    RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
    raf.seek(initialByte);
    byte[] bytes = new byte[length -1];
    raf.writeBytes(new String(bytes));
    raf.close();

  }

  /**
   * Generates BPlus tree object from file line
   * 
   * @param fileName,
   *          separator, line
   * @return BPlusTreeValueObject
   * 
   * @author Mauricio Hincapié Monsalve.
   */
  public static BPlusTreeValueObject generateTreeObject(String fileName, String separator, String line)
      throws IOException {
    var data = line + System.getProperty("line.separator");
    RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
    var initialByte = raf.length();
    raf.seek(initialByte);
    raf.write(data.getBytes());
    raf.close();

    var keyValue = getValueKeyFromLine(line, separator);

    return BPlusTreeValueObject.builder().key(keyValue[0]).value(keyValue[1]).initialPosition((int) initialByte)
        .objetLength(data.length()).build();
  }

  /**
   * Splits file line to get key and value
   * 
   * @param line,
   *          separator
   * @return String[]
   * 
   * @author Mauricio Hincapié Monsalve.
   */
  private static String[] getValueKeyFromLine(String line, String separator) {
    return line.split(separator);
  }

}

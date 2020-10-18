package co.com.softcaribbean.databaseengine.shared.bean.bplustree;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
*
* <p>
*   BtrePlus value object.
*   Contains the initial pointer value for each object in db and  the object length
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
@Component
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "file")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BPlusTreeValueObject {
  
  int initialPosition;
  int objetLength;
  String key;
  String value;


}

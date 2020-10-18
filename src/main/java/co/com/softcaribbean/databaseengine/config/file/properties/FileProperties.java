package co.com.softcaribbean.databaseengine.config.file.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
*
* <p>
*   BtrePlus properties mapping from application.yml file
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
@AllArgsConstructor
@NoArgsConstructor
public class FileProperties {

  String fileName;
  String keySeparator;
}

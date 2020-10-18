package co.com.softcaribbean.databaseengine.config.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
*
* <p>
*   BtrePlus properties configuration bean.
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
@ConfigurationProperties
@EnableConfigurationProperties({FilePropertiesConfig.class})
public class FilePropertiesConfig {

}

package co.com.softcaribbean.databaseengine.config.btreeplus;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import co.com.softcaribbean.databaseengine.config.btreeplus.properties.BPlusTreeProperties;


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
@EnableConfigurationProperties({BPlusTreeProperties.class})
public class BPlusTreePropertiesConfig {

}

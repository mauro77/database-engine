/**
 * 
 */
package co.com.softcaribbean.databaseengine.shared.bean.response;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
*
* <p>
*  User data response object
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CarResponse implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5326360404336918153L;

  String license;
  Integer model;
  String color;
  String owner;
}

package co.com.softcaribbean.databaseengine.shared.bean.request;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
*
* <p>
* User creation and update request object
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CarRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1365333994128190421L;

  String license;
  Integer model;
  String color;
  String owner;
}

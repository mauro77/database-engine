package co.com.softcaribbean.databaseengine.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
*
* <p>
*   Persistence model for Car entity
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {
  String license;
  Integer model;
  String color;
  String owner;
  
}

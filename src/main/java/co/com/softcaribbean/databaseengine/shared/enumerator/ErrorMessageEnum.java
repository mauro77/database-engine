package co.com.softcaribbean.databaseengine.shared.enumerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
*
* <p>
*   Enumeration for error messages definitio.
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorMessageEnum {
  
  
  CAR_NOT_FOUND("Car not found in database"),
  DATBASE_PROCESS_ERROR("Error while reading database info"),
  CAR_PROCESS_ERROR_MESSAGE("Error in car processing info"),
  
  INVALID_FIELDS_REQUEST("Error validando campos de la transaccion."),
  INVALID_FIELDS_MESSAGE_REQUEST("El campo: {0} {1}.");
  
  
  String message;
}

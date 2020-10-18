/**
 * 
 */
package co.com.softcaribbean.databaseengine.repository;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import co.com.softcaribbean.databaseengine.domain.Car;

/**
*
* <p>
*   Interface for user DAO definition
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
public interface CarRepository {
  
  void save(Car createCarRequest) throws JsonProcessingException, IOException;
  
  Car findByLicense(String carId) throws JsonMappingException, JsonProcessingException;
  
  void delete(String carId) throws IOException;
  
  Car update(String carId, Car carRequest) throws IOException;

}

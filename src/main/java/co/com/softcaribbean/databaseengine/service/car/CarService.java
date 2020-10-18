package co.com.softcaribbean.databaseengine.service.car;

import co.com.softcaribbean.databaseengine.shared.bean.request.CarRequest;
import co.com.softcaribbean.databaseengine.shared.bean.response.CarResponse;

/**
*
* <p>
*   User management service interface
* </p>
*
* @author Mauricio Hincapié Monsalve.
*
*/
public interface CarService {
  
  void createCar(CarRequest createCarRequest);
  
  CarResponse findCar(String carId);
  
  void deleteCarById(String carId);
  
  CarResponse updateCar(String carId, CarRequest carRequest);

}

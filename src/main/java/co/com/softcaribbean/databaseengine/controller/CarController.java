package co.com.softcaribbean.databaseengine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.softcaribbean.databaseengine.service.car.CarService;
import co.com.softcaribbean.databaseengine.shared.bean.request.CarRequest;
import co.com.softcaribbean.databaseengine.shared.bean.response.CarResponse;
import lombok.AllArgsConstructor;

/**
 *
 * <p>
 * car management resources controller
 * </p>
 *
 * @author Mauricio Hincapié Monsalve.
 *
 */
@RestController
@RequestMapping("private/car")
@AllArgsConstructor
public class CarController {

  CarService carService;

  /**
   *
   * @param createcarRequest
   * @return
   *
   * @author Mauricio Hincapié Monsalve.
   */
  @PostMapping()
  private ResponseEntity<Void> createCar(@RequestBody CarRequest createcarRequest) {
    carService.createCar(createcarRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);

  }

  /**
   * 
   * @param carUpdateRequest
   * @return
   * 
   * @author Mauricio Hincapié Monsalve.
   */
  @GetMapping(path = "/{carId}")
  public ResponseEntity<CarResponse> findCarByLisence(@PathVariable(name = "carId") String carId) {
    return new ResponseEntity<>(carService.findCar(carId), HttpStatus.OK);
  }

  /**
   * 
   * @param carUpdateRequest
   * @return
   * 
   * @author Mauricio Hincapié Monsalve.
   */
  @PutMapping(path = "/{carId}")
  public ResponseEntity<CarResponse> updateCar(@PathVariable(name = "carId") String carId,
      @RequestBody CarRequest carUpdateRequest) {
    return new ResponseEntity<>(carService.updateCar(carId, carUpdateRequest), HttpStatus.OK);
  }

  /**
   * 
   * @return
   * 
   * @author Mauricio Hincapié Monsalve.
   */
  @DeleteMapping(path = "/{carId}")
  public ResponseEntity<Void> deleteCar(@PathVariable(name = "carId") String carId) {
    carService.deleteCarById(carId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

package co.com.softcaribbean.databaseengine.service.car.impl;

import java.beans.FeatureDescriptor;
import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.com.softcaribbean.databaseengine.domain.Car;
import co.com.softcaribbean.databaseengine.repository.CarRepository;
import co.com.softcaribbean.databaseengine.service.car.CarService;
import co.com.softcaribbean.databaseengine.shared.bean.request.CarRequest;
import co.com.softcaribbean.databaseengine.shared.bean.response.CarResponse;
import co.com.softcaribbean.databaseengine.shared.enumerator.ErrorMessageEnum;
import co.com.softcaribbean.databaseengine.shared.exception.CarNotFoundRuntimeException;
import co.com.softcaribbean.databaseengine.shared.exception.DatabaseObjectProcessRuntimeException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 *
 * <p>
 * Implementation of @UserService interface for user management methods
 * </p>
 *
 * @author Mauricio Hincapié Monsalve.
 *
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarServiceImpl implements CarService {

  @Autowired
  CarRepository carRepository;

  /**
   * 
   */
  @Override
  public void createCar(CarRequest createCarRequest) {
    var newCar = new Car();
    BeanUtils.copyProperties(createCarRequest, newCar);
    try {
      carRepository.save(newCar);
    } catch (IOException e) {
      throw new DatabaseObjectProcessRuntimeException(ErrorMessageEnum.DATBASE_PROCESS_ERROR.getMessage());
    }
  }

  /**
   * 
   */
  @Override
  public CarResponse findCar(String carId) {
    Car car;
    try {
      car = carRepository.findByLicense(carId);
      if (car == null) {
        throw new CarNotFoundRuntimeException(ErrorMessageEnum.CAR_NOT_FOUND.getMessage());
      }
      var response = new CarResponse();
      BeanUtils.copyProperties(car, response);
      return response;
    } catch (JsonProcessingException e) {
      throw new DatabaseObjectProcessRuntimeException(ErrorMessageEnum.DATBASE_PROCESS_ERROR.getMessage());
    }

  }

  /**
   * 
   */
  @Override
  public void deleteCarById(String carId) {
    try {
      carRepository.delete(carId);
    } catch (IOException e) {
      throw new DatabaseObjectProcessRuntimeException(ErrorMessageEnum.DATBASE_PROCESS_ERROR.getMessage());
    }

  }

  /**
   * 
   */
  @Override
  public CarResponse updateCar(String carId, CarRequest carRequest) {
    Car car;
    try {
      car = carRepository.findByLicense(carId);
      if (car == null) {
        throw new CarNotFoundRuntimeException(ErrorMessageEnum.CAR_NOT_FOUND.getMessage());
      }
      BeanUtils.copyProperties(carRequest, car, getNullPropertyNames(carRequest));
      var updatedCar = carRepository.update(carId, car);
      var response = new CarResponse();
      BeanUtils.copyProperties(updatedCar, response, getNullPropertyNames(car));
      return response;
    } catch (IOException e) {
      throw new DatabaseObjectProcessRuntimeException(ErrorMessageEnum.DATBASE_PROCESS_ERROR.getMessage());
    }

  }

  /**
   * 
   * @param source
   * @return
   */
  private String[] getNullPropertyNames(Object source) {
    var wrappedSource = new BeanWrapperImpl(source);
    return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
        .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
  }

}

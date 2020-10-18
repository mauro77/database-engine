package co.com.softcaribbean.databaseengine.repository.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.softcaribbean.databaseengine.config.file.properties.FileProperties;
import co.com.softcaribbean.databaseengine.domain.Car;
import co.com.softcaribbean.databaseengine.repository.CarRepository;
import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeSingleton;
import co.com.softcaribbean.databaseengine.shared.bean.bplustree.BPlusTreeValueObject;
import co.com.softcaribbean.databaseengine.shared.enumerator.ErrorMessageEnum;
import co.com.softcaribbean.databaseengine.shared.exception.CarNotFoundRuntimeException;
import co.com.softcaribbean.databaseengine.shared.util.FileUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 *
 * <p>
 * Implementation of @UserRepository for user management CRUD methods
 * </p>
 *
 * @author Mauricio Hincapié Monsalve.
 *
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarRepositoryImpl implements CarRepository {

  ObjectMapper objectMapper = new ObjectMapper();
  
  static final Logger log = LoggerFactory.getLogger(CarRepositoryImpl.class);

  @Autowired
  FileProperties fileProperties;

  @Override
  public void save(Car createCarRequest) throws IOException {
    var license = createCarRequest.getLicense();
    var carString = objectMapper.writeValueAsString(createCarRequest);
    var line = license + fileProperties.getKeySeparator() + carString;
    var objectToInsert = FileUtil.generateTreeObject(fileProperties.getFileName(), fileProperties.getKeySeparator(),
        line);
    BPlusTreeSingleton.getInstance().getBPlusTreeService().insert(objectToInsert.getKey(), objectToInsert);
    log.info(BPlusTreeSingleton.getInstance().getBPlusTreeService().toString());
  }

  @Override
  public Car findByLicense(String carId) throws JsonMappingException, JsonProcessingException {
    var treeValue = BPlusTreeSingleton.getInstance().getBPlusTreeService().search(carId);
    if (treeValue == null) {
      return null;
    }
    var lineValue = ((BPlusTreeValueObject) treeValue).getValue();
    return objectMapper.readValue(lineValue, Car.class);

  }

  @Override
  public void delete(String carId) throws IOException {
    var treeValue = BPlusTreeSingleton.getInstance().getBPlusTreeService().search(carId);
    if (treeValue == null) {
      throw new CarNotFoundRuntimeException(ErrorMessageEnum.CAR_NOT_FOUND.getMessage());
    }
    
    BPlusTreeSingleton.getInstance().getBPlusTreeService().delete(carId);
    FileUtil.deleteFileLine(fileProperties.getFileName(), ((BPlusTreeValueObject) treeValue).getInitialPosition(), ((BPlusTreeValueObject) treeValue).getObjetLength());
    log.info(BPlusTreeSingleton.getInstance().getBPlusTreeService().toString());
  }

  @Override
  public Car update(String carId, Car carRequest) throws IOException {
    delete(carId);
    var license = carRequest.getLicense();
    var carString = objectMapper.writeValueAsString(carRequest);
    var line = license + fileProperties.getKeySeparator() + carString;
    var objectToInsert = FileUtil.generateTreeObject(fileProperties.getFileName(), fileProperties.getKeySeparator(),
        line);
    BPlusTreeSingleton.getInstance().getBPlusTreeService().insert(objectToInsert.getKey(), objectToInsert);
    log.info(BPlusTreeSingleton.getInstance().getBPlusTreeService().toString());
    return carRequest;
  }

}

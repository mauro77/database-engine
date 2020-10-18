package co.com.softcaribbean.databaseengine.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.softcaribbean.databaseengine.shared.bean.RestResponse;
import co.com.softcaribbean.databaseengine.shared.enumerator.ErrorMessageEnum;
import co.com.softcaribbean.databaseengine.shared.exception.BadRequestRuntimeException;
import co.com.softcaribbean.databaseengine.shared.exception.CarNotFoundRuntimeException;
import co.com.softcaribbean.databaseengine.shared.exception.DatabaseObjectProcessRuntimeException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>
 * Class for custom response in exception management
 * </p>
 * 
 * @authorMauricio Hincapie Monsalve.
 *
 */
@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice {

  /**
   * 
   * @param userNotFound
   * @return
   * @author Mauricio Hincapie Monsalve.
   *
   */
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public RestResponse handleException(Throwable exception) {
    return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception, exception.getMessage(), null, true);
  }

  /**
   * 
   * @param forbbidenKontratacionRuntimeException
   * @return
   * 
   * @author Mauricio Hincapie Monsalve.
   * @version 1.0
   */
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = DatabaseObjectProcessRuntimeException.class)
  @ResponseBody
  public RestResponse databaseObjectProcessRuntimeException(DatabaseObjectProcessRuntimeException databaseObjectProcessRuntimeException) {
    return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, databaseObjectProcessRuntimeException, databaseObjectProcessRuntimeException.getMessage(),
        null, false);
  }

  /**
   * 
   * 
   * @author Mauricio Hincapie Monsalve.
   */
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseBody
  public RestResponse fieldValidate(MethodArgumentNotValidException methodArgumentNotValidException) {
    return getResponse(HttpStatus.BAD_REQUEST, methodArgumentNotValidException,
        ErrorMessageEnum.INVALID_FIELDS_REQUEST.getMessage(), getDetailErrorMessage(methodArgumentNotValidException),
        false);
  }

  /**
   * 
   * 
   * @author Mauricio Hincapie Monsalve.
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = CarNotFoundRuntimeException.class)
  @ResponseBody
  public RestResponse tenderError(CarNotFoundRuntimeException tenderNotFoundException) {
    return getResponse(HttpStatus.NOT_FOUND, tenderNotFoundException,
        ErrorMessageEnum.CAR_PROCESS_ERROR_MESSAGE.getMessage(), null, false);
  }
  

  /**
   * 
   * @param forbbidenKontratacionRuntimeException
   * @return
   * 
   * @author Mauricio Hincapie Monsalve.
   * @version 1.0
   */
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = BadRequestRuntimeException.class)
  @ResponseBody
  public RestResponse userBadRequestRuntimeException(BadRequestRuntimeException badRequestRuntimeException) {
    return getResponse(HttpStatus.BAD_REQUEST, badRequestRuntimeException, badRequestRuntimeException.getMessage(),
        null, false);
  }

  /**
   * This method obtains detail error message generates from @validate
   * 
   * @param methodArgumentNotValidException
   * @return
   * 
   * @author Mauricio Hincapie Monsalve.
   */
  private String getDetailErrorMessage(MethodArgumentNotValidException methodArgumentNotValidException) {
    return methodArgumentNotValidException.getBindingResult().getAllErrors().stream().map(errorEnLista -> {
      return ErrorMessageEnum.INVALID_FIELDS_MESSAGE_REQUEST.getMessage()
          .replace("{0}", errorEnLista.getCodes()[1].split("[.]")[1]).replace("{1}", errorEnLista.getDefaultMessage());
    }).reduce((errorAcumulado, errorEnLista) -> errorAcumulado.concat(" | ").concat(errorEnLista)).get();
  }

  /**
   * 
   * @return
   * 
   * @author Mauricio Hincapie Monsalve.
   * 
   */
  private RestResponse getResponse(HttpStatus status, Throwable e, String message, String errorMessage,
      boolean printLog) {
    RestResponse response = new RestResponse();
    String stackTrace = ExceptionUtils.getStackTrace(e);
    String msg = new StringBuilder(e.getMessage()).toString();
    response.setMessage(message);
    response.setErrorMessage(errorMessage != null ? errorMessage : msg);

    if (printLog) {
      log.error(stackTrace);
    }

    return response;
  }

}

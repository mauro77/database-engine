package co.com.softcaribbean.databaseengine.shared.exception;

/**
 * 
 * <p>
 *  
 * </p>
 * 
 * @author Mauricio Hincapie Monsalve.
 *
 */
public class CarNotFoundRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 2040667881916844117L;

  /**
   * Constructor
   */
  public CarNotFoundRuntimeException() {
    super();
  }

  /**
   * Constructor
   *
   * @param mensaje
   */
  public CarNotFoundRuntimeException(String message) {
    super(message);
  }

  /**
   * Constructor
   *
   * @param causa
   */
  public CarNotFoundRuntimeException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor
   *
   * @param mensaje
   * @param causa
   */
  public CarNotFoundRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor
   *
   * @param mensaje
   * @param causa
   * @param habilitarSupresion
   * @param escribirTrazaError
   */
  public CarNotFoundRuntimeException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

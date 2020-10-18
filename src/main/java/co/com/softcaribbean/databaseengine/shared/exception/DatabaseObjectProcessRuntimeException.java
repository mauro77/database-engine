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
public class DatabaseObjectProcessRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 2040667881916844117L;

  /**
   * Constructor
   */
  public DatabaseObjectProcessRuntimeException() {
    super();
  }

  /**
   * Constructor
   *
   * @param mensaje
   */
  public DatabaseObjectProcessRuntimeException(String message) {
    super(message);
  }

  /**
   * Constructor
   *
   * @param causa
   */
  public DatabaseObjectProcessRuntimeException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor
   *
   * @param mensaje
   * @param causa
   */
  public DatabaseObjectProcessRuntimeException(String message, Throwable cause) {
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
  public DatabaseObjectProcessRuntimeException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

package co.com.softcaribbean.databaseengine.shared.exception;

/**
 * 
 * <p>
 * This generic bean use to returns RuntimeException customized
 * </p>
 * 
 * @author Camilo Rivera L.
 *
 */
public class GenericRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 2915909369040769236L;

  public GenericRuntimeException() {
    super();
  }

  public GenericRuntimeException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public GenericRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public GenericRuntimeException(String message) {
    super(message);
  }

  public GenericRuntimeException(Throwable cause) {
    super(cause);
  }

}

package co.com.softcaribbean.databaseengine.shared.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadRequestRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 5364116298022045532L;

  public BadRequestRuntimeException(String message) {
    super(message);
  }

  public BadRequestRuntimeException(Throwable cause) {
    super(cause);
  }

  public BadRequestRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadRequestRuntimeException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

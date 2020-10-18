package co.com.softcaribbean.databaseengine.shared.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RestResponse {

  String message;

  String errorMessage;

  public RestResponse(String message) {
    super();
    this.message = message;
  }

}

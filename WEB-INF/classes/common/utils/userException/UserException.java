package common.utils.userException;

public class UserException extends Exception {
  String errorMessage;

  public UserException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

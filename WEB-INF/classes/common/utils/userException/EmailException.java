package common.utils.userException;

public class EmailException extends Exception {
  String errorMessage;

  public EmailException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

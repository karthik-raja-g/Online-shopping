package common.utils.userException;

public class PhoneException extends Exception {
  String errorMessage;

  public PhoneException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

package common.utils.userException;

public class NameException extends Exception {
  String errorMessage;

  public NameException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

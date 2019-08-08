package common.utils.userException;

public class NoRecordFoundException extends Exception {
  String errorMessage;

  public NoRecordFoundException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

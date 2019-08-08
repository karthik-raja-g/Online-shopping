package common.utils.userException;

public class OrderException extends Exception {
  String errorMessage;

  public OrderException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

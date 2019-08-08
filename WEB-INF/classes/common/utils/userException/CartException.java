package common.utils.userException;

public class CartException extends Exception {
  String errorMessage;

  public CartException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

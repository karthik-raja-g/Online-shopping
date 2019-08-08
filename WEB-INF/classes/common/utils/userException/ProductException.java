package common.utils.userException;

public class ProductException extends Exception {
  String errorMessage;

  public ProductException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

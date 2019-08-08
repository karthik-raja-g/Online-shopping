package common.utils.userException;

public class RoleExceptionn extends Exception {
  String errorMessage;

  public RoleExceptionn(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}

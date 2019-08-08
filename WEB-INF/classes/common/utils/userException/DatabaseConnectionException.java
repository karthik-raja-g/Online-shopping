package common.utils.userException;

public class DatabaseConnectionException extends Exception {
  String errorCode;

  public DatabaseConnectionException(String errorCode,Throwable ex) {
    super(errorCode,ex);

  }
}

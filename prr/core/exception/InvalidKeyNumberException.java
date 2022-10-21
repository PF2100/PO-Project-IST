package prr.core.exception;

/** Thrown when an input key is not valid */
public class  InvalidKeyNumberException extends Exception {
  private static final long serialVersionUID = 202208091753L;
  String _key ;

  public InvalidKeyNumberException(String key) {
    super("Invalid key" + key);
    _key = key;
  }

  public String getKey() {
    return _key;
  }
}

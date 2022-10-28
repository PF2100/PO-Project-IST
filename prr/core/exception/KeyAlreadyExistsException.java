package prr.core.exception;

/** Thrown when an application is not associated with a file. */
public class  KeyAlreadyExistsException extends Exception {
  private static final long serialVersionUID = 202208091753L;

  private  String _key ;

  public KeyAlreadyExistsException(String key) {
    super("Key already exists" + key);
    _key = key;
  }

  public String getKey() {
    return _key;
  }
}
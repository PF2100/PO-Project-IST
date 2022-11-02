package prr.core.exception;

/** Thrown when an application is not associated with a file. */
public class  DestinationTerminalException extends Exception {
  private static final long serialVersionUID = 202208091753L;

  private String _key;

  public DestinationTerminalException(String key) {
    super("Destination was innacessible");
    _key = key;
  }

  public String getKey(){
    return _key;
  }

}
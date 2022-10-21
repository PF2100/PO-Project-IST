package prr.core.exception;


/**
 * Exception for unknown clients.
 */
public class  UnknownClientException extends Exception {
	private static final long serialVersionUID = 202208091753L;
  
	String _key ;
  
	public UnknownClientException(String key) {
	  super("Client "+key+" does  exist" );
	  _key = key;
	}
  
	public String getKey() {
	  return _key;
	}
  }
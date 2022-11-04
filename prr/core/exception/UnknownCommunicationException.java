package prr.core.exception;


/**
 * Exception for unknown clients.
 */
public class  UnknownCommunicationException extends Exception {
	private static final long serialVersionUID = 202208091753L;
  
	private int _key ;
  
	public UnknownCommunicationException(int key) {
	  super("Communication"+key+" does  exist" );
	  _key = key;
	}
  
	public int getKey() {
	  return _key;
	}
  }
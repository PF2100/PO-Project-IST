
package prr.core.exception;

public class  UnknownTerminalException extends Exception {
	private static final long serialVersionUID = 202208091753L;
  
	String _key ;
  
	public UnknownTerminalException(String key) {
	  super("Terminal "+key+" does  exist" );
	  _key = key;
	}
  
	public String getKey() {
	  return _key;
	}
  }
package prr.core.exception;

/**
 * Exception for unknown import file entries.
 */
public class UnknownIdentifierException extends Exception {

  /** Class serial number. */
  private static final long serialVersionUID = 202214101406L;
  
  /** Bad bad entry specification. */
  private String _entrySpecification;
  
  /**
   * @param entrySpecification
   */
  public UnknownIdentifierException(String entrySpecification) {
    _entrySpecification = entrySpecification;
  }
  
  /**
   * @param entrySpecification
   * @param cause
   */
  public UnknownIdentifierException(String entrySpecification, Exception cause) {
    super(cause);
    _entrySpecification = entrySpecification;
  }
  
  /**
   * @return the bad entry specification.
   */
  public String getEntrySpecification() {
    return _entrySpecification;
  }
}
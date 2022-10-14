package prr.core;

import java.io.Serializable;
import java.util.Map;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */


enum TerminalMode {
  BUSY,
  ON,
  SILENCE,
  OFF
}



abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  private String _id;
  private TerminalMode _mode;
  private double _debt;
  private double _payments;
  private Map<String,Terminal> friends;
  private Client _owner;
  //Colocar Comunicacoes


  public Terminal(String terminalID, Client owner, String Mode) {
    _id = terminalID;
    _mode = Mode.valueOF(Mode) ;// AYO? 
    _debt = 0; 
    _payments = 0;
    _owner = owner;
  }
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods
  
  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
    return true; // Tava a dar erro
  }
  
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    // FIXME add implementation code
    return true; // Tava a dar erro
  }
}

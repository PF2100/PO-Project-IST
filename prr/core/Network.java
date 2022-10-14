package prr.core;

import java.io.Serializable;
import java.io.IOException;
import prr.core.exception.UnrecognizedEntryException;
import prr.core.Parser;
import prr.core.Terminal;
import java.util.Map;


// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  private Map<String,Client> _clients;

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  
  
   void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
    Parser _parser = new Parser(this);
    _parser.parseFile(filename);
  }

  public void registerClient(String id , String nome , int taxNumber) {
    //Adicionar novas cenas
  }

  /* 
  public Terminal registerTerminal(String terminalID , String ClientID , String mode) {
    Terminal t = new Terminal(terminalID,_clients.get(ClientID) ) ;
      
    return t;
  }
  */
 

}


package prr.core;

import java.io.Serializable;
import java.io.IOException;
import prr.core.exception.UnrecognizedEntryException;
import prr.core.Parser;
import prr.core.Terminal;
import prr.app.exception.DuplicateClientKeyException;
import prr.core.Client;
import prr.app.exception.DuplicateClientKeyException;

import java.util.HashMap;
import java.util.Map;


// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  private Map<String,Client> _clients = new HashMap<>();
  private Map<String,Terminal> _terminals = new HashMap<>();

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

  public void registerClient(String id , String nome , int taxNumber) throws DuplicateClientKeyException { //É preciso adicionar a duplicateKeyexception
    
    if ( _clients.get(id) != null) {
      throw new DuplicateClientKeyException(id);
    }
    else {
      Client client = new Client(id, nome, taxNumber);
      _clients.put(id, client);
    }
  }

 
  public Terminal registerTerminal(String type,String terminalID , String clientID ) {
    Terminal terminal;
    Client client = _clients.get(clientID); //Throw de nao existir client;
    if ( type == "BASIC") {
      terminal = new Terminal(terminalID,client);
    }
    else{
      terminal = new FancyTerminal(terminalID,client);
    }
    client.addTerminal(terminal); //Faz sentido ter aqui o addTerminal? ou fazer isso no do Register
      return terminal;
  }

  public void addFriend(String terminal,String friend ) {
    Terminal friend1= _terminals.get(terminal);
    Terminal friend2 = _terminals.get(friend);
    friend1.treatFriends(friend2);
    //FIXME é preciso mudar umas cenas
  }

  public Map getClients () throws NullPointerException{
    return _clients;
  }

  public Map getTerminals () throws NullPointerException{
    return _terminals;
  }
}


package prr.core;

import java.io.Serial;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.io.IOException;

import prr.core.exception.UnrecognizedEntryException;
import prr.core.exception.KeyAlreadyExistsException;
import prr.core.exception.InvalidKeyNumberException;

import prr.core.Parser;
import prr.core.Terminal;
import prr.core.Client;
import prr.core.exception.*;

import prr.app.exception.DuplicateClientKeyException;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;


// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  private Map<String,Client> _clients = new HashMap<>();
  private Map<String,Terminal> _terminals = new HashMap<>();

  /** Serial number for serialization. */
  @Serial
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

  public void registerClient(String id, String nome, int taxNumber) throws KeyAlreadyExistsException { //É preciso adicionar a duplicateKeyexception
    
    if (_clients.containsKey(id)) {
      throw new KeyAlreadyExistsException();
    }
    else {
      Client client = new Client(id, nome, taxNumber);
      _clients.put(id, client);
    }
  }

 
  public Terminal registerTerminal(String type,String terminalID , String clientID ) throws KeyAlreadyExistsException, UnknownClientException, InvalidKeyNumberException {
    Terminal terminal;
    Client client = getClient(clientID); //Throws UnknownClientException;
    if (_terminals.containsKey(terminalID)) {
      throw new KeyAlreadyExistsException();
    }
    if ( type.equals("BASIC")) {
      terminal = new Terminal(terminalID,client); //Throws  InvalidKeyNumberException and NumberFormatException
    }
    else{
      terminal = new FancyTerminal(terminalID,client); //Throws  InvalidKeyNumberException and NumberFormatException
    }
    client.addTerminal(terminal);
    _terminals.put(terminalID, terminal);
      return terminal;
  }

  public void addFriend(String terminal,String friend ) {
    Terminal friend1= _terminals.get(terminal);
    Terminal friend2 = _terminals.get(friend);
    friend1.addFriend(friend2);        //Colocar excecao do cliente n existir
    //FIXME é preciso mudar umas cenas
  }

  public Map<String,Client> getClients () { // aplicar clonable
    return _clients;
  }

  public Map<String,Terminal> getTerminals() { // aplicar clonable{
    return _terminals;
  }

  public Client getClient(String id) throws UnknownClientException {
    if (!(_clients.containsKey(id))) {
      throw new UnknownClientException();
    }
    return _clients.get(id);
  }

  public String showClient(String clientID)  throws UnknownClientException {
    if (!(_clients.containsKey(clientID))) {
      throw new UnknownClientException();
    }
    return _clients.get(clientID).toString();
  }

  public Terminal getTerminal(String terminalID) throws UnknownClientException {
    if(!(_terminals.containsKey(terminalID))) {
      throw new UnknownClientException();
    }
    return _terminals.get(terminalID);
  }

  public List<String> showClients()  {
    List<String> clientsToString = new ArrayList<>();
    List<String> clientIDS = new ArrayList<>(_clients.keySet());
    Collections.sort(clientIDS,new IdComparator());

    for ( String elemento : clientIDS) {
      clientsToString.add(_clients.get(elemento).toString());
    }
    return clientsToString;
  }

  public String Terminal(String terminalID)  throws UnknownClientException {
    if (!(_terminals.containsKey(terminalID))) {
      throw new UnknownClientException();
    }
    return _terminals.get(terminalID).toString();
  }

  public List<String> showTerminals()  {
    List<String> terminalsToString = new ArrayList<>();
    List<String> terminalIDS = new ArrayList<>(_terminals.keySet());
    Collections.sort(terminalIDS,new IdComparator());

    for ( String elemento : terminalIDS) {
      terminalsToString.add(_terminals.get(elemento).toString());
    }
    return terminalsToString;
  }

  public List<String> showUnusedTerminals()  {
    List<String> terminalsToString = new ArrayList<>();
    List<String> terminalIDS = new ArrayList<>(_terminals.keySet());
    Collections.sort(terminalIDS,new IdComparator());

    for (String elemento : terminalIDS) {
     if (_terminals.get(elemento).getMadeCommunications() == null &&
             _terminals.get(elemento).getReceivedCommunications() == null) {
       terminalsToString.add(_terminals.get(elemento).toString());
     }}
    return terminalsToString;
  }
}

  class IdComparator implements Comparator<String> {
    public int compare(String id1, String id2) {
      return id1.compareToIgnoreCase(id2);
    }
  }


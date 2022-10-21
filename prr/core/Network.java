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

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;



/**
 * Class Network implements Serializable.
 */
public class Network implements Serializable {

  private Map<String,Client> _clients = new HashMap<>();
  private Map<String,Terminal> _terminals = new HashMap<>();

  /** Serial number for serialization. */
  @Serial
  private static final long serialVersionUID = 202208091753L;
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO error while processing the text file
   */
  
   void importFile(String filename) throws UnrecognizedEntryException, IOException  {
    Parser _parser = new Parser(this);
    _parser.parseFile(filename);
  }

  
  /** 
   * @param key  client's key
   * @param nome Client's name
   * @throws KeyAlreadyExistsExceptionif If already exists a client with the inputed key, then throws the exception
   */
  public void registerClient(String key, String nome, int taxNumber) throws KeyAlreadyExistsException {
    
    if (_clients.containsKey(key)) {throw new KeyAlreadyExistsException(key);}
    else {
      Client client = new Client(key, nome, taxNumber);
      _clients.put(key, client);
    }
  }

 
  
  /** 
   * @param type Terminal type (FANCY or BASIC)
   * @param terminalkey Terminal's key
   * @param clientKey Client's key associated with the terminal
   * @return Terminal
   * @throws KeyAlreadyExistsException if the terminal key already exists then throws the exception
   * @throws UnknownClientException   if the client associated with the terminal doesn't exist, throws the exception
   * @throws InvalidKeyNumberException if the terminal key is invalid , throws the exception
   */

  public Terminal registerTerminal(String type,String terminalID , String clientKey ) throws KeyAlreadyExistsException, UnknownClientException, InvalidKeyNumberException {
    Terminal terminal;
    Client client = getClient(clientKey); //Throws UnknownClientException;
    if (_terminals.containsKey(terminalID)) {throw new KeyAlreadyExistsException(terminalID);}
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

  
  /** 
   * @param terminal Terminal who will befriend another Terminal
   * @param friend Terminal who will be the friend
   */
  public void addFriend(String terminal,String friend ) {
    Terminal friend1= _terminals.get(terminal);
    Terminal friend2 = _terminals.get(friend);
    friend1.addFriend(friend2);
  }

  
  /** 
   * @param key Client's key
   * @return Client associated with the client's key
   * @throws UnknownClientException if there is no client associated with the client's key
   */
  public Client getClient(String key) throws UnknownClientException {
    if (!(_clients.containsKey(key))) {
      throw new UnknownClientException(key);
    }
    return _clients.get(key);
  }

  
  /** 
   * @param clientKey Client's key
   * @return Client's toString form 
   * @throws UnknownClientException if there is no client associated with the client's key
   */
  public String showClient(String clientKey)  throws UnknownClientException {
    Client client = getClient(clientKey); 
    return client.toString();
  }


    /** 
   * @return List<String>
   */
  public List<String> showClients()  {
    List<String> clientsToString = new ArrayList<>();
    List<String> clientKeys = new ArrayList<>(_clients.keySet());
    Collections.sort(clientKeys,new IdComparator());

    for ( String elemento : clientKeys) {
      clientsToString.add(_clients.get(elemento).toString());
    }
    return clientsToString;
  }
  
  /** 
   * @param terminalID terminal's ID
   * @return Terminal associated with the terminalID 
   * @throws UnknownTerminalException  if there is no terminal associated with the terminalID
   */
  public Terminal getTerminal(String terminalID) throws UnknownTerminalException {
    if(!(_terminals.containsKey(terminalID))) {
      throw new UnknownTerminalException (terminalID);
    }
    return _terminals.get(terminalID);
  }

  
  /** 
   * @param terminalID terminal's ID
   * @return terminal's toString form
   * @throws UnknownTerminalException  if there is no terminal associated with terminalID
   */
  public String showTerminal(String terminalID)  throws UnknownTerminalException  {
    Terminal terminal = getTerminal(terminalID);
    return _terminals.get(terminalID).toString();
  }

  
  /** 
   * @return String list with all of the toString form of all the Terminals
   */
  public List<String> showTerminals() {
    List<String> terminalsToString = new ArrayList<>();
    List<String> terminalIDs = new ArrayList<>(_terminals.keySet());
    Collections.sort(terminalIDs,new IdComparator());

    for ( String elemento : terminalIDs) {
      terminalsToString.add(_terminals.get(elemento).toString());
    }
    return terminalsToString;
  }

  
  /** 
   * @return String list with all of the toString form of all the unused Terminals
   */


  public List<String> showUnusedTerminals()  {
    List<String> terminalsToString = new ArrayList<>();
    List<String> terminalIDs = new ArrayList<>(_terminals.keySet());
    Collections.sort(terminalIDs,new IdComparator());

    for (String elemento : terminalIDs) {
     if (_terminals.get(elemento).getMadeCommunications() == null &&
             _terminals.get(elemento).getReceivedCommunications() == null) {
       terminalsToString.add(_terminals.get(elemento).toString());
     }}
    return terminalsToString;
  }
}


class IdComparator implements Comparator<String> {
  public int compare(String key1, String key2) {return key1.compareToIgnoreCase(key2);}}


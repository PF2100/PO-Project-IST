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
import prr.core.Communication;
import prr.core.exception.*;

import java.util.SortedMap;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.AbstractAction;

import java.util.Map;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;



/**
 * Class Network implements Serializable.
 */
public class Network implements Serializable {

  private Map<String,Client> _clients = new TreeMap<>(new IdComparator());
  private Map<String,Terminal> _terminals = new TreeMap<>(new IdComparator());
  private Map<Integer,Communication> _communications = new TreeMap<>();
  private int _nComms = 0;

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
    Parser parser = new Parser(this);
    parser.parseFile(filename);
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

  public Terminal registerTerminal(String type,String terminalId , String clientKey ) throws KeyAlreadyExistsException, UnknownClientException, InvalidKeyNumberException {
    Terminal terminal;
    Client client = getClient(clientKey); //Throws UnknownClientException;
    if (_terminals.containsKey(terminalId)) {throw new KeyAlreadyExistsException(terminalId);}
    if ( type.equals("BASIC")) {
      terminal = new BasicTerminal(terminalId,client); //Throws  InvalidKeyNumberException and NumberFormatException
    }
    else{
      terminal = new FancyTerminal(terminalId,client); //Throws  InvalidKeyNumberException and NumberFormatException
    }
    client.addTerminal(terminal);
    _terminals.put(terminalId, terminal);
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
    for ( Client client : _clients.values()) {    //Pode ficar simplificado
      clientsToString.add(client.toString());
    }
    return clientsToString;
  }
  
  /** 
   * @param terminalId terminal's ID
   * @return Terminal associated with the terminalId  
   * @throws UnknownTerminalException  if there is no terminal associated with the terminalId
   */
  public Terminal getTerminal(String terminalId) throws UnknownTerminalException {
    if(!(_terminals.containsKey(terminalId))) {
      throw new UnknownTerminalException (terminalId);
    }
    return _terminals.get(terminalId);
  }


  /** 
   * @param terminalId terminal's ID
   * @return terminal's toString form
   * @throws UnknownTerminalException  if there is no terminal associated with terminalID
   */
  public String showTerminal(String terminalId)  throws UnknownTerminalException  {
    Terminal terminal = getTerminal(terminalId);
    return terminal.toString();
  }

  public boolean enableClientNotifications(String clientKey) throws UnknownClientException{
    Client client = getClient(clientKey);
    return client.turnNotificationsOn();
    /*Falta aplicar lógica de notificações */
  }
  
  public boolean disableClientNotifications(String clientKey) throws UnknownClientException{
    Client client = getClient(clientKey);
    return client.turnNotificationsOff();
    /*Falta aplicar lógica de notificações */
  }

  /** 
   * @return String list with all of the toString form of all the Terminals
   */
  public List<String> showTerminals() {
    List<String> terminalsToString = new ArrayList<>(); //Pode ficar simplificado
    for ( Terminal terminal : _terminals.values()) {
      terminalsToString.add(terminal.toString());
    }
    return terminalsToString;
  }

  
  /** 
   * @return String list with all of the toString form of all the unused Terminals
   */


  public List<String> showUnusedTerminals()  {
    List<String> terminalsToString = new ArrayList<>();
    for (Terminal terminal : _terminals.values()) {
     if (terminal.getMadeCommunications() == null && terminal.getReceivedCommunications() == null) { //pode ficar simplificado?
       terminalsToString.add(terminal.toString());
     }}
    return terminalsToString;
  }

  
  public void makeSms(Terminal makerTerminal,String terminalId,String message) throws UnknownTerminalException, DestinationTerminalException{
    Terminal terminal = getTerminal(terminalId);
    Communication communication = makerTerminal.makeSms(terminal,message);
    if( communication != null) {
      addCommunication(communication);
    }
  }

  public void addCommunication(Communication communication) {
    _nComms++;
    communication.setId(_nComms);
    _communications.put(communication.getId(),communication);
  }
 
  public List<String> showCommunications() {
    List<String> communicationsToString = new ArrayList<>();
    for ( Communication communication : _communications.values()) { //Pode ficar simplificado
      communicationsToString.add(communication.toString());
    }
    return communicationsToString;
  }

  

  public List<String> getClientReceivedCommunicationStrings(String clientKey) throws UnknownClientException{
    Client client = getClient(clientKey);
    List<Communication> receivedCommunications = new ArrayList<>(client.getReceivedCommunications());
    Collections.sort(receivedCommunications,new CommunicationComparator());
    return getAllCommunications(receivedCommunications);
  }

  public List<String> getClientMadeCommunicationsStrings( String clientKey) throws UnknownClientException{
    Client client = getClient(clientKey);
    List<Communication> madeCommunications = new ArrayList<>(client.getMadeCommunications());
    Collections.sort(madeCommunications,new CommunicationComparator());
    return getAllCommunications(madeCommunications);

  
  }

  /*
  public List<String> getAll(Collection<Object> coll){
    List<Object> strings = new ArrayList<>();
    for(var obj : coll) {
        strings.add(obj.toString());
    }
    return strings;
  }
  */

  public List<String> getAllCommunications(Collection<Communication> comms){
    List<String> communicationStrings = new ArrayList<>();
    List<Communication> communications = new ArrayList<>(comms);
    for(Communication communication : communications) {   //Preciso que isto fique de maneira a que funcione para supertipo, e assim não tenho de repetir a mesma cena 20 vezes
      communicationStrings.add(communication.toString());
    }
    return communicationStrings;
  }

}



class IdComparator implements Comparator<String> ,Serializable {
  public int compare(String key1, String key2) {return key1.compareToIgnoreCase(key2);}}


class CommunicationComparator implements Comparator<Communication>,Serializable {
  public int compare(Communication that, Communication other) {return that.getId() - other.getId();};
}
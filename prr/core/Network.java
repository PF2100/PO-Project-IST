package prr.core;

import java.io.Serial;
import java.io.Serializable;
import java.io.IOException;
import prr.core.exception.*;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import prr.app.IdComparator;
import prr.app.CommunicationComparator;
import prr.app.DebtComparator;


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
   * @throws KeyAlreadyExistsException If already exists a client with the inputed key, then throws the exception
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
   * @param terminalId Terminal's key
   * @param clientKey Client's key associated with the terminal
   * @return Terminal
   * @throws KeyAlreadyExistsException if the terminal key already exists then throws the exception
   * @throws UnknownClientException   if the client associated with the terminal doesn't exist, throws the exception
   * @throws InvalidKeyNumberException if the terminal key is invalid , throws the exception
   */

  public Terminal registerTerminal(String type, String terminalId, String clientKey ) throws KeyAlreadyExistsException, UnknownClientException, InvalidKeyNumberException {
    Terminal terminal;
    Client client = getClient(clientKey); //Throws UnknownClientException;
    if (_terminals.containsKey(terminalId)) {throw new KeyAlreadyExistsException(terminalId);}
    if (type.equals("BASIC")) {
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

  public List<String> getNotifications(Client client) {
      List <String> notificationStrings = new ArrayList<>();
      for(Notification notification : client.getNotifications()){
          notificationStrings.add(notification.toString());
      }
      client.clearNotifications();
      return notificationStrings;
    }

  /* 
  * @return List<Client>
  */

  public List<Client> getClients() {
    return new ArrayList<Client>( _clients.values());
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

  
  public boolean enableClientNotifications(String clientKey) throws UnknownClientException{
    Client client = getClient(clientKey);
    return client.turnNotificationsOn();
  }
  
  public boolean disableClientNotifications(String clientKey) throws UnknownClientException{
    Client client = getClient(clientKey);
    return client.turnNotificationsOff();
  }

  /** 
   * @return String list with all of the toString form of all the Terminals
   */
  public List<Terminal> getTerminals() {
    return new ArrayList<Terminal> (_terminals.values());
  }

  /** 
   * @return String list with all of the toString form of all the unused Terminals
   */

  public List<Terminal> getUnusedTerminals()  {
    List<Terminal> unusedTerminals = new ArrayList<>();
    for (Terminal terminal : _terminals.values()) {
     if (terminal.isUnused()) { 
       unusedTerminals.add(terminal);
     }}
    return Collections.unmodifiableList(unusedTerminals);
  }

  public void makeSms(Terminal from,String terminalId,String message) throws UnknownTerminalException, DestinationTerminalException{
    Terminal terminal = getTerminal(terminalId);
    Communication communication = from.makeSms(terminal,message);
    addCommunication(communication);
  }

  public void addCommunication(Communication communication) {
    _nComms++;
    communication.setId(_nComms);
    _communications.put(communication.getId(),communication);
  }

  public List<Communication> getCommunications() {
    return new ArrayList<Communication> (_communications.values());
  }
  
  public List<Communication> getClientReceivedCommunications(String clientKey) throws UnknownClientException{
    Client client = getClient(clientKey);
    List<Communication> receivedCommunications = new ArrayList<>(client.getReceivedCommunications());
    Collections.sort(receivedCommunications,new CommunicationComparator());
    return receivedCommunications;
  }


  public List<Communication> getClientMadeCommunications( String clientKey) throws UnknownClientException{
    Client client = getClient(clientKey);
    List<Communication> madeCommunications = new ArrayList<>(client.getMadeCommunications());
    Collections.sort(madeCommunications,new CommunicationComparator());
    return madeCommunications;
  }


  public void addFriend(String selectedTerminalKey,String friendKey) throws UnknownTerminalException {
    Terminal selectedTerminal = getTerminal(selectedTerminalKey);
    Terminal friend = getTerminal(friendKey);
    selectedTerminal.addFriend(friend);
  }


  
  public void removeFriend(Terminal selectedTerminal,String friendKey) throws UnknownTerminalException {
    Terminal friend = getTerminal(friendKey);
    selectedTerminal.removeFriend(friend);
  }

  public void startInteractiveCommunication(String type,Terminal from, String receiverId) throws UnknownTerminalException,DestinationTerminalException{
    Terminal to = getTerminal(receiverId);
    Communication communication = null;
    if(from.equals(to)){throw new DestinationTerminalException("BUSY");}
    else if (type.equals("VOICE")) {communication = from.makeVoiceCall(to);}
    else{communication = from.makeVideoCall(to);}
    addCommunication(communication);
  }

  public double endOngoingCommunication(int duration,Terminal terminal) {
    return terminal.endOngoingCommunication(duration);
  }

  public Communication getCommunication(Integer key) throws UnknownCommunicationException {
    if (!(_communications.containsKey(key))) {
      throw new UnknownCommunicationException(key);
    }
    return _communications.get(key);
  }

  public void payCommunication(Terminal terminal, Integer communicationId) throws UnknownCommunicationException {
    Communication communication = getCommunication(communicationId);
    if(!communication.isPaid() && !communication.isOngoing() && communication.getFrom().equals(terminal) ) {
      communication.payCommunication();
    }
    else {throw new UnknownCommunicationException(communication.getId());}
  }

  public Communication getOngoingCommunication(Terminal terminal){
    Communication communication = terminal.getOngoingCommunication();
    return communication;
  }

  public List<Client> getClientsWithoutDebts()  {
    List<Client> clientsWithoutDebts = new ArrayList<>();
    for (Client client : _clients.values()) {
     if (client.getDebts() == 0) { 
       clientsWithoutDebts.add(client);
     }}
    return Collections.unmodifiableList(clientsWithoutDebts);
  }

  public List<Client> getClientsWithDebts()  {
    List<Client> clientsWithDebts = new ArrayList<>();
    for (Client client : _clients.values()) {
     if (client.getDebts() != 0) { 
       clientsWithDebts.add(client);
     }}
    Collections.sort(clientsWithDebts,new DebtComparator());
    return Collections.unmodifiableList(clientsWithDebts);
  }


  public List<Terminal> getTerminalWithPosBalance()  {
    List<Terminal> terminalsWithPosBalance = new ArrayList<>();
    for (Terminal terminal : _terminals.values()) {
    if (terminal.getBalance() > 0) { 
      terminalsWithPosBalance.add(terminal);
    }}
    return Collections.unmodifiableList(terminalsWithPosBalance);
  }

  public double getDebts(){
    double debt = 0;
    for(Client client : _clients.values()) {
        debt += client.getDebts();
    }
    return debt;
}

  public double getPayments(){
      double payments = 0;
      for(Client client : _clients.values()) {
        payments += client.getPayments();
    }
      return payments;
  }
}
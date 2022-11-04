package prr.core;

import java.io.Serial;
import java.io.Serializable;

import java.util.regex.Pattern;
import prr.core.exception.*;

import java.util.*;




public abstract class Terminal implements Serializable /* FIXME maybe addd more interfaces */ {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    private final String _id;

    protected TerminalState _state;
    protected TerminalState _previous;


    private Map<String, Terminal> _friends = new TreeMap<>(new IdComparator());
    private List<Client> _toNotify = new ArrayList<>();
    private Client _owner;
    private List<Communication> _madeCommunications = new ArrayList<>();
    private List<Communication> _receivedCommunications = new ArrayList<>();

    private InteractiveCommunication _ongoingCommunication;



    public Terminal(String terminalId, Client owner) throws InvalidKeyNumberException {
        if(!(Pattern.matches("[0-9]{6}", terminalId))) {
            throw new InvalidKeyNumberException(terminalId);
        }
        _id = terminalId;
        _owner = owner;
        _state = new IdleState(this);
        _ongoingCommunication = null;
    }

    public void notifyClients(String type){
        for(Client client : _toNotify){
            client.getNotification(this,type);
        }
        _toNotify.clear();
    }


    public void addNotifiableClient(Communication communication) {
        if(communication.getClientOwner().canReceiveNotifications()) {
         _toNotify.add(communication.getFrom().getOwner());
      
        }
    }


    public String getId() {return _id;}

    public TerminalState getState() {return _state;}


    public Collection<Communication> getMadeCommunications(){
        return _madeCommunications;
    }


    public Collection<Communication> getReceivedCommunications() {
        return _receivedCommunications;
    }

    protected Collection<String> getFriends() {return _friends.keySet();}

    public Client getOwner() {return _owner;}



    //Returns the communication if it was successfuly made, null if not
    public Communication makeSms(Terminal to, String message) throws DestinationTerminalException{
        Communication communication = _state.makeSms(to,message);
        communication.calculateCost();
        return communication;
    }

    protected void acceptSms(Communication communication) throws DestinationTerminalException {
        _state.acceptSms(communication);
    }

    public Communication makeVoiceCall(Terminal to) throws DestinationTerminalException {
        return _state.makeVoiceCall(to);
    }

    protected void acceptVoiceCall(Communication communication) throws DestinationTerminalException {
        _state.acceptVoiceCall(communication);
    }

    public abstract Communication makeVideoCall(Terminal to) throws DestinationTerminalException;


    public abstract void acceptVideoCall(Communication communication) throws DestinationTerminalException;


    
    public long endOngoingCommunication(int duration) {
        _ongoingCommunication.setDuration(duration);
        _ongoingCommunication.calculateCost();
        _ongoingCommunication.stopOngoing();
        double price = _ongoingCommunication.getCost();
        Terminal to = _ongoingCommunication.getTo();
        to.setOngoingCommunication(null);
        setOngoingCommunication(null);
        to.returnState();
        returnState();
        return Math.round(price);
    }



    public Communication getOngoingCommunication() {
        return _ongoingCommunication;
    }




    public void setOngoingCommunication(Communication ongoingCommunication) {
        _ongoingCommunication = (InteractiveCommunication)ongoingCommunication;
    }

    

    public void returnState(){
        _state.returnState();
    }

    public boolean setOnIdle() {return _state.setOnIdle();}

    public boolean setBusy() {return _state.setBusy();}

    public boolean setOnSilent() {return _state.setOnSilent();}

    public boolean turnOff() {return _state.turnOff();}

    public abstract String toString();



    
    

  
   
    /* 
    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive communication) and
     * it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
        return _state.canEndCurrentCommunication();
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     *
    /* */
    public boolean canStartCommunication() {
        return _state.canStartCommunication();
    }

    


    public boolean isUnused() {
        return getMadeCommunications().isEmpty() && getReceivedCommunications().isEmpty();
    } 

    public void addFriend(Terminal friend) {
        if ( !this.equals(friend) && !isFriend(friend)) {
            _friends.put(friend.getId(),friend);
        }
    }

    public void removeFriend(Terminal friend) {
        if (isFriend(friend)) {
            _friends.remove(friend.getId());
        }
    }

    public boolean isFriend(Terminal friend) {
        return _friends.containsKey(friend.getId());
    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof Terminal ){
            return (this.getId().compareTo( ((Terminal)other).getId())) == 0;
        }
        return false;
    }


    public void setTerminalState(TerminalState state) {
        _state = state;
    }

    public void setPreviousState(TerminalState state) {
        _previous = state;
    }

    public void addMadeCommunication(Communication communication) {
        _madeCommunications.add(communication);
    }

    public void addReceivedCommunication(Communication communication) {
        _receivedCommunications.add(communication);
    }


    public double getDebts(){
        double debt = 0;
        for(Communication communication : _madeCommunications) {
            if(!communication.isPaid()){
                debt += communication.getCost();
            }
        }
        return debt;
    }

    public double getPayments(){
        double payments = 0;
        for(Communication communication : _madeCommunications) {
            if(communication.isPaid()){
                payments += communication.getCost();
            }
        }
        return payments;
    }

    public double getBalance() {
        return getPayments() - getDebts(); 
    }
}

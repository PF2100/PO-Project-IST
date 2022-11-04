package prr.core;

import java.io.Serializable;


public class Notification implements Serializable{
    
    private String _terminalId;
    private String _type;


    Notification(Terminal terminal,String type){
        _terminalId = terminal.getId();
        _type = type;
    }

    public boolean equals(Notification other){
        Boolean teste1 =  this.getTerminalId().equals(other.getTerminalId());
        Boolean teste2 = this.getTerminalId().equals(other.getTerminalId());
        return (this.getTerminalId()).equals(other.getTerminalId()) && (this.getType()).equals(other.getType());
    }

    public boolean equals(Object other) {
        if(other instanceof Notification ){
            return ((Notification)other).getTerminalId().equals(_terminalId) && ((Notification)other).getType().equals(_type);
        }
        return false;
    }


    public String getTerminalId(){
        return _terminalId;
    }

    public String getType(){
        return _type;
    }

    public String toString(){
        return getType() +"|"+getTerminalId();
    }
}

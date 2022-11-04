package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.DestinationTerminalException;
import prr.core.exception.UnknownTerminalException;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("terminalKey", Message.terminalKey());
    addOptionField("communicationType", Message.commType(),"VIDEO","VOICE");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminalKey = stringField("terminalKey");
    String communicationType = stringField("communicationType");
    try{
      _network.startInteractiveCommunication(communicationType, _receiver, terminalKey);
    }catch(UnknownTerminalException ute){
      throw new UnknownTerminalKeyException(terminalKey);
    }catch(DestinationTerminalException dte){
      switch(dte.getKey()){
        case "ORIGIN" :
          _display.popup(Message.unsupportedAtOrigin(terminalKey, communicationType));
          break;
        case "RECEIVE":
          _display.popup(Message.unsupportedAtDestination(terminalKey, communicationType));
          break;
        case "OFF" :
          _display.popup(Message.destinationIsOff(terminalKey));
          break;
        case "BUSY":
           _display.popup(Message.destinationIsBusy(terminalKey));
           break;
        case "SILENCE":
          _display.popup(Message.destinationIsSilent(terminalKey));
          break;
      }
    }
  }
}

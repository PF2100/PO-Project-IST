package prr.app.terminal;

import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnknownCommunicationException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

  DoShowOngoingCommunication(Network context, Terminal terminal) {
    super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
      Communication communication = _network.getOngoingCommunication(_receiver);
      if(communication == null)  {
        _display.popup(Message.noOngoingCommunication());
      }
      else{_display.popup(communication.toString());}
  }
}

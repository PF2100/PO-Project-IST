package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnknownCommunicationException;
import pt.tecnico.uilib.menus.CommandException;
// Add more imports if needed

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    addIntegerField("communicationId",Message.commKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    Integer communicationId = integerField("communicationId");
    try{
    _network.payCommunication(_receiver, communicationId);
    }catch(UnknownCommunicationException uce) {
      _display.popup(Message.invalidCommunication());
    }

  }
}

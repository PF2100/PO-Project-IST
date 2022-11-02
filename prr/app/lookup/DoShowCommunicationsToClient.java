package prr.app.lookup;

import prr.app.exception.UnknownClientKeyException;

import prr.core.Network;
import prr.core.exception.UnknownClientException;
import prr.core.Communication;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.*;
//FIXME add more imports if needed

/**
 * Show communications to a client.
 */
class DoShowCommunicationsToClient extends Command<Network> {

  DoShowCommunicationsToClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_TO_CLIENT, receiver);
    addStringField("clientKey",Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String client = stringField("clientKey");
    try{
    List<Communication> receivedCommunications = _receiver.getClientReceivedCommunications(client);
      for (Communication communication : receivedCommunications ){
        _display.addLine(communication.toString());
      }
      _display.display();
    }catch(UnknownClientException uce){
      throw new UnknownClientKeyException(client);
    }
  }
}

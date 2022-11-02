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
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {

  DoShowCommunicationsFromClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
    addStringField("clientKey",Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String client = stringField("clientKey");
    try{
    List<Communication> madeCommunications = _receiver.getClientMadeCommunications(client);
      for (Communication communication : madeCommunications ){
        _display.addLine(communication.toString());
      }
      _display.display();
    }catch(UnknownClientException uce){
      throw new UnknownClientKeyException(client);
    }
  }
}

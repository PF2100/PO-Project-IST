package prr.app.client;

import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.client.Message;
import prr.core.*;
import java.util.*;

//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("clientKey",Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
    Map <String,Client> clients = _receiver.getClients();      //Colocar exceção de verificar se existe o Map está nulo ou não
    Client client = clients.get(stringField("clientKey")); 
    _display.popup(client.toString()); 
    }catch ( NullPointerException npe) {
      throw new NullPointerException();
    }
  }
}

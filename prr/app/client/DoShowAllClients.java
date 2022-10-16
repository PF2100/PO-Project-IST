package prr.app.client;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.Client;
import java.util.Map;
//FIXME add more imports if needed

/**
 * Show all clients.
 */
class DoShowAllClients extends Command<Network> {

  DoShowAllClients(Network receiver) {
    super(Label.SHOW_ALL_CLIENTS, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      Map<String,Client> clients = _receiver.getClients();
      for (String clientID : clients.keySet()){
        String client = (clients.get(clientID)).toString();
        _display.addLine(client);
      }
      _display.display();
    }catch(NullPointerException npe) {
      throw new NullPointerException();
    }
  }
}

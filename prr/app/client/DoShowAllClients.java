package prr.app.client;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.Client;
import java.util.*;

import prr.core.exception.UnknownClientException;

/**
 * Show all clients.
 */
class DoShowAllClients extends Command<Network> {

  DoShowAllClients(Network receiver) {
    super(Label.SHOW_ALL_CLIENTS, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
      List<String> clients = _receiver.showClients();
      for (String clientString : clients ){
        _display.addLine(clientString);
      }
      _display.display();
  }
}

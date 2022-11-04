package prr.app.lookup;

import prr.core.Network;
import prr.core.Client;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;


/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

  DoShowClientsWithDebts(Network receiver) {
    super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<Client> clientsWithDebts = _receiver.getClientsWithDebts();
    for(Client client : clientsWithDebts){
      _display.popup(client.toString());
    }
  }
}

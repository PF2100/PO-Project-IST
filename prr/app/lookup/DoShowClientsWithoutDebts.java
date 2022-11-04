package prr.app.lookup;

import prr.core.Network;
import prr.core.Client;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;
//FIXME more imports if needed

/**
 * Show clients with positive balance.
 */
class DoShowClientsWithoutDebts extends Command<Network> {

  DoShowClientsWithoutDebts(Network receiver) {
    super(Label.SHOW_CLIENTS_WITHOUT_DEBTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<Client> clientsWithoutDebts = _receiver.getClientsWithoutDebts();
    for(Client client : clientsWithoutDebts){
      _display.popup(client.toString());
    }
  }
}

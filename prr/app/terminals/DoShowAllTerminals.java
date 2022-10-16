package prr.app.terminals;

import java.util.Collections;
import java.util.Map;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

  DoShowAllTerminals(Network receiver) {
    super(Label.SHOW_ALL_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    Map<String,Terminal> terminals = _receiver.getTerminals();
    for(String terminalID: terminals.keySet()) {
      _display.addLine(terminals.get(terminalID));
    }
    _display.display();
  }
}

package prr.app.terminals;

import java.util.Collections;
import java.util.Map;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.*;

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
    List<String> terminals = _receiver.showTerminals();
    for (String terminalString : terminals ){
      _display.addLine(terminalString);
    }
    _display.display();
  }
}

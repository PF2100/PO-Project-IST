package prr.app.terminals;

import java.util.Collections;
import java.util.Map;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.*;

/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

  DoShowAllTerminals(Network receiver) {
    super(Label.SHOW_ALL_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<Terminal> terminals = _receiver.getTerminals();
    for (Terminal terminal : terminals ){
      _display.addLine(terminal.toString());
    }
    _display.display();
  }
}

package prr.app.terminals;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.terminals.Message;
import java.util.Map;

//FIXME add mode import if needed

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    addStringField("terminal",Message.terminalKey());
  }

  @Override
  protected final void execute() throws CommandException { //Adicionar exceção denão existir este terminal
    String terminalNumber = stringField("terminal");
    Map<String,Terminal> terminals = _receiver.getTerminals();
    Terminal terminal = terminals.get(terminalNumber);
    (new prr.app.terminal.Menu(_receiver,terminal)).open();
  }
}

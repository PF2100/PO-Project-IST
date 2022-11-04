package prr.app.terminal;

import javax.sound.midi.Receiver;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Terminal terminal) {
    super(Label.MUTE_TERMINAL, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    if(!_receiver.setOnSilent()) {
      _display.popup(Message.alreadySilent());
    }
  }
}

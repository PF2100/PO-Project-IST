package prr.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import prr.core.NetworkManager;
import prr.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {

  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }
  
  @Override
  protected final void execute()  {
    //Form f = new Form();
    //f.addStringField("filename", Message.newSaveAs());
    try {
      try{
        _receiver.save();
      }catch(MissingFileAssociationException mfae) {
        try {
          String filename  = Form.requestString(Message.newSaveAs());
          _receiver.saveAs(filename);

        }catch (MissingFileAssociationException e) {
          e.printStackTrace();
        }
      }
    }catch(FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    }catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
}


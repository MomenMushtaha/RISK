package Logic;


import javax.swing.*;
import java.io.OutputStream;


/**
 * Outputer Class for showing the text on the messagePanel in BoardView
 *
 * @author Momin Mushtaha
 * @version 1
 */
public class Outputer extends OutputStream {
  private final JTextArea text;
  private final StringBuilder string = new StringBuilder();


  /**
   * constructor for Outputer
   *
   * @param text is the text area to be written at
   */
  public Outputer(JTextArea text) {
    this.text = text;
  }


  /**
   * writes the print texts on a panel
   *
   * @param line used for the if statement
   */
  public void write(int line) {
    if (line == '\n') {
      final String texted = string.toString();
      SwingUtilities.invokeLater(() -> text.append(texted));
      string.setLength(0);
    }
    string.append((char) line);
  }


}

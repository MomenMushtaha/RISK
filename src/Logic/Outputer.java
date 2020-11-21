package Logic;

import java.io.OutputStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Outputer extends OutputStream {

  private final JTextArea textArea;
  private final StringBuilder stringBuilder = new StringBuilder();

  public Outputer(JTextArea textArea) {
    this.textArea = textArea;
  }



  public void write(int b) {

    if (b == '\n') {
      final String text = stringBuilder.toString();

      SwingUtilities.invokeLater(() -> textArea.append(text));

      stringBuilder.setLength(0);
    }

    stringBuilder.append((char) b);
  }
}

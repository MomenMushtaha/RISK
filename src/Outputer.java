import java.io.OutputStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Outputer extends OutputStream {

  private JTextArea textArea;
  private StringBuilder stringBuilder = new StringBuilder();

  public Outputer(JTextArea textArea) {
    this.textArea = textArea;
  }



  public void write(int b) {

    if (b == '\n') {
      final String text = stringBuilder.toString();

      SwingUtilities.invokeLater(new Runnable() {
        public void run()
        {
          textArea.append(text);
        }
      });

      stringBuilder.setLength(0);
    }

    stringBuilder.append((char) b);
  }
}

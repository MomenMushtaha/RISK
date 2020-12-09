package Logic;
import javax.swing.*;
import java.io.*;

public class Serialization {


    public ObjectOutputStream save(JFileChooser fileChooser) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
        return objectWriter;
    }

    public ObjectInputStream load(JFileChooser fileChooser) throws IOException, ClassNotFoundException {
        ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
        return objectReader;
    }
}

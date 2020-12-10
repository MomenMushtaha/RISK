package Control;

import Logic.Gameplay;
import Logic.Serialization;
import View.MapView;
import View.BoardView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;


public class MapControl implements ActionListener {

    public final Gameplay game;
    private final MapView view;
    public Serialization serialization;

    //Constructor
    public MapControl(Gameplay game, MapView view) {
        this.view = view;
        this.game = game;
        serialization = new Serialization();
        System.out.println("Map Panel");
        view.mapViewActionListeners(this);
        //Add this class' actionListener to MapView's buttons
    }

    //MapView's controller
    public void actionPerformed(ActionEvent evt) {
        String actionEvent = evt.getActionCommand();
        if (actionEvent.equals("useOriginalBtn")) {
            System.out.println("Initializing game");
            view.setVisible(false);
            game.setBoard(new File("Continents.xml"));
            game.startGame();
            new BoardViewControl(new BoardView(game), game);
            System.out.println("Loading BoardViewControl...");
            }
        else if (actionEvent.equals("uploadBtn")) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            //try (FileInputStream fileInput = new FileInputStream(fileChooser.getSelectedFile()) {
                game.setBoard(fileChooser.getSelectedFile());
                game.startGame();
                System.out.println("loading xml map file is done successfully");
                view.setVisible(false);
                new BoardViewControl(new BoardView(game),game);
                System.out.println("Loading BoardViewControl...");
            }
        }
        }
    }

package Control;

import Logic.Gameplay;
import Logic.Serialization;
import View.MapView;
import View.BoardView;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.xml", "xml"));
            if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                game.setBoard(fileChooser.getSelectedFile());
                view.setVisible(false);
                if (game.board.valid ==false)
                {
                    System.out.println("Please choose a valid map");
                    new MapControl(game,new MapView());
                }
                else{
                game.startGame();
                System.out.println("loading xml map file is done successfully");
                new BoardViewControl(new BoardView(game),game);
                System.out.println("Loading BoardViewControl...");
            }}
        }
        }
    }

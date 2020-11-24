package Control;

import Logic.Gameplay;
import Objects.Territory;
import View.DeployView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class DeployControl implements ActionListener {

  public final Gameplay game;
  public final DeployView view;

  //Constructor
  public DeployControl(Gameplay game, DeployView view) {
    this.game = game;
    System.out.println("Deploy Panel");
    this.view = view;
    Random t = new Random();
    if (game.getCurrentPlayer().getIsAI()) {
      view.setVisible(false);

    } else {
      //Add this class' actionListener to DeployView's buttons
      view.DeployViewActionListeners(this);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    if (actionEvent.equals("Deploy Now!")) {
      if (view.getplayerterritoryIndex() != -1) {
        int q = view.getplayerterritoryIndex();
        String selected = Objects.requireNonNull(view.troopsComboBox.getSelectedItem()).toString();
        int adding = Integer.parseInt(selected);
        Territory deployedInto = game.getCurrentPlayer().getTerritories().get(q);
        game.deploy(deployedInto, adding);
        game.getCurrentPlayer().getTerritories().get(q).addTroops(adding);
        System.out.println("Deploying");
        game.updateTroopsNewTurn(adding);
        view.setVisible(false);
        if (game.getTroopsNewTurn() != 0) {
          new DeployControl(game, new DeployView(game));
        } else {
          System.out.println("no more to deploy");
        }
      } else {
        System.out.println("no territoy was chosen");
        view.setVisible(false);
        new DeployControl(game, new DeployView(game));
      }
    }
  }


}
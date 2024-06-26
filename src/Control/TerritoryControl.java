package Control;

import Logic.Gameplay;
import View.AttackView;
import View.FortifyView;
import View.TerritoryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerritoryControl implements ActionListener {

  public final Gameplay game;
  public final TerritoryView view;
  public final String passed;

  //Constructor
  public TerritoryControl(Gameplay game, TerritoryView view, String passed) {
    this.passed = passed;
    this.game = game;
    System.out.println("Risk!");
    this.view = view;
    //Add this class' actionListener to TerritoryView's buttons
    view.TerritoryViewActionListeners(this);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    if (actionEvent.equals("Proceed")) {
      int a = view.getplayerterritoryIndex();
      int b = game.getCurrentPlayer().getTerritories().get(a).getTroops();
      if (a != -1) {
        if (b > 1) {
          view.setVisible(false);
          if (passed.equals("fortifyBtn")) {
            new FortifyControl(game, new FortifyView(game, b, a), b, a);
          } else if (passed.equals("attackBtn")) {
            new AttackControl(game, new AttackView(game, b, a), b, a);
          }
        } else {
          System.out.println("you dont have enough troops to attack");
          System.out.println("Try a different territory");
          view.setVisible(false);
          new TerritoryControl(game, new TerritoryView(game), passed);
        }
      } else {
        System.out.println("no territoy was chosen");
        view.setVisible(false);
        new TerritoryControl(game, new TerritoryView(game), passed);
      }
    } else {
      System.out.println("not working");
    }
  }
}
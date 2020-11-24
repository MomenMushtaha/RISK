package Control;

import Logic.Gameplay;
import Objects.Territory;
import View.FortifyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FortifyControl implements ActionListener {
  public final Gameplay game;
  public final FortifyView view;
  public final int troops;
  public final int fortifyIndex;

  //Constructor
  public FortifyControl(Gameplay game, FortifyView view, int troops, int fortifyingIndex) {
    this.fortifyIndex = fortifyingIndex;
    this.game = game;
    System.out.println("Risk!");
    this.view = view;
    this.troops = troops;
    //Add this class' actionListener to FortifyView's buttons
    view.FortifyViewActionListeners(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    if (actionEvent.equals("Fortify!")) {
      int f = view.getPathTerritoryIndex();
      if (f != -1) {
        int x = view.getFortifyingIndex();
        Territory fortifying = game.getCurrentPlayer().getTerritories().get(x);
        Territory target = game.getPathListingAtIndex(f);
        String selected = Objects.requireNonNull(view.troopsComboBox.getSelectedItem()).toString();
        game.fortify(fortifying, target, Integer.parseInt(selected));
        view.setVisible(false);
      }
    } else {
      System.out.println("no territoy was chosen");
      view.setVisible(false);
      new FortifyControl(game, new FortifyView(game, troops, fortifyIndex), troops, fortifyIndex);
    }
  }
}

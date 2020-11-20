import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FortifyControl implements ActionListener {
  public Gameplay game;
  public FortifyView view;
  public int troops;
  public int fortifyIndex;

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
        Territory fortifying  = game.getcurrentPlayer().getTerritories().get(x);
        Territory target = game.pathListing.get(f);
        String selected = Objects.requireNonNull(view.troopsComboBox.getSelectedItem()).toString();
        game.fortify(fortifying,target, Integer.parseInt(selected));
        view.setVisible(false);
      }
      } else {
        System.out.println("no territoy was chosen");
        view.setVisible(false);
        new FortifyControl(game, new FortifyView(game, troops, fortifyIndex ),troops, fortifyIndex);
      }}}

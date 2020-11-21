package Control;
import View.*;
import Objects.*;
import Logic.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AttackControl implements ActionListener {

  public Gameplay game;
  public AttackView view;
  public int troops;
  public int attackingIndex;

  //Constructor
  public AttackControl(Gameplay game, AttackView view, int troops, int attackingIndex) {
    this.attackingIndex = attackingIndex;
    this.game = game;
    System.out.println("Risk!");
    this.view = view;
    this.troops = troops;
    //Add this class' actionListener to AttackView's buttons
    view.AttackViewActionListeners(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    if (actionEvent.equals("Attack Now!")) {
      int f = view.getBoarderingTerritoryIndex();
      if (f != -1) {
        int x = view.getAttackingIndex();
        Territory attacking  = game.getCurrentPlayer().getTerritories().get(x);
        String selected = Objects.requireNonNull(view.troopsComboBox.getSelectedItem()).toString();
        Territory target = game.getBorderTerritories().get(f);
          System.out.println("Attacking");
        view.setVisible(false);
        game.attack(target, attacking, Integer.parseInt(selected));
        if(target.getPlayer() == game.getCurrentPlayer()) {
          if(attacking.getTroops() > 3) {
            new MoveAfterControl(game, new MoveAfterView(game, attacking.getTroops()), attacking.getTroops(), target, attacking);
          }
        }
        else {
          new AttackControl(game, new AttackView(game, troops, attackingIndex ),troops, attackingIndex);
        }
      } else {
        System.out.println("no territoy was chosen" );
        view.setVisible(false);
        new AttackControl(game, new AttackView(game, troops, attackingIndex ),troops, attackingIndex);
      }}}}

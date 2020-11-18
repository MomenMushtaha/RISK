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
        Territory attacking  = game.getcurrentPlayer().getTerritories().get(x);
        String selected = Objects.requireNonNull(view.troopsComboBox.getSelectedItem()).toString();
        Territory target = attacking.getBorderTerritories().get(f);
        if (game.getcurrentPlayer().getTerritories().contains(target)) {
          System.out.println("you own this boardering territory, please choose another territory");
          new AttackControl(game, new AttackView(game, troops, attackingIndex ),troops, attackingIndex);
        } else {
          System.out.println("Attacking");
          try {
            game.attack(target, attacking, Integer.parseInt(selected));
          } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
          }
        }
        view.setVisible(false);
      } else {
        System.out.println("no territoy was chosen" );
        view.setVisible(false);
        new AttackControl(game, new AttackView(game, troops, attackingIndex ),troops, attackingIndex);
      }}}}

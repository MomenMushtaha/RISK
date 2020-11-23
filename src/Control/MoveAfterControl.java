package Control;
import Objects.*;
import Logic.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MoveAfterControl implements ActionListener {
  public final Gameplay game;
  public final MoveAfterView view;
  public final Territory target;
  public final Territory source;

  //Constructor
  public MoveAfterControl(Gameplay game,MoveAfterView view, int troops, Territory target, Territory source) {
    this.game = game;
    System.out.println("Move After Attack Dialog");
    this.view = view;
    this.target = target;
    this.source = source;
    //Add this class' actionListener to MoveAfterView's buttons
    view.MoveAfterViewActionListeners(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    if (actionEvent.equals("Move Troops!")) {
        String selected = Objects.requireNonNull(view.troopsComboBox.getSelectedItem()).toString();
        game.MoveAfterAttack(target, source, Integer.parseInt(selected));
        view.setVisible(false);
      }
    }
}

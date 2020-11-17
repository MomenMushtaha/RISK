import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerritoryControl implements ActionListener {

  public Gameplay game;
  public TerritoryView view;

  //Constructor
  public TerritoryControl(Gameplay game,TerritoryView view) {
    this.game = game;
    System.out.println("Risk!");
    this.view = view;
    //Add this class' actionListener to TerritoryView's buttons
    view.TerritoryViewActionListeners(this::actionPerformed);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    if (actionEvent.equals("Proceed to choose where to attack")) {
      int a = view.getplayerterritoryIndex();
      int b = game.getcurrentPlayer().getTerritories().get(a).getTroops();
      if (a != -1) {
        if (b > 1) {
          view.setVisible(false);
          new AttackControl(game, new AttackView(game,b,a),b,a);
        }
        else{
          System.out.println("you dont have enough troops to attack");
          System.out.println("Try a different territory");
          view.setVisible(false);
          new TerritoryControl(game, new TerritoryView(game));
        }
      }
      else {
        System.out.println("no territoy was chosen" );
        view.setVisible(false);
        new TerritoryControl(game, new TerritoryView(game));
      }}
  else {
  System.out.println("not working");}
  }}
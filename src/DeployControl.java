import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DeployControl implements ActionListener {

  public Gameplay game;
  public DeployView view;
  public int troops;
  //Constructor
  public DeployControl(Gameplay game, DeployView view, int troops) {
    this.game = game;
    System.out.println("Risk!");
    this.view = view;
    this.troops = troops;
    //Add this class' actionListener to DeployView's buttons
    view.DeployViewActionListeners(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    if (actionEvent.equals("Deploy Now!")) {
      if (view.getplayerterritoryIndex() != -1) {
        int q = view.getplayerterritoryIndex();
        String selected = Objects.requireNonNull(view.troopsComboBox.getSelectedItem()).toString();
        int adding = Integer.parseInt(selected);
        game.getcurrentPlayer().getTerritories().get(q).addTroops(adding);
        System.out.println("Deploying");
        troops -= adding;
        view.setVisible(false);
        if (troops != 0) {
          new DeployControl(game, new DeployView(game, troops), troops);
        }
        else{
          System.out.println("no more to deploy");
        }
      }
    else {
    System.out.println("no territoy was chosen" );
        view.setVisible(false);
        new DeployControl(game, new DeployView(game, troops), troops);
      }}}}
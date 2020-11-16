import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeployControl implements MouseListener {

  public Gameplay game;
  public DeployView view;
  int troops;

  //Constructor
  public DeployControl(Gameplay game, DeployView view, int troops) {
    this.game = game;
    System.out.println("Risk!");
    this.view = view;
    this.troops = troops;
    //Add this class' actionListener to riskView's buttons
    view.DeployViewMouseListeners(this);
  }


  @Override
  public void mouseClicked(MouseEvent e) {
    if(e.getClickCount()==2){
      JList list = (JList)e.getSource();
      System.out.println( list.getSelectedValue());
      Territory terr = (Territory) list.getSelectedValue();
      terr.addTroops(troops);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
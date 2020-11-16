import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Integer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TerritoryControl implements MouseListener {

  public Gameplay game;
  public TerritoryView view;

  //Constructor
  public TerritoryControl(TerritoryView view) {
    System.out.println("Risk!");
    this.view = view;
    //Add this class' actionListener to riskView's buttons
    view.TerritoryViewMouseListeners(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
          JList list = (JList)e.getSource();
          System.out.println( list.getSelectedValue());
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

  }}
package Control;
import Objects.*;
import Logic.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TradeControl implements ActionListener {
  public Gameplay game;
  public TradeView view;
  public String[] options;

  //Constructor
  public TradeControl(Gameplay game, TradeView view, String[] options) {
    this.game = game;
    System.out.println("Trade Panel!");
    this.view = view;
    this.options = options;
    //Add this class' actionListener to TradeView's buttons
    view.TradeViewActionListeners(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    view.tradeComboBox.getSelectedItem();
    view.setVisible(false);
    //trade()
      }
    }

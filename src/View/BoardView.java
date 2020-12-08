package View;

import Logic.Gameplay;
import Logic.Outputer;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.PrintStream;

public class BoardView extends JFrame {
  public JButton deployBtn;
  //initialize buttons
  private JButton tradeBtn;
  private JButton attackBtn;
  private JButton fortifyBtn;
  private JButton passBtn;
  private JButton saveBtn;
  private JButton quitBtn;
  private JButton helpBtn;
  private JButton printBtn;
  //initialize constraints
  private GridBagConstraints cons;

  /**
   * Constructs the Risk game board.
   **/
  public BoardView(Gameplay game) {
    setTitle("Java-Risk");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //  GridBagLayout allows a flexible sizing of components
    //initialize Layouts
    GridBagLayout gameLayout = new GridBagLayout();
    setLayout(gameLayout);
    cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.anchor = GridBagConstraints.LINE_START;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 0;
    add(PrintOutputPanel());
    cons.fill = GridBagConstraints.BOTH;
    cons.anchor = GridBagConstraints.CENTER;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 1;
    cons.gridy = 0;
    add(riskMapPanel());
    cons.fill = GridBagConstraints.BOTH;
    cons.anchor = GridBagConstraints.LINE_END;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 2;
    cons.gridy = 0;
    add(commandsPanel());
    setResizable(false);
    setVisible(true);
    pack();
    toFront();
  }


  private JPanel PrintOutputPanel() {

    //initialize panels
    JPanel printOutputPanel = new JPanel();
    printOutputPanel.setPreferredSize(new Dimension(400 / 2, 980 / 2));
    GridBagLayout printOutputLayout = new GridBagLayout();
    printOutputPanel.setLayout(printOutputLayout);
    cons = new GridBagConstraints();

    // initialize Text area
    JTextArea printTextArea = new JTextArea();
    System.setOut(new PrintStream(new Outputer(printTextArea)));
    printTextArea.setFocusable(false);
    printTextArea.setLineWrap(true);
    printTextArea.setWrapStyleWord(true);
    // initialize caret
    DefaultCaret caret = (DefaultCaret) printTextArea.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    JScrollPane printOutputScrollPane = new JScrollPane(printTextArea);
    printOutputScrollPane.setPreferredSize(new Dimension(400 / 2, 980 / 2));

    printOutputPanel.add(printOutputScrollPane);

    return printOutputPanel;
  }

  /**
   * The panel containing the scrollable Risk map image.
   **/
  private JPanel riskMapPanel() {

    JPanel riskMapPanel = new JPanel();
    riskMapPanel.setLayout(new GridLayout(1, 1, 5, 5));
    //initialize image
    ImageIcon riskImageIcon = new ImageIcon("Map.jpg");
    JLabel map = new JLabel(riskImageIcon);
    // initialize scrolling panes
    JScrollPane riskScrollPane = new JScrollPane(map);
    riskScrollPane.setPreferredSize(new Dimension(1210, 720));
    riskMapPanel.add(riskScrollPane);
    toFront();
    return riskMapPanel;
  }


  private JPanel commandsPanel() {

    JPanel commandsPanel = new JPanel();

    commandsPanel.setPreferredSize(new Dimension(200 / 2, 980 / 2));

    GridBagLayout commandsLayout = new GridBagLayout();
    commandsPanel.setLayout(commandsLayout);


    tradeBtn = new JButton("TRADE");
    attackBtn = new JButton("ATTACK");
    fortifyBtn = new JButton("FORTIFY");
    passBtn = new JButton("PASS");
    printBtn = new JButton("PRINT");
    saveBtn = new JButton("SAVE");
    quitBtn = new JButton("QUIT");
    deployBtn = new JButton("DEPLOY");
    helpBtn = new JButton("HELP");


    String tradeName = "tradeBtn";
    tradeBtn.setActionCommand(tradeName);
    String attackName = "attackBtn";
    attackBtn.setActionCommand(attackName);
    String fortifyName = "fortifyBtn";
    fortifyBtn.setActionCommand(fortifyName);
    String passName = "passBtn";
    passBtn.setActionCommand(passName);
    String printName = "printBtn";
    printBtn.setActionCommand(printName);
    String saveName = "saveBtn";
    saveBtn.setActionCommand(saveName);
    String quitName = "quitBtn";
    quitBtn.setActionCommand(quitName);
    String deployName = "deployBtn";
    deployBtn.setActionCommand(deployName);
    String helpName = "helpBtn";
    helpBtn.setActionCommand(helpName);


    cons = new GridBagConstraints();


    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 0;
    commandsPanel.add(tradeBtn, cons);


    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 1;
    commandsPanel.add(deployBtn, cons);


    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 2;
    commandsPanel.add(attackBtn, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 3;
    commandsPanel.add(fortifyBtn, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 4;
    commandsPanel.add(passBtn, cons);


    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 5;
    commandsPanel.add(saveBtn, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 6;
    commandsPanel.add(quitBtn, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 7;
    commandsPanel.add(helpBtn, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 8;
    commandsPanel.add(printBtn, cons);

    return commandsPanel;
  }


  public void BoardViewActionListeners(ActionListener evt1) {

    tradeBtn.addActionListener(evt1);
    deployBtn.addActionListener(evt1);
    attackBtn.addActionListener(evt1);
    fortifyBtn.addActionListener(evt1);
    passBtn.addActionListener(evt1);
    saveBtn.addActionListener(evt1);
    printBtn.addActionListener(evt1);
    quitBtn.addActionListener(evt1);
    helpBtn.addActionListener(evt1);
  }
}


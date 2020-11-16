import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.PrintStream;

class BoardView extends JDialog {
  private JPanel printOutputPanel;
  private JPanel riskMapPanel;
  private JPanel commandsPanel;
  private GridBagConstraints cons;
  private GridBagLayout gameLayout;
  private GridBagLayout printOutputLayout;
  private GridBagLayout commandsLayout;
  private JButton attackBtn;
  private JButton deployBtn;
  private JButton helpBtn;
  private JButton quitBtn;
  private JButton printBtn;
  private JButton fortifyBtn;
  private JButton tradeBtn;
  private JButton passBtn;
  private String attackName = "attackBtn";
  private String deployName = "deployBtn";
  private String printName = "printBtn";
  private String quitName = "quitBtn";
  private String fortifyName = "fortifyBtn";
  private String tradeName = "turnInBtn";
  private String passName = "passBtn";
  private String helpName = "helpBtn";
  private JTextArea printTextArea;
  private JScrollPane riskScrollPane;
  private JScrollPane printOutputScrollPane;
  private DefaultCaret caret;
  private ImageIcon riskImageIcon;
  public Gameplay game;

  /**
   * Constructs the Risk game board.
   **/
  public BoardView(Gameplay game) {

    //super(owner, modality);
    setTitle("Java-Risk");
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


    this.game = game;

    //  GridBagLayout allows a flexible sizing of components
    gameLayout = new GridBagLayout();
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
    System.out.println("r");
    add(riskMapPanel());

    cons.fill = GridBagConstraints.BOTH;
    cons.anchor = GridBagConstraints.LINE_END;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 2;
    cons.gridy = 0;
    add(commandsPanel());
    setVisible(true);


    setLocationRelativeTo(null);

    pack();
  }



  private JPanel PrintOutputPanel() {

    printOutputPanel = new JPanel();
    printOutputPanel.setPreferredSize(new Dimension(400/2, 980/2));
    printOutputLayout = new GridBagLayout();
    printOutputPanel.setLayout(printOutputLayout);
    cons = new GridBagConstraints();

    printTextArea = new JTextArea();
    System.setOut(new PrintStream(new Outputer(printTextArea)));
    printTextArea.setFocusable(false);
    printTextArea.setLineWrap(true);
    printTextArea.setWrapStyleWord(true);
    caret = (DefaultCaret) printTextArea.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    printOutputScrollPane = new JScrollPane(printTextArea);
    printOutputScrollPane.setPreferredSize(new Dimension(400/2,980/2));

    printOutputPanel.add(printOutputScrollPane);

    return printOutputPanel;
  }
  /**
   * The panel containing the scrollable Risk map image.
   **/
  private JPanel riskMapPanel() {

    riskMapPanel = new JPanel();
    riskMapPanel.setLayout(new GridLayout(1, 1, 5, 5));
    riskImageIcon = new ImageIcon("Map.jpg");
    JLabel map = new JLabel(riskImageIcon);
    riskScrollPane = new JScrollPane(map);
    riskScrollPane.setPreferredSize(new Dimension(1200, 720));
    riskMapPanel.add(riskScrollPane);
    toFront();
    return riskMapPanel;
  }


  private JPanel commandsPanel() {

    commandsPanel = new JPanel();

    commandsPanel.setPreferredSize(new Dimension(200/2, 980/2));

    commandsLayout = new GridBagLayout();
    commandsPanel.setLayout(commandsLayout);



    tradeBtn = new JButton("TRADE");
    attackBtn = new JButton("ATTACK");
    fortifyBtn = new JButton("FORTIFY");
    passBtn = new JButton("PASS");
    printBtn = new JButton("PRINT");
    quitBtn = new JButton("QUIT");
    deployBtn = new JButton("DEPLOY");
    helpBtn = new JButton("HELP");


    tradeBtn.setActionCommand(tradeName);
    attackBtn.setActionCommand(attackName);
    fortifyBtn.setActionCommand(fortifyName);
    passBtn.setActionCommand(passName);
    printBtn.setActionCommand(printName);
    quitBtn.setActionCommand(quitName);
    deployBtn.setActionCommand(deployName);
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
    commandsPanel.add(quitBtn, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 6;
    commandsPanel.add(helpBtn, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 7;
    commandsPanel.add(printBtn, cons);

    return commandsPanel;
  }



  public void BoardViewActionListeners(ActionListener evt1) {

    tradeBtn.addActionListener(evt1);
    deployBtn.addActionListener(evt1);
    attackBtn.addActionListener(evt1);
    fortifyBtn.addActionListener(evt1);
    passBtn.addActionListener(evt1);
    printBtn.addActionListener(evt1);
    quitBtn.addActionListener(evt1);
    helpBtn.addActionListener(evt1);
  }}


import java.util.*;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.MILLISECONDS;


/**
 * Gameplay Class contains the logic used to run RISK
 * @author Momin Mushtaha
 * @version 1
 */

public class Gameplay {
  public Board board;
  public Player currentPlayer;
  public ArrayList<Player> players;
  public static int numPlayers;
  public int troopsNewTurn;
  public ArrayList<Territory> pathListing;
  public String[] listpath;


  /**
   * Constructor for objects of class Gameplay
   */
  public Gameplay() {
    this.board = new Board();
    this.players = new ArrayList<>();
  }





  /**
   * Performs an attack command between territories t1 and t2
   *
   * @param t1        is the Target Territory
   * @param t2        is the attacking Territory
   * @param attackers The number of troops the t2 has to attack with
   */
  public void attack(Territory t1, Territory t2, int attackers) throws InterruptedException {
    threeDice dice3 = new threeDice();
    twoDice dice2 = new twoDice();
    Die die = new Die();

    int a;
    int d;
    int aa;
    int dd;
    if (!t1.getBorderTerritories().contains(t2)) {
      MILLISECONDS.sleep(300);
      System.out.println("The two territories selected don't border each other");
      //processCommand("ATTACK");
      return;
    }
    if (t2.getPlayer() != currentPlayer) {
      MILLISECONDS.sleep(300);
      System.out.println("You can't attack from that territory. You don't control it");
      //processCommand("ATTACK");
      return;
    }
    if (t1.getPlayer() == currentPlayer) {
      MILLISECONDS.sleep(300);
      System.out.println("You cannot attack your own territory");
      //processCommand("ATTACK");
      return;
    }
    if (attackers == 1) {
      MILLISECONDS.sleep(300);
      System.out.println("You can only attack with a 2 or more Soldiers");
      //processCommand("ATTACK");
      return;
    }
    if (attackers == 2) {
      MILLISECONDS.sleep(300);
      System.out.println(t2.getPlayer().getName() + " is attacking with 1 die");
      System.out.println(t1.getPlayer().getName() + " is defending with 1 die");
      a = die.roll();

      d = die.roll();
      if (a > d) {
        t1.removeTroops(1);
        MILLISECONDS.sleep(300);
        System.out.println(t1.getPlayer().getName() + " lost 1 soldier");
      } else {
        t2.removeTroops(1);
        MILLISECONDS.sleep(300);
        System.out.println(t2.getPlayer().getName() + " lost 1 soldier");
      }
    }
    if (attackers == 3) {
      MILLISECONDS.sleep(300);
      System.out.println(t2.getPlayer().getName() + " is attacking with 2 dice");
      dice2.roll();
      a = dice2.getHighest();
      aa = dice2.getSecondHighest();
      if (t1.getTroops() >= 2) {
        MILLISECONDS.sleep(300);
        System.out.println(t1.getPlayer().getName() + " is defending with 2 dice");
        dice2.roll();
        d = dice2.getHighest();
        dd = dice2.getSecondHighest();
        if (a > d && aa > dd) {
          t1.removeTroops(2);
          MILLISECONDS.sleep(300);
          System.out.println(t1.getPlayer().getName() + " lost 2 soldiers");
        } else if (a > d && aa < dd) {
          t1.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t1.getPlayer().getName() + " lost 1 soldier");
          t2.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t2.getPlayer().getName() + " lost 1 soldier");
        } else {
          t2.removeTroops(2);
          MILLISECONDS.sleep(300);
          System.out.println(t2.getPlayer().getName() + " lost 1 soldier");

        }
      } else if (t1.getTroops() == 1) {
        MILLISECONDS.sleep(300);
        System.out.println(t1.getPlayer().getName() + " is defending with 1 die");
        d = die.roll();
        if (a > d) {
          t1.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t1.getPlayer().getName() + " lost 1 soldier");
        } else {
          t2.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t2.getPlayer().getName() + " lost 1 soldier");
        }
      }
    }
    if (attackers >= 4) {
      MILLISECONDS.sleep(300);
      System.out.println(t2.getPlayer().getName() + " is attacking with 3 dice");
      dice3.roll();
      a = dice3.getHighest();
      aa = dice3.getSecondHighest();
      if (t1.getTroops() == 1) {
        MILLISECONDS.sleep(300);
        System.out.println(t1.getPlayer().getName() + " is defending with 1 die");
        d = die.roll();
        if (a > d) {
          t1.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t1.getPlayer().getName() + " lost 1 soldier");
        } else {
          t2.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t2.getPlayer().getName() + " lost 1 soldier");
        }
      } else if (t1.getTroops() == 2) {
        MILLISECONDS.sleep(300);
        System.out.println(t1.getPlayer().getName() + " is defending with 2 dice");
        dice2.roll();
        d = dice2.getHighest();
        dd = dice2.getSecondHighest();
        if (a > d && aa > dd) {
          t1.removeTroops(2);
          MILLISECONDS.sleep(300);
          System.out.println(t1.getPlayer().getName() + " lost 2 soldiers");
        } else if (a > d && aa < dd) {
          t1.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t1.getPlayer().getName() + " lost 1 soldier");
          t2.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t2.getPlayer().getName() + " lost 1 soldier");
        } else {
          t2.removeTroops(2);
          MILLISECONDS.sleep(300);
          System.out.println(t2.getPlayer().getName() + " lost 2 soldiers");
        }
      } else {
        MILLISECONDS.sleep(300);
        System.out.println(t1.getPlayer().getName() + " is defending with 3 dice");
        dice3.roll();
        d = dice3.getHighest();
        dd = dice3.getSecondHighest();
        if (a > d && aa > dd) {
          t1.removeTroops(3);
          MILLISECONDS.sleep(300);
          System.out.println(t1.getPlayer().getName() + " lost 3 soldiers");
        } else if (a > d && aa < dd) {
          t1.removeTroops(1);
          MILLISECONDS.sleep(300);
          System.out.println(t1.getPlayer().getName() + " lost 1 soldier");
          t2.removeTroops(2);
          MILLISECONDS.sleep(300);
          System.out.println(t2.getPlayer().getName() + " lost 2 soldiers");
        } else {
          t2.removeTroops(3);
          MILLISECONDS.sleep(300);
          System.out.println(t2.getPlayer().getName() + " lost 3 soldiers");
        }
      }
    }
    attackResult(t1, t2);
  }

  /**
   * Shows the results of the attack command performed and indicates what happens next
   *
   * @param t  is the defending Territory
   * @param tt is the attacking Territory
   */
  public void attackResult(Territory t, Territory tt) throws InterruptedException {
    if (tt.getTroops() == 1) {
      MILLISECONDS.sleep(300);
      System.out.println("cant attack daug common have some sense, you got nobody there");
    } else if (t.getTroops() <= 0) {
      if (t.getTroops() < 0) {
        int k = t.getTroops();
        t.addTroops(-k);
      }
      //defending territory has no troops left to defend with
      MILLISECONDS.sleep(300);
      System.out.println("defending territory has no troops left");
      MILLISECONDS.sleep(300);
      System.out.println(t.getName() + " was conquered!");
      Player OldOwner = t.getPlayer();
      t.getPlayer().removeTerritories(t);
      tt.getPlayer().addTerritories(t);
      t.changeOwner(tt.getPlayer());
      continent_check();
      if (OldOwner.getTerritories().size() == 0) {
        //eliminated if it was the defenders last territory
        removePlayer(OldOwner);
      }
      if (players.size() == 1) {
        //Declares if we have a winner
        MILLISECONDS.sleep(300);
        System.out.println(currentPlayer.getName() + " has won");
      }
      if (tt.getTroops() > 3) {
        MoveAfterAttack(t, tt);
      } else if (tt.getTroops() == 3) {
        t.addTroops(2);
        tt.removeTroops(2);
        MILLISECONDS.sleep(300);
        System.out.println("Two Troops left to " + t.getName() + " and one remaining in " + tt.getName());
      } else if (tt.getTroops() == 2) {
        MILLISECONDS.sleep(300);
        t.addTroops(1);
        tt.removeTroops(1);
        System.out.println("One Troop left to " + t.getName() + " and one remaining in " + tt.getName());
      }
    } else {
      attack(mapper(t.getName()), mapper(tt.getName()), tt.getTroops());
    }
  }


  public void MoveAfterAttack(Territory td, Territory ta) throws InterruptedException {
    //how many troops to move to Territory t
    Scanner s = new Scanner(System.in);
    MILLISECONDS.sleep(300);
    System.out.println("How many troops would you like to move?");
    int TroopsToMove = s.nextInt();
    TimeUnit.SECONDS.sleep(1);
    if (TroopsToMove >= 3) {
      if (TroopsToMove < ta.getTroops()) {
        MILLISECONDS.sleep(300);
        System.out.println(TroopsToMove + " added to " + td.getName() + " from " + ta.getName());
        td.addTroops(TroopsToMove);
        ta.removeTroops(TroopsToMove);
      } else {
        MILLISECONDS.sleep(300);
        System.out.println("You dont have that many Troops");
        MoveAfterAttack(td, ta);
        //repeat loop
      }
    } else {
      System.out.println("You Cant move less than 3 troops");
      MoveAfterAttack(td, ta);
    }
  }


  /**
   * Fortifies the troops when the command is passed
   */
  public void fortify(Territory t1, Territory t2, int tr){
    t1.removeTroops(tr);
    t2.addTroops(tr);
  }
  /**
   * Method to find if there a path between two countries or not
   *
   * @param source: The country which armies are being moved from
   * @param destination: The country which armies are being moved to
   * @return Returns true if there is a path to move the armies between countries
   */
  public boolean hasPathBFS2(Territory source, Territory destination) {
    LinkedList<Territory> nexttovisit = new LinkedList<Territory>();
    HashSet<String> visited = new HashSet<String>();
    nexttovisit.add(source);
    while (!nexttovisit.isEmpty()) {
      Territory node = nexttovisit.remove();
      if (node.getName().equals(destination.getName())) {
        return true;
      }
      if (visited.contains(node.getName()))
        continue;
      visited.add(node.getName());
      for (Territory child : node.getBorderTerritories()) {
        if (child.getPlayer() == node.getPlayer()) {
          nexttovisit.add(child);
        }
      }
    }
    return false;
  }
public Player getcurrentPlayer()
{
  return currentPlayer;
}

  /**
   * Deploy troops at the beginning of each Player's turn
   *
   * @param newTroops an Integer of the number of troops to be deployed on the Territory specified in the method
   */
  /*private void DeployTroops(int newTroops) throws InterruptedException {
    new DeployControl(new DeployView(currentPlayer,this), newTroops);
    listTheTerritories(currentPlayer.getTerritories());
    listTheContinents(currentPlayer.getContinents());
    MILLISECONDS.sleep(300);
    System.out.println("add Troops to:");
    String addingToTerritories;
    addingToTerritories = "d";
    int g = stringTerritoryMapping(addingToTerritories, newTroops);
    while (g == -1) {
      MILLISECONDS.sleep(300);
      System.out.println("You have entered an invalid territory");
      MILLISECONDS.sleep(300);
      System.out.println("Enter a territory to add troops to");
      addingToTerritories = "d";
      g = stringTerritoryMapping(addingToTerritories, newTroops);
    }
    MILLISECONDS.sleep(300);
    System.out.println(newTroops + " added to " + addingToTerritories + " territory");
    System.out.println();
    //board.getTer(g).addTroops(1);
  }*/


  /**
   * Gets a Player for players ArrayList
   *
   * @param index the index of the Player in players
   * @return the Player indicated
   */
  public Player getPlayers(int index) {
    return players.get(index);
  }


  /**
   * Removes a Player from the game
   *
   * @param player the Player to be removed
   */
  public void removePlayer(Player player) {
    System.out.println(player.getName() + " is removed");
    players.remove(player);
  }


  /**
   * Initializes the players to the game
   *
   * @param number an integer of the number of players to be initialized
   */
  public void InitializePlayers(int number){
    for (int z = 0; z < number; z++) {
      players.add(new Player("Player " + (z + 1)));
    }
  }

  /**
   * Initializes troops to territories at the start of the game
   *
   * @param p is the numPlayers Initialized the game
   */
  public void addInitialTerritories(int p) {
    int Num = 0;
    for (int n = 0; n <= (board.getTerritoriesList().length - 1); n++) {
      getPlayers(Num).addTerritories(board.getTerritoriesList()[n]);
      board.getTerritoriesList()[n].changeOwner(players.get(Num));
      Num = (Num + 1) % p;
    }
  }

  /**
   * Sets the amount of initial troops each player can start out with depending on number of players
   */
  public void NumberInitialTroops() {
    //The number of players ranges from 2 to 6, and the corresponding initial number of armies
    //is 50, 35, 30, 25, and 20 respectively, depending on the number of players
    //2 : 50 troops each
    //3: 35 troops each
    //4: 30 troops
    //5: 25
    //6: 20
    int m;
    numPlayers = players.size();
    for (int pl = 0; pl < numPlayers; pl++ )
    if (numPlayers == 2) {
        for (m = 0; m < 13; m++) {
          players.get(pl).getTerritories().get(m).addTroops(2);
        }
        for (m = 13; m < 21; m++) {
          players.get(pl).getTerritories().get(m).addTroops(3);
        }
    } else if (numPlayers == 3) {
      for (m = 0; m < 7; m++) {
        players.get(pl).getTerritories().get(m).addTroops(2);
      }
      for (m = 7; m < 14; m++) {
        players.get(pl).getTerritories().get(m).addTroops(3);
      }
    } else if (numPlayers == 4) {
      if(players.get(pl).getTerritories().size() < 11) {
        for (m = 0; m < 3; m++) {
          players.get(pl).getTerritories().get(m).addTroops(4);
        }
        for (m = 3; m < 7; m++) {
          players.get(pl).getTerritories().get(m).addTroops(3);
        }
        players.get(pl).getTerritories().get(7).addTroops(2);
        players.get(pl).getTerritories().get(8).addTroops(2);
        players.get(pl).getTerritories().get(9).addTroops(2);
      }
      else if(players.get(pl).getTerritories().size() == 11){
        for (m = 0; m < 2; m++) {
          players.get(pl).getTerritories().get(m).addTroops(4);
        }
        for (m = 2; m < 6; m++) {
          players.get(pl).getTerritories().get(m).addTroops(3);
        }
        for (m = 6; m < 11; m++) {
          players.get(pl).getTerritories().get(m).addTroops(2);
        }
      }
    } else if (numPlayers == 5) {
      if(players.get(pl).getTerritories().size() < 9) {
        for (m = 0; m < 4; m++) {
          players.get(pl).getTerritories().get(m).addTroops(4);
        }
        for (m = 4; m < 7; m++) {
          players.get(pl).getTerritories().get(m).addTroops(2);
        }
        players.get(pl).getTerritories().get(7).addTroops(3);
      }
    else if(players.get(pl).getTerritories().size() == 9){
        for (m = 0; m < 3; m++) {
          players.get(pl).getTerritories().get(m).addTroops(4);
        }
        for (m = 3; m < 6; m++) {
          players.get(pl).getTerritories().get(m).addTroops(3);
        }
        for (m = 6; m < 8; m++) {
          players.get(pl).getTerritories().get(m).addTroops(2);
        }
      }}
    else if (numPlayers == 6) {
      for (m = 0; m < 6; m++) {
        getPlayers(pl).getTerritories().get(m).addTroops(3);
      }
      getPlayers(pl).getTerritories().get(6).addTroops(2);
    }}


  /**
   * Adds the troops to the territory
   *
   * @param t     is the string indicating the Territory name
   * @param troop is the amount of troops to be added to that Territory
   * @return 0 if the troops are added successfully, otherwise it returns -1;
   */
  public int stringTerritoryMapping(String t, int troop) {
    Territory addT = mapper(t);
    if (addT != null) {
      if (addT.getPlayer() == currentPlayer) {

        addT.addTroops(troop);
        return 0;
      } else {
        System.out.println("the territory does not belong to the player");
        return -1;
      }
    }
    return -1;
  }

  /**
   * looks for the territory using its String name
   *
   * @param confirm is the territory name that wants to be confirmed that it exist in the list of territories
   * @return the Territory asked for, otherwise it returns null
   */
  public Territory mapper(String confirm) {
    for (Territory terr : board.getTerritoriesList())
      if (terr.getName().equalsIgnoreCase(confirm)) {
        return terr;
      }
    return null;
  }

  /**
   * Prints the continents a player owns
   *
   * @param continentList a list of continents
   */
  private void printTheContinents(ArrayList<Continent> continentList) {
    System.out.println();

    if (continentList == null) {
      System.out.println("No Continents Owned");
    } else {
      for (Continent cont : continentList) {
        System.out.println("Continent");
        System.out.println(cont.getName());
      }
    }
  }
  /**
   * Prints the Territories a player owns
   *
   * @param territoryList a list of Territories
   */
  public void printTheTerritories(ArrayList<Territory> territoryList) {
    String[] territoryListing =new String[territoryList.size()];
    int t = 0;
    for (Territory terr : territoryList) {
      System.out.println(territoryListing[t] = (terr.getName() + ": Troops = " + terr.getTroops()));
      t++;
    }
  }


  /**
   * lists the Territories a player owns
   *
   * @param territoryList a list of Territories
   */
  public String[] listTheTerritories(ArrayList<Territory> territoryList) {
    String[] territoryListing =new String[territoryList.size()];
    int t = 0;
      for (Territory terr : territoryList) {
        territoryListing[t] = (terr.getName() + ": Troops = " + terr.getTroops());
        t++;
      }
    return territoryListing;
  }

  public String[] listBorderingTerritories(Territory terr) {
    String[] boarderingListing = new String[terr.getBorderTerritories().size()];
    int add = 0;
    for (int b = 0; b < terr.getBorderTerritories().size(); b++) {
      Territory bordering = terr.getBorderTerritories().get(b);
      if (!currentPlayer.getTerritories().contains(bordering)) {
        boarderingListing[add] = bordering.getName() + ": Troops = " + bordering.getTroops();
        add++;
      }
    }
    return boarderingListing;
  }

  public String[] listPathTerritories(Territory sor) {
    pathListing = new ArrayList<>();
    for (Territory terr : board.getTerritoriesList()) {
      if (hasPathBFS2(sor, terr)) {
        pathListing.add(terr);
      }
    }
    listpath = new String[pathListing.size()];
    for (int p = 0 ; p < listpath.length; p++)
    {
      listpath[p] = (pathListing.get(p).getName() + ": Troops = " + pathListing.get(p).getTroops());
    }
    return listpath;
  }

  public ArrayList<Territory> getPathListing()
  {
    return pathListing;
  }


  /**
   * lists the continents a player owns
   *
   * @param continentList a list of continents
   */
  private void listTheContinents(ArrayList<Continent> continentList) {
    System.out.println();

    if (continentList == null) {
      System.out.println("No Continents Owned");
    } else {
      for (Continent cont : continentList) {
        System.out.println("Continent");
        System.out.println(cont.getName());
      }
    }
  }


  /**
   * prints the status of the game
   */
  public void getGameStatus() {
    for (int g = 0; g < players.size(); g++) {
      System.out.println(getPlayers(g).getName() + " Territories and Continents");
      printTheTerritories(getPlayers(g).getTerritories());
      printTheContinents(getPlayers(g).getContinents());
    }

  }

  /**
   * Prints the commands possible for the game
   */
  public void printCommands() {
    System.out.println("""
      THESE ARE THE POSSIBLE COMMANDS:
      ATTACK: ATTACKS A COUNTRY
      PASS: PASSES YOUR TURN
      PRINT: PRINTS THE STATE OF THE MAP
      QUIT: QUITS THE GAME SESSION
      HELP: SHOWS HELP
      FORTIFY: MOVE TROOPS BETWEEN YOUR TERRITORIES (ONLY ONE MOVE IS ALLOWED)\s
      """);
  }

  /**
   * Prints the rules of the game
   */
  public void printRules() {
    System.out.println
      ("""
        Rules\s
        1. The winner is the first player to eliminate every opponent by capturing all 42 territories on the board.
        2. You can only attack a country that is adjacent to a country you control.
        3. At the start of each turn you will receive at least 3 armies or the # of territories you own divided by 3 (which ever one is higher).
        4. You can only attack a country if you own at least 2 armies in the attacking country.
        5. When attacking the person who is attacking can choose to roll up to 3 dice.
        6. The person defending can roll up to 3 dice if the have more that 3 troops, 2 if they have at least 2 troops in the defending country (if not they can only roll one dice).
        7. When you capture a territory, you must move at least as many armies as dice you rolled in your last attack.
                                         \s"""
      );
  }

  /**
   * Signals that currentPlayer wants to quit the game
   */
  public void quit(){
    System.out.println(currentPlayer.getName() + " has left the game");
    removePlayer(currentPlayer);
    if ((players.size() == 1)) {
      System.out.println(getPlayers(0).getName() + " has won the game");
      System.exit(0);
    }
  }

  /**
   * Prints welcoming phrases at the beginning of each ga,e
   */
  void printWelcome(){
    System.out.println();
    System.out.println("Welcome to Risk!");
    System.out.println("Everybody wants to rule the world!");
    System.out.println("Type 'help' if you need help.");
    System.out.println("At the start of each turn each player receives 3 or more troops and" +
      " if you rule a whole continent you will get more bonus troops.");
    System.out.println("The game will start with player 1");
    System.out.println();
    currentPlayer = getPlayers(0);
  }


  /**
   * Prints Help then followed by the commands possible for the game
   */
  public void printHelp(){
    {
      System.out.println("ARE YOU LOST?");
      System.out.println("I CAN HELP YOU");
      System.out.println();
      printCommands();
    }
  }

  /**
   * Signals that currentPlayer wants to pass the turn
   *
   */
  public void nextPlayerTurn() {
    System.out.println(getcurrentPlayer().getName() + " passes");
    if (players.size() == 6)
    {
      if (currentPlayer == getPlayers(5))
      {currentPlayer = getPlayers(0);}
      else if (currentPlayer == getPlayers(4))
      {currentPlayer = getPlayers(5);}
      else if (currentPlayer == getPlayers(3))
    {currentPlayer = getPlayers(4);}
      else if (currentPlayer == getPlayers(2))
    {currentPlayer = getPlayers(3);}
     else if (currentPlayer == getPlayers(1))
    {currentPlayer = getPlayers(2);}
     else if (currentPlayer == getPlayers(0))
    {currentPlayer = getPlayers(1);}
    }
    else if (players.size() == 5)
    {
      if (currentPlayer == getPlayers(4))
    {currentPlayer = getPlayers(0);}
    else if (currentPlayer == getPlayers(3))
    {currentPlayer = getPlayers(4);}
    else if (currentPlayer == getPlayers(2))
    {currentPlayer = getPlayers(3);}
    else if (currentPlayer == getPlayers(1))
    {currentPlayer = getPlayers(2);}
    else if (currentPlayer == getPlayers(0))
    {currentPlayer = getPlayers(1);}
    }
    else if(players.size() == 4)
    {
      if (currentPlayer == getPlayers(3))
    {currentPlayer = getPlayers(0);}
    else if (currentPlayer == getPlayers(2))
    {currentPlayer = getPlayers(3);}
    else if (currentPlayer == getPlayers(1))
    {currentPlayer = getPlayers(2);}
    else if (currentPlayer == getPlayers(0))
    {currentPlayer = getPlayers(1);}
    }
    else if(players.size() == 3)
    {
      if (currentPlayer == getPlayers(2))
      {currentPlayer = getPlayers(0);}
      else if (currentPlayer == getPlayers(1))
      {currentPlayer = getPlayers(2);}
      else if (currentPlayer == getPlayers(0))
      {currentPlayer = getPlayers(1);}
    }
    else if(players.size() == 2)
    {
      if (currentPlayer == getPlayers(1))
    {currentPlayer = getPlayers(0);}
    else if (currentPlayer == getPlayers(0))
    {currentPlayer = getPlayers(1);}}
    else
    {
      System.out.println(currentPlayer.getName() + " has won");
    }
    System.out.println("NEXTTTTT!!");
    troopsNewTurn = get_bonus(currentPlayer);
    //if pass is entered cycle to the next player
  }



  public int get_bonus(Player p)
  {
    int troopsNewTurn = 0;
    System.out.println("It is " +p.getName() + " turn");
    int bonus = 0;
    if (p.getContinents().size() > 0) {
      for (int j = 0; j < p.getContinents().size(); j++) {
        bonus = bonus + p.getContinents().get(j).getBonusArmies();
      }
      System.out.println
        ("you received " + bonus + " bonus troops for the continents you are holding");
    }
    troopsNewTurn = (p.getTerritories().size() / 3) + bonus;
    if (troopsNewTurn < 3)
    {
      troopsNewTurn = 3;
    }
    System.out.println(p.getName() + " receives " + troopsNewTurn + " troops");
    return troopsNewTurn;
  }

  public void continent_check() {
    int a = 0;
    for (int y = 0; y < board.getContinentList().size(); y++) {
      for (int z = 0; z < board.getContinentList().get(y).getMemberTerritories().size(); z++) {
        if (currentPlayer.getTerritories().contains(board.getContinentList().get(y).getMemberTerritories().get(z))) {
          a += 1;
        }
      }
      if (a == board.getContinentList().get(y).getMemberTerritories().size()) {
        System.out.println(board.getContinentList().get(y).getMemberTerritories().size());
        System.out.println(board.getContinentList().get(y).getName() + " is added to " + currentPlayer.getName());
        currentPlayer.addContinents(board.getContinentList().get(y));
      }
      a = 0;
    }
  }


  public int getupdateTroopsNewTurn() {
    return 0;
  }
}
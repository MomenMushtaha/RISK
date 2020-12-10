package Logic;


import Objects.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;


/**
 * Gameplay Class contains the logic used to run RISK
 *
 * @author Momin Mushtaha
 * @version 2
 */
public class Gameplay implements Serializable {
  public Board board;
  public final ArrayList<Player> players;
  public int numPlayers;
  private Player currentPlayer;
  private int troopsNewTurn;
  public ArrayList<Territory> pathListing;
  private String state;
  private Boolean addCard;
  private ArrayList<Territory> borderTerritories;


  /**
   * Constructor for objects of class Gameplay
   */
  public Gameplay() {
    this.players = new ArrayList<>();
    addCard = false;
  }


  /**
   * Performs an attack command between territories t1 and t2
   *
   * @param targetTerritory    is the Target Territory
   * @param attackingTerritory is the attacking Territory
   * @param attackers          The number of troops the attacking Territory has to attack with
   */
  public void attack(Territory targetTerritory, Territory attackingTerritory, int attackers) {
    threeDice dice3 = new threeDice();
    twoDice dice2 = new twoDice();
    Die die = new Die();
    int a;
    int d;
    int aa;
    int dd;

    if (targetTerritory.getPlayer() == getCurrentPlayer()) {
      System.out.println("You cannot attack your own territory");
      return;
    }
    if (attackers == 1) {
      System.out.println("You can only attack with a 2 or more Soldiers");
      return;
    }
    if (attackers == 2) {
      System.out.println(attackingTerritory.getPlayer().getName() + " is attacking with 1 die");
      System.out.println(targetTerritory.getPlayer().getName() + " is defending with 1 die");
      a = die.roll();
      d = die.roll();
      if (a > d) {
        targetTerritory.removeTroops(1);
        System.out.println(targetTerritory.getPlayer().getName() + " lost 1 soldier");
      } else {
        attackingTerritory.removeTroops(1);
        System.out.println(attackingTerritory.getPlayer().getName() + " lost 1 soldier");
      }
    }
    if (attackers == 3) {
      System.out.println(attackingTerritory.getPlayer().getName() + " is attacking with 2 dice");
      dice2.roll();
      a = dice2.getHighest();
      aa = dice2.getSecondHighest();
      if (targetTerritory.getTroops() >= 2) {
        System.out.println(targetTerritory.getPlayer().getName() + " is defending with 2 dice");
        dice2.roll();
        d = dice2.getHighest();
        dd = dice2.getSecondHighest();
        if (a > d & aa > dd) {
          targetTerritory.removeTroops(2);
          System.out.println(targetTerritory.getPlayer().getName() + " lost 2 soldiers");
        } else if ((a > d & aa < dd) | (a < d & aa > dd)) {
          targetTerritory.removeTroops(1);
          System.out.println(targetTerritory.getPlayer().getName() + " lost 1 soldier");
          attackingTerritory.removeTroops(1);
          System.out.println(attackingTerritory.getPlayer().getName() + " lost 1 soldier");
        } else {
          attackingTerritory.removeTroops(2);
          System.out.println(attackingTerritory.getPlayer().getName() + " lost 1 soldier");
        }
      } else if (targetTerritory.getTroops() == 1) {
        System.out.println(targetTerritory.getPlayer().getName() + " is defending with 1 die");
        d = die.roll();
        if (a > d) {
          targetTerritory.removeTroops(1);
          System.out.println(targetTerritory.getPlayer().getName() + " lost 1 soldier");
        } else {
          attackingTerritory.removeTroops(1);
          System.out.println(attackingTerritory.getPlayer().getName() + " lost 1 soldier");
        }
      }
    }
    if (attackers >= 4) {
      System.out.println(attackingTerritory.getPlayer().getName() + " is attacking with 3 dice");
      dice3.roll();
      a = dice3.getHighest();
      aa = dice3.getSecondHighest();
      if (targetTerritory.getTroops() == 1) {
        System.out.println(targetTerritory.getPlayer().getName() + " is defending with 1 die");
        d = die.roll();
        if (a > d) {
          targetTerritory.removeTroops(1);
          System.out.println(targetTerritory.getPlayer().getName() + " lost 1 soldier");
        } else {
          attackingTerritory.removeTroops(1);
          System.out.println(attackingTerritory.getPlayer().getName() + " lost 1 soldier");
        }
      } else if (targetTerritory.getTroops() >= 2) {
        System.out.println(targetTerritory.getPlayer().getName() + " is defending with 2 dice");
        dice2.roll();
        d = dice2.getHighest();
        dd = dice2.getSecondHighest();
        if (a > d & aa > dd) {
          targetTerritory.removeTroops(2);
          System.out.println(targetTerritory.getPlayer().getName() + " lost 2 soldiers");
        }
        if (a > d & aa <= dd) {
          targetTerritory.removeTroops(1);
          System.out.println(targetTerritory.getPlayer().getName() + " lost 1 soldier");
          attackingTerritory.removeTroops(1);
          System.out.println(attackingTerritory.getPlayer().getName() + " lost 1 soldier");
        } else if (a <= d & aa > dd) {
          targetTerritory.removeTroops(1);
          System.out.println(targetTerritory.getPlayer().getName() + " lost 1 soldier");
          attackingTerritory.removeTroops(1);
          System.out.println(attackingTerritory.getPlayer().getName() + " lost 1 soldier");
        } else if (a <= d & aa <= dd) {
          attackingTerritory.removeTroops(2);
          System.out.println(attackingTerritory.getPlayer().getName() + " lost 2 soldiers");
        }
      }
    }
    attackResult(targetTerritory, attackingTerritory);
  }


  /**
   * Shows the results of the attack command performed and indicates what happens next
   *
   * @param targetTerritory    is the defending Territory
   * @param attackingTerritory is the attacking Territory
   */
  public void attackResult(Territory targetTerritory, Territory attackingTerritory) {
    state = "fortifying";
    if (attackingTerritory.getTroops() == 1) {
      System.out.println("cant attack daug common have some sense, you got nobody there");
    } else if (targetTerritory.getTroops() <= 0) {
      if (targetTerritory.getTroops() < 0) {
        int k = targetTerritory.getTroops();
        targetTerritory.removeTroops(k);
      }
      //defending territory has no troops left to defend with
      System.out.println("defending territory has no troops left");
      System.out.println(targetTerritory.getName() + " was conquered!");
      addCard = true;
      Player OldOwner = targetTerritory.getPlayer();
      targetTerritory.getPlayer().removeTerritories(targetTerritory);
      attackingTerritory.getPlayer().addTerritories(targetTerritory);
      targetTerritory.changeOwner(attackingTerritory.getPlayer());
      continent_check(attackingTerritory.getPlayer());
      if (OldOwner.getTerritories().size() == 0) {
        //eliminated if it was the defenders last territory
        removePlayer(OldOwner);
      }
      if (playersSize() == 1) {
        //Declares if we have a winner
        System.out.println(attackingTerritory.getPlayer().getName() + " has won");
      }
      if (attackingTerritory.getTroops() > 3) {
        System.out.println("Please choose how many troops you would like to move");
      } else if (attackingTerritory.getTroops() == 3) {
        targetTerritory.addTroops(2);
        attackingTerritory.removeTroops(2);
        System.out.println("Two Troops went to " + targetTerritory.getName() + " and one remaining in " + attackingTerritory.getName());
      } else if (attackingTerritory.getTroops() == 2) {
        targetTerritory.addTroops(1);
        attackingTerritory.removeTroops(1);
        System.out.println("One Troop wnt to " + targetTerritory.getName() + " and one remaining in " + attackingTerritory.getName());
      }
    } else {
      attack(mapper(targetTerritory.getName()), mapper(attackingTerritory.getName()), attackingTerritory.getTroops());
    }
  }


  /**
   * moving the troops from the attacking territory to the conquered one
   *
   * @param newTerritory    moving troops to
   * @param sourceTerritory moving troops from
   * @param TroopsToMove    how many troops to move
   */
  public void MoveAfterAttack(Territory newTerritory, Territory sourceTerritory, int TroopsToMove) {
    //how many troops to move to Territory t
    if (TroopsToMove >= 3) {
      System.out.println(TroopsToMove + " added to " + newTerritory.getName() + " from " + sourceTerritory.getName());
      newTerritory.addTroops(TroopsToMove);
      sourceTerritory.removeTroops(TroopsToMove);
    }
  }


  /**
   * Fortifies the troops when the command is passed
   */
  public void fortify(Territory sourceTerritory, Territory targetTerritory, int fortifiedTroops) {
    state = "";
    sourceTerritory.removeTroops(fortifiedTroops);
    targetTerritory.addTroops(fortifiedTroops);
  }


  /**
   * Method to find if there a path between two countries or not
   *
   * @param source: The country which armies are being moved from
   * @param target: The country which armies are being moved to
   * @return Returns true if there is a path to move the armies between countries
   */
  public boolean path(Territory source, Territory target) {
    LinkedList<Territory> visitingNext = new LinkedList<>();
    HashSet<String> visited = new HashSet<>();
    visitingNext.add(source);
    while (!visitingNext.isEmpty()) {
      Territory node = visitingNext.remove();
      if (node.getName().equals(target.getName())) {
        return true;
      }
      if (visited.contains(node.getName()))
        continue;
      visited.add(node.getName());
      for (Territory child : node.getBorderTerritories()) {
        if (child.getPlayer() == node.getPlayer()) {
          visitingNext.add(child);
        }
      }
    }
    return false;
  }


  /**
   * gets the current player
   *
   * @return the current player playing
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * sets the new current player at the start of the turn
   *
   * @param Index index indicating which player is to be set
   */
  public void setCurrentPlayer(int Index) {
    currentPlayer = getPlayers(Index);
  }

  /**
   * Deploy troops at the beginning of each Player's turn
   *
   * @param newTroop an Integer of the number of troops to be deployed on the Territory specified in the method
   * @param terr     is the Territory to be deployed at
   */
  public void deploy(Territory terr, int newTroop) {
    state = "attacking";
    terr.addTroops(newTroop);
  }

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
    player.equals(null);
  }

  /**
   * Initializes the players to the game
   *
   * @param number an integer of the number of players to be initialized
   */
  public void InitializePlayers(int number) {
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
      board.getTerritoriesList()[n].changeOwner(getPlayers(Num));
      Num = (Num + 1) % p;
    }
  }

  public Board getBoard()
  {
    return board;
  }

  /**
   * Sets the amount of initial troops each player can start out with depending on number of players
   *
   * @param numPlayers is the number of players playing the Risk
   */
  public void NumberInitialTroops(int numPlayers) {
    //2 : 50 troops each, 3: 35 troops each, 4: 30 troops, 5: 25, 6: 20
    if(numPlayers == 2) shuffleTroops(50);
    else if(numPlayers == 3) shuffleTroops(35);
    else if(numPlayers == 4) shuffleTroops(30);
    else if(numPlayers == 5) shuffleTroops(25);
    else if(numPlayers == 6) shuffleTroops(20);
    }


  /**
   * randomly shuffles the troops around the territories
   * @param playerTroops is the amount of troops each player will get
   */
    private void shuffleTroops(int playerTroops)
    {
      Random nextTerr = new Random();
      for (Player player : players) {
        ArrayList<Territory> playerTerritories = player.getTerritories();
        for (int Troops = playerTroops - playerTerritories.size();Troops > 0; Troops --) {
          int size = playerTerritories.size()-1;
          for (int s = 0;s <= size;s++)
          {
            player.getTerritories().get(s).addTroops(1);
            Troops = Troops-1;
          }
          player.getTerritories().get(nextTerr.nextInt(size)).addTroops(1);
        }
      }
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
    for (Territory terr : territoryList) {
      System.out.println((terr.getName() + ": Troops = " + terr.getTroops()));
    }
  }

  /**
   * lists the cards the player has in his hands
   */
  public void listTheCards() {
    if (getCurrentPlayer().getHand() == null) {
      System.out.println("you dont have any cards in your hand");
    } else {
      ArrayList<Card> cards = getCurrentPlayer().getHand().getCards();
      System.out.println("Cards: ");
      for (Card card : cards) {
        System.out.println(card.getTerritoryName() + ": " + card.getType() + " Troops = " + card.getTypeWorth());
      }
    }
  }

  /**
   * lists the Territories a player owns
   *
   * @param territoryList a list of Territories
   */
  public String[] listTheTerritories(ArrayList<Territory> territoryList) {
    String[] territoryListing = new String[territoryList.size()];
    int t = 0;
    for (Territory terr : territoryList) {
      territoryListing[t] = (terr.getName() + ": Troops = " + terr.getTroops());
      t++;
    }
    return territoryListing;
  }

  /**
   * lists the board territories of the territory passed
   *
   * @param terr the territory wished to list its border territories
   * @return a string array of border territories
   */
  public String[] listBorderingTerritories(Territory terr) {
    String[] borderingListing = new String[terr.getBorderTerritories().size()];
    borderTerritories = new ArrayList<>();
    int add = 0;
    for (int b = 0; b < terr.getBorderTerritories().size(); b++) {
      Territory bordering = terr.getBorderTerritories().get(b);
      if (getCurrentPlayer().getTerritories().contains(bordering)) {
        //do nothing
        // could just invert the if statement but its considered code smell
      } else {
        borderingListing[add] = bordering.getName() + ": Troops = " + bordering.getTroops();
        borderTerritories.add(bordering);
        add++;
      }
    }
    return borderingListing;
  }

  /**
   * list the path a territory has when fortifying is clicked
   *
   * @param sor source territory to list the path for
   * @returns String[] list of the path territories
   */
  public String[] listPathTerritories(Territory sor) {
    pathListing = new ArrayList<>();
    for (Territory terr : board.getTerritoriesList()) {
      if (path(sor, terr)) {
        pathListing.add(terr);
      }
    }
    String[] listPath = new String[pathListing.size()];
    for (int p = 0; p < listPath.length; p++) {
      listPath[p] = (pathListing.get(p).getName() + ": Troops = " + pathListing.get(p).getTroops());
    }
    return listPath;
  }

  /**
   * get a territory for the pathListing at a specific index
   *
   * @param Index territory wished to be retrieved index
   * @return the territory at the index specified
   */
  public Territory getPathListingAtIndex(int Index) {
    return pathListing.get(Index);
  }

  /**
   * trades cards for current player
   */
  public void trade() {
    currentPlayer.addToTradeTimes();
    troopsNewTurn = currentPlayer.getNewTroopers();
    currentPlayer.trade = "";
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
  public void quit() {
    System.out.println(getCurrentPlayer().getName() + " has left the game");
    removePlayer(getCurrentPlayer());
    if ((playersSize() == 1)) {
      System.out.println(getPlayers(0).getName() + " has won the game");
      System.exit(0);
    }
  }

  /**
   * Prints welcoming phrases at the beginning of each ga,e
   */
  public void printWelcome() {
    System.out.println();
    System.out.println("Welcome to Risk!");
    System.out.println("Everybody wants to rule the world!");
    System.out.println("Type 'help' if you need help.");
    System.out.println("At the start of each turn each player receives 3 or more troops and" +
      " if you rule a whole continent you will get more bonus troops.");
    System.out.println("The game will start with player 1");
    System.out.println();
  }

  /**
   * Prints Help then followed by the commands possible for the game
   */
  public void printHelp() {
    {
      System.out.println("ARE YOU LOST?");
      System.out.println("I CAN HELP YOU");
      System.out.println();
      printCommands();
    }
  }

  /**
   * checks if the player conquered at least one territory in his turn and adds a card to current player
   */
  private void checkAddCards() {
    if (addCard) {
      getCurrentPlayer().addCardToPlayer(board.deck.draw());
      System.out.println("A card added to " + getCurrentPlayer().getName());
    } else {
      System.out.println("no cards are added to " + getCurrentPlayer().getName());
    }
  }

  /**
   * Signals that currentPlayer wants to pass the turn
   */
  public void nextPlayerTurn() {
    checkAddCards();
    System.out.println(getCurrentPlayer().getName() + " passes");
    if (playersSize() == 6) {
      if (getCurrentPlayer() == getPlayers(5)) {
        setCurrentPlayer(0);
      } else if (getCurrentPlayer() == getPlayers(4)) {
        setCurrentPlayer(5);
      } else if (getCurrentPlayer() == getPlayers(3)) {
        setCurrentPlayer(4);
      } else if (getCurrentPlayer() == getPlayers(2)) {
        setCurrentPlayer(3);
      } else if (getCurrentPlayer() == getPlayers(1)) {
        setCurrentPlayer(2);
      } else if (getCurrentPlayer() == getPlayers(0)) {
        setCurrentPlayer(1);
      }
    } else if (playersSize() == 5) {
      if (getCurrentPlayer() == getPlayers(4)) {
        setCurrentPlayer(0);
      } else if (getCurrentPlayer() == getPlayers(3)) {
        setCurrentPlayer(4);
      } else if (getCurrentPlayer() == getPlayers(2)) {
        setCurrentPlayer(3);
      } else if (getCurrentPlayer() == getPlayers(1)) {
        setCurrentPlayer(2);
      } else if (getCurrentPlayer() == getPlayers(0)) {
        setCurrentPlayer(1);
      }
    } else if (playersSize() == 4) {
      if (getCurrentPlayer() == getPlayers(3)) {
        setCurrentPlayer(0);
      } else if (getCurrentPlayer() == getPlayers(2)) {
        setCurrentPlayer(3);
      } else if (getCurrentPlayer() == getPlayers(1)) {
        setCurrentPlayer(2);
      } else if (getCurrentPlayer() == getPlayers(0)) {
        setCurrentPlayer(1);
      }
    } else if (playersSize() == 3) {
      if (getCurrentPlayer() == getPlayers(2)) {
        setCurrentPlayer(0);
      } else if (getCurrentPlayer() == getPlayers(1)) {
        setCurrentPlayer(2);
      } else if (getCurrentPlayer() == getPlayers(0)) {
        setCurrentPlayer(1);
      }
    } else if (playersSize() == 2) {
      if (getCurrentPlayer() == getPlayers(1)) {
        setCurrentPlayer(0);
      } else if (getCurrentPlayer() == getPlayers(0)) {
        setCurrentPlayer(1);
      }
    } else {
      System.out.println(getCurrentPlayer().getName() + " has won");
    }
    System.out.println("NEXTTTTT!!");
    state = "deploying";
    troopsNewTurn = get_bonus(getCurrentPlayer());
    addCard = false;
    checkTradeCards();
    if (getCurrentPlayer().getIsAI()) {
      AI ai = new AI(this);
      ai.makeMove();
    }//if pass is entered cycle to the next player
  }

  /**
   * get the state of the trade string
   *
   * @return string indicating the state of trade
   */
  public String getTrade() {
    return currentPlayer.trade;
  }

  /**
   * checks if cards are tradeable or not
   */
  private void checkTradeCards() {
    if (getCurrentPlayer().getHand() == null) {
      System.out.println("you have no cards in your hand");
      currentPlayer.trade = "";
    } else if (getCurrentPlayer().getHand().getCards().size() < 3) {
      System.out.println("you have less than 3 cards");
      currentPlayer.trade = "";
    } else if (getCurrentPlayer().getHand().mustTurnInCards()) {
      System.out.println("you must trade cards this turn");
      currentPlayer.trade = "must Trade";
    } else {
      currentPlayer.trade = "can Trade";
    }
  }

  /**
   * calculates the bonus troops of the new turn
   *
   * @param p is the current player
   * @return integer of the bonus troops at the start of the turn
   */
  public int get_bonus(Player p) {
    System.out.println("It is " + p.getName() + " turn");
    int bonus = 0;
    if (p.getContinents().size() > 0) {
      for (int j = 0; j < p.getContinents().size(); j++) {
        bonus = bonus + p.getContinents().get(j).getBonusArmies();
      }
      System.out.println
        ("you received " + bonus + " bonus troops for the continents you are holding");
    }
    troopsNewTurn = (p.getTerritories().size() / 3) + bonus;
    if (getTroopsNewTurn() < 3) {
      troopsNewTurn = 3;
    }
    System.out.println(p.getName() + " receives " + getTroopsNewTurn() + " troops");
    return troopsNewTurn;
  }


  /**
   * checks any continents are to be added to the current player
   */
  public void continent_check(Player p) {
    int a = 0;
    for (int y = 0; y < board.getContinentList().size(); y++) {
      for (int z = 0; z < board.getContinentList().get(y).getMemberTerritories().size(); z++) {
        if (p.getTerritories().contains(board.getContinentList().get(y).getMemberTerritories().get(z))) {
          a += 1;
        }
      }
      if (a == board.getContinentList().get(y).getMemberTerritories().size()) {
        System.out.println(board.getContinentList().get(y).getMemberTerritories().size());
        System.out.println(board.getContinentList().get(y).getName() + " is added to " + p.getName());
        p.addContinents(board.getContinentList().get(y));
      }
      a = 0;
    }
  }


  /**
   * get the troops of the new turn
   *
   * @return an integer of the troops of the new turn
   */
  public int getTroopsNewTurn() {
    return troopsNewTurn;
  }


  /**
   * updates the troops after they are added
   */
  public void updateTroopsNewTurn(int addedTroops) {
    troopsNewTurn = troopsNewTurn - addedTroops;
  }


  /**
   * calculates the size of players ArrayList
   *
   * @return integer number of the size of players
   */
  public int playersSize() {
    return players.size();
  }


  /**
   * getter for state string
   *
   * @return a string indicating the state of the turn
   */
  public String getState() {
    return state;
  }


  /**
   * getter for border territories
   *
   * @return an ArrayList of the border territories
   */
  public ArrayList<Territory> getBorderTerritories() {
    return borderTerritories;
  }


  /**
   * removes the card for hand if the option chosen is applicable
   *
   * @param index is the index indicating which option was chosen
   */
  public boolean indexer(int index) {
    boolean x = false;
    //easier to read when the if statement is just one line
    if (index == 0) {
      x = getCurrentPlayer().getHand().removeCards(1, 2, 3);
    }
    if (index == 1) {
      x = getCurrentPlayer().getHand().removeCards(1, 2, 4);
    }
    if (index == 2) {
      x = getCurrentPlayer().getHand().removeCards(1, 2, 5);
    }
    if (index == 3) {
      x = getCurrentPlayer().getHand().removeCards(1, 3, 4);
    }
    if (index == 4) {
      x = getCurrentPlayer().getHand().removeCards(1, 3, 5);
    }
    if (index == 5) {
      x = getCurrentPlayer().getHand().removeCards(1, 4, 5);
    }
    if (index == 6) {
      x = getCurrentPlayer().getHand().removeCards(2, 3, 4);
    }
    if (index == 7) {
      x = getCurrentPlayer().getHand().removeCards(2, 3, 5);
    }
    if (index == 8) {
      x = getCurrentPlayer().getHand().removeCards(2, 4, 5);
    }
    if (index == 9) {
      x = getCurrentPlayer().getHand().removeCards(3, 4, 5);
    }
    return x;
  }

  /**
   * set the number of players playing
   * @param num is an integer indicating the number of players
   */
  public void setNumPlayers(int num)
  {
    numPlayers = num;
  }

  /**
   * sets the bord of risk based on the xml file passed
   * @param xml is the file passed
   */
  public void setBoard(File xml)
{
  this.board = new Board(xml);
}

  /**
   * initialize the game at its start
   *
   */
  public void startGame() {
    addInitialTerritories(numPlayers);
    NumberInitialTroops(numPlayers);
    setCurrentPlayer(0);
    get_bonus(getCurrentPlayer());
    state = "deploying";
  }
}
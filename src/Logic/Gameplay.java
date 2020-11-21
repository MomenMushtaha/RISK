package Logic;

import Objects.*;
import java.util.*;

/**
 * Gameplay Class contains the logic used to run RISK
 * @author Momin Mushtaha
 * @version 1
 */
public class Gameplay {
  private final Board board;
  private Player currentPlayer;
  private final ArrayList<Player> players;
  private int troopsNewTurn;
  private ArrayList<Territory> pathListing;
  private String state;
  private String trade;
  private Boolean addCard;
  private ArrayList<Territory> currentPlayerTerritories;
  private ArrayList<Territory> borderTerritories;


  /**
   * Constructor for objects of class Gameplay
   */
  public Gameplay() {
    this.board = new Board();
    this.players = new ArrayList<>();
    state ="";
    trade ="";
  }


  /**
   * Performs an attack command between territories t1 and t2
   *
   * @param targetTerritory is the Target Territory
   * @param attackingTerritory is the attacking Territory
   * @param attackers The number of troops the t2 has to attack with
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
   * @param targetTerritory  is the defending Territory
   * @param attackingTerritory is the attacking Territory
   */
  public void attackResult(Territory targetTerritory, Territory attackingTerritory) {
    state = "attacked";
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
      continent_check();
      if (OldOwner.getTerritories().size() == 0) {
        //eliminated if it was the defenders last territory
        removePlayer(OldOwner);
      }
      if (playersSize() == 1) {
        //Declares if we have a winner
        System.out.println(getCurrentPlayer().getName() + " has won");
      }
      if (attackingTerritory.getTroops() > 3) {
        return;
      } else if (attackingTerritory.getTroops() == 3) {
        targetTerritory.addTroops(2);
        attackingTerritory.removeTroops(2);
        System.out.println("Two Troops left to " + targetTerritory.getName() + " and one remaining in " + attackingTerritory.getName());
      } else if (attackingTerritory.getTroops() == 2) {
        targetTerritory.addTroops(1);
        attackingTerritory.removeTroops(1);
        System.out.println("One Troop left to " + targetTerritory.getName() + " and one remaining in " + attackingTerritory.getName());
      }
    } else {
      attack(mapper(targetTerritory.getName()), mapper(attackingTerritory.getName()), attackingTerritory.getTroops());
    }
  }


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
  public void fortify(Territory sourceTerritory, Territory targetTerritory, int fortifiedTroops){
    state = "fortified";
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
public Player getCurrentPlayer()
{
  return currentPlayer;
}

  /**
   * Deploy troops at the beginning of each Player's turn
   *
   * @param newTroop an Integer of the number of troops to be deployed on the Territory specified in the method
   */
  public void deploy(Territory terr,int newTroop)
  {
    state = "deployed";
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
      board.getTerritoriesList()[n].changeOwner(getPlayers(Num));
      Num = (Num + 1) % p;
    }
  }

  /**
   * Sets the amount of initial troops each player can start out with depending on number of players
   */
  public void NumberInitialTroops(int numPlayers) {
    //2 : 50 troops each, 3: 35 troops each, 4: 30 troops, 5: 25, 6: 20
    int m;
    for (int pl = 0; pl < numPlayers; pl++ )
    if (numPlayers == 2) {
        for (m = 0; m < 13; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(2);
        }
        for (m = 13; m < 21; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(3);
        }
    } else if (numPlayers == 3) {
      for (m = 0; m < 7; m++) {
        getPlayers(pl).getTerritoyAtIndex(m).addTroops(2);
      }
      for (m = 7; m < 14; m++) {
        getPlayers(pl).getTerritoyAtIndex(m).addTroops(3);
      }
    } else if (numPlayers == 4) {
      if(getPlayers(pl).getTerritories().size() < 11) {
        for (m = 0; m < 3; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(2);
        }
        for (m = 3; m < 7; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(3);
        }
          for (m = 7; m < 10; m++) {
            getPlayers(pl).getTerritoyAtIndex(m).addTroops(4);
          }
      }
      else if(getPlayers(pl).getTerritories().size() == 11){
        for (m = 0; m < 5; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(2);
        }
        for (m = 5; m < 9; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(3);
        }
        for (m = 9; m < 11; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(4);
        }
      }
    } else if (numPlayers == 5) {
      if(getPlayers(pl).getTerritories().size() < 9) {
        for (m = 0; m < 3; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(2);
        }
        getPlayers(pl).getTerritoyAtIndex(3).addTroops(3);
        for (m = 4; m < 7; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(4);
        }
      }
    else if(getPlayers(pl).getTerritories().size() == 9){
        for (m = 0; m < 2; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(2);
        }
      for (m = 2; m < 5; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(3);
        }
        for (m = 5; m < 8; m++) {
          getPlayers(pl).getTerritoyAtIndex(m).addTroops(4);
        }
      }
    }
    else if (numPlayers == 6) {
      getPlayers(pl).getTerritoyAtIndex(0).addTroops(2);
      for (m = 1; m < 7; m++) {
        getPlayers(pl).getTerritoyAtIndex(m).addTroops(3);
      }
    }}


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

  public void listTheCards() {
    if (getCurrentPlayer().getHand() == null)
    {
      System.out.println("you dont have any cards in your hand");
    }
    else{
    ArrayList<Card> cards = getCurrentPlayer().getHand().getCards();
    System.out.println("Cards: ");
    for (Card card : cards) {
      System.out.println(card.getTerritoryName() + ": " + card.getType() + " Troops = " + card.getTypeWorth());
    }
  }}

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
    String[] borderingListing = new String[terr.getBorderTerritories().size()];
    borderTerritories = new ArrayList<>();
    int add = 0;
    for (int b = 0; b < terr.getBorderTerritories().size(); b++) {
      Territory bordering = terr.getBorderTerritories().get(b);
      if (getCurrentPlayer().getTerritories().contains(bordering)) {
      }else{
        borderingListing[add] = bordering.getName() + ": Troops = " + bordering.getTroops();
        borderTerritories.add(bordering);
        add++;
      }
    }
    return borderingListing;
  }

  public String[] listPathTerritories(Territory sor) {
    pathListing = new ArrayList<>();
    for (Territory terr : board.getTerritoriesList()) {
      if (path(sor, terr)) {
        pathListing.add(terr);
      }
    }
    String[] listPath = new String[pathListing.size()];
    for (int p = 0; p < listPath.length; p++)
    {
      listPath[p] = (pathListing.get(p).getName() + ": Troops = " + pathListing.get(p).getTroops());
    }
    return listPath;
  }

  public Territory getPathListingAtIndex(int Index)
  {
    return pathListing.get(Index);
  }

public String getTrade()
{
  return trade;
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
  public void printWelcome(){
    System.out.println();
    System.out.println("Welcome to Risk!");
    System.out.println("Everybody wants to rule the world!");
    System.out.println("Type 'help' if you need help.");
    System.out.println("At the start of each turn each player receives 3 or more troops and" +
      " if you rule a whole continent you will get more bonus troops.");
    System.out.println("The game will start with player 1");
    System.out.println();
    setCurrentPlayer(0);
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

  private void checkAddCards()
  {

    if (addCard)
    {
      getCurrentPlayer().addCardToPlayer(board.deck.draw());
      System.out.println("A card added to " + getCurrentPlayer().getName());
    }
    else
    {
      System.out.println("no cards are added to " + getCurrentPlayer().getName());
    }
  }

  /**
   * Signals that currentPlayer wants to pass the turn
   *
   */
  public void nextPlayerTurn() {
    checkAddCards();
    System.out.println(getCurrentPlayer().getName() + " passes");
    if (playersSize() == 6)
    {
      if (getCurrentPlayer() == getPlayers(5))
      {setCurrentPlayer(0);}
      else if (getCurrentPlayer() == getPlayers(4))
      {setCurrentPlayer(5);}
      else if (getCurrentPlayer() == getPlayers(3))
      {setCurrentPlayer(4);}
      else if (getCurrentPlayer() == getPlayers(2))
    {setCurrentPlayer(3);}
     else if (getCurrentPlayer() == getPlayers(1))
    {setCurrentPlayer(2);}
     else if (getCurrentPlayer() == getPlayers(0))
    {setCurrentPlayer(1);}
    }
    else if (playersSize() == 5)
    {
      if (getCurrentPlayer() == getPlayers(4))
    {setCurrentPlayer(0);}
    else if (getCurrentPlayer() == getPlayers(3))
    {setCurrentPlayer(4);}
    else if (getCurrentPlayer() == getPlayers(2))
    {setCurrentPlayer(3);}
    else if (getCurrentPlayer() == getPlayers(1))
    {setCurrentPlayer(2);}
    else if (getCurrentPlayer() == getPlayers(0))
    {setCurrentPlayer(1);}
    }
    else if(playersSize() == 4)
    {
      if (getCurrentPlayer() == getPlayers(3))
    {setCurrentPlayer(0);}
    else if (getCurrentPlayer() == getPlayers(2))
    {setCurrentPlayer(3);}
    else if (getCurrentPlayer() == getPlayers(1))
    {setCurrentPlayer(2);}
    else if (getCurrentPlayer() == getPlayers(0))
    {setCurrentPlayer(1);}
    }
    else if(playersSize() == 3)
    {
      if (getCurrentPlayer() == getPlayers(2))
      {setCurrentPlayer(0);}
      else if (getCurrentPlayer() == getPlayers(1))
      {setCurrentPlayer(2);}
      else if (getCurrentPlayer() == getPlayers(0))
      {setCurrentPlayer(1);}
    }
    else if(playersSize() == 2)
    {
      if (getCurrentPlayer() == getPlayers(1))
    {setCurrentPlayer(0);}
    else if (getCurrentPlayer() == getPlayers(0))
    {setCurrentPlayer(1);}}
    else
    {
      System.out.println(getCurrentPlayer().getName() + " has won");
    }
    System.out.println("NEXTTTTT!!");
    state  = "";
    troopsNewTurn = get_bonus(getCurrentPlayer());
    currentPlayerTerritories = new ArrayList<>();
    addCard = false;
    checkTradeCards();
    //if pass is entered cycle to the next player
  }

  private void checkTradeCards() {
    if(getCurrentPlayer().getHand() == null) {
      trade = "";
    }
    else{
    if (getCurrentPlayer().getHand().checkCardSet())
    {
      if(getCurrentPlayer().getHand().getCards().size() == 5) {
        trade = "must Trade";
      }
      else{
        trade = "can Trade";
      }
    }
    else {
      trade = "";
    }
  }}

  public void setCurrentPlayer(int Index)
  {
    currentPlayer = getPlayers(Index);
  }


  public int get_bonus(Player p)
  {
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
    if (getTroopsNewTurn() < 3)
    {
      troopsNewTurn = 3;
    }
    System.out.println(p.getName() + " receives " + getTroopsNewTurn() + " troops");
    return troopsNewTurn;
  }

  public void continent_check() {
    int a = 0;
    for (int y = 0; y < board.getContinentList().size(); y++) {
      for (int z = 0; z < board.getContinentList().get(y).getMemberTerritories().size(); z++) {
        if (getCurrentPlayer().getTerritories().contains(board.getContinentList().get(y).getMemberTerritories().get(z))) {
          a += 1;
        }
      }
      if (a == board.getContinentList().get(y).getMemberTerritories().size()) {
        System.out.println(board.getContinentList().get(y).getMemberTerritories().size());
        System.out.println(board.getContinentList().get(y).getName() + " is added to " + getCurrentPlayer().getName());
        getCurrentPlayer().addContinents(board.getContinentList().get(y));
      }
      a = 0;
    }
  }

public int getTroopsNewTurn()
{
  return troopsNewTurn;
}
  public void updateTroopsNewTurn(int addedTroops) {
    troopsNewTurn = troopsNewTurn - addedTroops;
  }


public int playersSize()
{
  return players.size();
}

public String getState()
{
  return state;
}
public ArrayList<Territory> getBorderTerritories()
{
  return borderTerritories;
}

public void startGame(int numPlayers)
  {
    InitializePlayers(numPlayers) ;
    addInitialTerritories(numPlayers);
    NumberInitialTroops(numPlayers);
    setCurrentPlayer(numPlayers-1);
    get_bonus(getCurrentPlayer());
    addCard = false;
  }}
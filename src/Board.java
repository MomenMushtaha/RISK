import java.util.ArrayList;

/**
 * Board Class: This class creates the Territories, Continents, Cards, Deck, and Hands
 *
 * @author Momin Mushtaha
 */

public class Board {
  private static final int numTerritories = 42;
  public Territory[] territoriesList;
  private ArrayList<Continent> continentsList;


  //Troop types
  private Cavalry cavalry;
  private wildCard wildCardA;
  private infantry infantry;
  private artillery artillery;
  private wildCard wildCardB;
  private Territory Unknown;


  public Board(){
    setTerritories();
    setContinents();
  }

  /**
   * Sets the amount of initial troops each player can start out with depending on number of players
   */

  /**
   * Returns the number of Territories in the game
   * @return
   */
  private int getNumTerritories(){
    return numTerritories;
  }

  /**
   * Creates the cards and the deck for the Risk Game
   */


    /* Done in GamePlay()
    private void setTerritoryOwners() {

    }

     */

    /* Done in GamePlay()
    private void setPlayers() {

    }
     */

  /**
   * Creates the continents for the game and assigns the territories to each Continent
   */
  private void setContinents() {
    Continent NorthAmerica, SouthAmerica, Africa, Europe, Oceania, Asia;

    int bonusArmiesNA = 5;
    int bonusArmiesSA = 2;
    int bonusArmiesA = 5;
    int bonusArmiesEur = 5;
    int bonusArmiesOce = 2;
    int bonusArmiesAsia = 7;

    continentsList = new ArrayList<>();


    //Set territories in Continents
    ArrayList<Territory> NAterritores = new ArrayList<>();
    ArrayList<Territory> SAterritores = new ArrayList<>();
    ArrayList<Territory> AfAterritores = new ArrayList<>();
    ArrayList<Territory> EurNAterritores = new ArrayList<>();
    ArrayList<Territory> OceNAterritores = new ArrayList<>();
    ArrayList<Territory> AsiaNAterritores = new ArrayList<>();


    /**Add Territories for each Continent**/
    NAterritores.add(territoriesList[0]);
    NAterritores.add(territoriesList[1]);
    NAterritores.add(territoriesList[2]);
    NAterritores.add(territoriesList[3]);
    NAterritores.add(territoriesList[4]);
    NAterritores.add(territoriesList[5]);
    NAterritores.add(territoriesList[6]);
    NAterritores.add(territoriesList[7]);
    NAterritores.add(territoriesList[8]);

    SAterritores.add(territoriesList[9]);
    SAterritores.add(territoriesList[10]);
    SAterritores.add(territoriesList[11]);
    SAterritores.add(territoriesList[12]);

    AfAterritores.add(territoriesList[13]);
    AfAterritores.add(territoriesList[14]);
    AfAterritores.add(territoriesList[15]);
    AfAterritores.add(territoriesList[16]);
    AfAterritores.add(territoriesList[17]);
    AfAterritores.add(territoriesList[18]);

    EurNAterritores.add(territoriesList[19]);
    EurNAterritores.add(territoriesList[20]);
    EurNAterritores.add(territoriesList[21]);
    EurNAterritores.add(territoriesList[22]);
    EurNAterritores.add(territoriesList[23]);
    EurNAterritores.add(territoriesList[24]);
    EurNAterritores.add(territoriesList[25]);

    OceNAterritores.add(territoriesList[26]);
    OceNAterritores.add(territoriesList[27]);
    OceNAterritores.add(territoriesList[28]);
    OceNAterritores.add(territoriesList[29]);

    AsiaNAterritores.add(territoriesList[30]);
    AsiaNAterritores.add(territoriesList[31]);
    AsiaNAterritores.add(territoriesList[32]);
    AsiaNAterritores.add(territoriesList[33]);
    AsiaNAterritores.add(territoriesList[34]);
    AsiaNAterritores.add(territoriesList[35]);
    AsiaNAterritores.add(territoriesList[36]);


    //Initialize the Continents
    NorthAmerica = new Continent("North America", bonusArmiesNA, NAterritores);
    SouthAmerica = new Continent("South America", bonusArmiesSA, SAterritores);
    Africa = new Continent("Africa", bonusArmiesA, AfAterritores);
    Europe = new Continent("Europe", bonusArmiesEur, EurNAterritores);
    Oceania = new Continent("Oceania", bonusArmiesOce, OceNAterritores);
    Asia = new Continent("Asia", bonusArmiesAsia, AsiaNAterritores);

    //Add Continents to Continents list
    continentsList.add(NorthAmerica);
    continentsList.add(SouthAmerica);
    continentsList.add(Africa);
    continentsList.add(Europe);
    continentsList.add(Oceania);
    continentsList.add(Asia);
  }

  /**
   * Creates the Territories for the game and adds neighbouring territories for each Territory
   */
  private void setTerritories() {

    //Creating Unknown territory for WildCard
    Territory Unknown2 = new Territory("Unknown");
    this.Unknown = Unknown2;


    territoriesList = new Territory[42];

    //North America
    territoriesList[0] = new Territory("Alaska");
    territoriesList[1] = new Territory("NorthWestTerritories");
    territoriesList[2] = new Territory("GreenLand");
    territoriesList[3] = new Territory("Alberta");
    territoriesList[4] = new Territory("Ontario");
    territoriesList[5] = new Territory("Quebec");
    territoriesList[6] = new Territory("WestUSA");
    territoriesList[7] = new Territory("EastUSA");
    territoriesList[8] = new Territory("CentralAmerica");

    //South America
    territoriesList[9] = new Territory("Venezuela");
    territoriesList[10] = new Territory("Peru");
    territoriesList[11] = new Territory("Brazil");
    territoriesList[12] = new Territory("Argentina");

    //Africa
    territoriesList[13] = new Territory("NorthAfrica");
    territoriesList[14] = new Territory("Egypt");
    territoriesList[15] = new Territory("EastAfrica");
    territoriesList[16] = new Territory("Congo");
    territoriesList[17] = new Territory("SouthAfrica");
    territoriesList[18] = new Territory("Madagascar");

    //Europe
    territoriesList[19] = new Territory("Iceland");
    territoriesList[20] = new Territory("Scandinavia");
    territoriesList[21] = new Territory("Ukraine");
    territoriesList[22] = new Territory("Great Britain");
    territoriesList[23] = new Territory("Northern Europe");
    territoriesList[24] = new Territory("Southern Europe");
    territoriesList[25] = new Territory("Western Europe");

    //Oceania
    territoriesList[26] = new Territory("Indonesia");
    territoriesList[27] = new Territory("New Guinea");
    territoriesList[28] = new Territory("Western Australia");
    territoriesList[29] = new Territory("Eastern Australia");

    //Asia
    territoriesList[30] = new Territory("Siberia");
    territoriesList[31] = new Territory("Middle East");
    territoriesList[32] = new Territory("Afghanistan");
    territoriesList[33] = new Territory("Japan");
    territoriesList[34] = new Territory("China");
    territoriesList[35] = new Territory("India");
    territoriesList[36] = new Territory("Mongolia");
    territoriesList[37] = new Territory("Ural");
    territoriesList[38] = new Territory("Yakutsk");
    territoriesList[39] = new Territory("Kamchatka");
    territoriesList[40] = new Territory("Irkutsk");
    territoriesList[41] = new Territory("SoutheastAsia");

    //Add Bordering Territories
    //Alaska
    territoriesList[0].addBorderTerritories(territoriesList[1]);
    territoriesList[0].addBorderTerritories(territoriesList[3]);
    territoriesList[0].addBorderTerritories(territoriesList[33]);

    //NorthWestTerr.
    territoriesList[1].addBorderTerritories(territoriesList[0]);
    territoriesList[1].addBorderTerritories(territoriesList[3]);
    territoriesList[1].addBorderTerritories(territoriesList[4]);
    territoriesList[1].addBorderTerritories(territoriesList[4]);
    //Greenland
    territoriesList[2].addBorderTerritories(territoriesList[1]);
    territoriesList[2].addBorderTerritories(territoriesList[4]);
    territoriesList[2].addBorderTerritories(territoriesList[5]);
    territoriesList[2].addBorderTerritories(territoriesList[19]);

    //Alberta
    territoriesList[3].addBorderTerritories(territoriesList[6]);
    territoriesList[3].addBorderTerritories(territoriesList[4]);
    territoriesList[3].addBorderTerritories(territoriesList[1]);
    territoriesList[3].addBorderTerritories(territoriesList[0]);

    //Ontario
    territoriesList[4].addBorderTerritories(territoriesList[2]);
    territoriesList[4].addBorderTerritories(territoriesList[4]);
    territoriesList[4].addBorderTerritories(territoriesList[5]);
    territoriesList[4].addBorderTerritories(territoriesList[6]);
    territoriesList[4].addBorderTerritories(territoriesList[8]);
    //Quebec
    territoriesList[5].addBorderTerritories(territoriesList[7]);
    territoriesList[5].addBorderTerritories(territoriesList[4]);
    territoriesList[5].addBorderTerritories(territoriesList[2]);
    //WESTUSA
    territoriesList[6].addBorderTerritories(territoriesList[8]);
    territoriesList[6].addBorderTerritories(territoriesList[7]);
    territoriesList[6].addBorderTerritories(territoriesList[4]);
    territoriesList[6].addBorderTerritories(territoriesList[3]);
    //EASTUSA
    territoriesList[7].addBorderTerritories(territoriesList[6]);
    territoriesList[7].addBorderTerritories(territoriesList[8]);
    territoriesList[7].addBorderTerritories(territoriesList[5]);
    territoriesList[7].addBorderTerritories(territoriesList[4]);
    //CENTRALAMERICA
    territoriesList[8].addBorderTerritories(territoriesList[6]);
    territoriesList[8].addBorderTerritories(territoriesList[7]);
    territoriesList[8].addBorderTerritories(territoriesList[9]);


    //Venezuela
    territoriesList[9].addBorderTerritories(territoriesList[8]);
    territoriesList[9].addBorderTerritories(territoriesList[11]);
    territoriesList[9].addBorderTerritories(territoriesList[10]);
    //Peru
    territoriesList[10].addBorderTerritories(territoriesList[11]);
    territoriesList[10].addBorderTerritories(territoriesList[12]);
    territoriesList[10].addBorderTerritories(territoriesList[9]);
    //Brazil
    territoriesList[11].addBorderTerritories(territoriesList[12]);
    territoriesList[11].addBorderTerritories(territoriesList[10]);
    territoriesList[11].addBorderTerritories(territoriesList[9]);
    territoriesList[11].addBorderTerritories(territoriesList[13]);
    //Argentina
    territoriesList[12].addBorderTerritories(territoriesList[11]);
    territoriesList[12].addBorderTerritories(territoriesList[10]);

    //NorthAFRICA
    territoriesList[13].addBorderTerritories(territoriesList[14]);
    territoriesList[13].addBorderTerritories(territoriesList[15]);
    territoriesList[13].addBorderTerritories(territoriesList[16]);
    territoriesList[13].addBorderTerritories(territoriesList[24]);
    territoriesList[13].addBorderTerritories(territoriesList[11]);
    //EGYPT
    territoriesList[14].addBorderTerritories(territoriesList[13]);
    territoriesList[14].addBorderTerritories(territoriesList[15]);
    territoriesList[14].addBorderTerritories(territoriesList[24]);
    territoriesList[14].addBorderTerritories(territoriesList[31]);

    //EAST-AFRIC
    territoriesList[15].addBorderTerritories(territoriesList[14]);
    territoriesList[15].addBorderTerritories(territoriesList[13]);
    territoriesList[15].addBorderTerritories(territoriesList[16]);
    territoriesList[15].addBorderTerritories(territoriesList[17]);
    territoriesList[15].addBorderTerritories(territoriesList[18]);
    territoriesList[15].addBorderTerritories(territoriesList[31]);
    //Congo
    territoriesList[16].addBorderTerritories(territoriesList[17]);
    territoriesList[16].addBorderTerritories(territoriesList[15]);
    territoriesList[16].addBorderTerritories(territoriesList[13]);
    //SouthAFRICA
    territoriesList[17].addBorderTerritories(territoriesList[16]);
    territoriesList[17].addBorderTerritories(territoriesList[18]);
    territoriesList[17].addBorderTerritories(territoriesList[15]);
    //Madagascar
    territoriesList[18].addBorderTerritories(territoriesList[17]);
    territoriesList[18].addBorderTerritories(territoriesList[15]);
    //ICELAND
    territoriesList[19].addBorderTerritories(territoriesList[2]);
    territoriesList[19].addBorderTerritories(territoriesList[22]);
    territoriesList[19].addBorderTerritories(territoriesList[20]);
    //Scand
    territoriesList[20].addBorderTerritories(territoriesList[22]);
    territoriesList[20].addBorderTerritories(territoriesList[21]);
    territoriesList[20].addBorderTerritories(territoriesList[33]);
    //Ukrain
    territoriesList[21].addBorderTerritories(territoriesList[20]);
    territoriesList[21].addBorderTerritories(territoriesList[23]);
    territoriesList[21].addBorderTerritories(territoriesList[24]);
    territoriesList[21].addBorderTerritories(territoriesList[31]);
    territoriesList[21].addBorderTerritories(territoriesList[32]);
    //GB
    territoriesList[22].addBorderTerritories(territoriesList[19]);
    territoriesList[22].addBorderTerritories(territoriesList[20]);
    territoriesList[22].addBorderTerritories(territoriesList[23]);
    territoriesList[22].addBorderTerritories(territoriesList[25]);
    //Neuro
    territoriesList[23].addBorderTerritories(territoriesList[20]);
    territoriesList[23].addBorderTerritories(territoriesList[21]);
    territoriesList[23].addBorderTerritories(territoriesList[22]);
    territoriesList[23].addBorderTerritories(territoriesList[24]);
    territoriesList[23].addBorderTerritories(territoriesList[25]);
    //Seuro
    territoriesList[24].addBorderTerritories(territoriesList[13]);
    territoriesList[24].addBorderTerritories(territoriesList[14]);
    territoriesList[24].addBorderTerritories(territoriesList[21]);
    territoriesList[24].addBorderTerritories(territoriesList[23]);
    territoriesList[24].addBorderTerritories(territoriesList[25]);
    //Weuro
    territoriesList[25].addBorderTerritories(territoriesList[13]);
    territoriesList[25].addBorderTerritories(territoriesList[22]);
    territoriesList[25].addBorderTerritories(territoriesList[23]);
    territoriesList[25].addBorderTerritories(territoriesList[24]);
    //Indonesia
    territoriesList[26].addBorderTerritories(territoriesList[27]);
    territoriesList[26].addBorderTerritories(territoriesList[28]);
    territoriesList[26].addBorderTerritories(territoriesList[34]);
    territoriesList[26].addBorderTerritories(territoriesList[29]);
    //newG
    territoriesList[27].addBorderTerritories(territoriesList[26]);
    territoriesList[27].addBorderTerritories(territoriesList[28]);
    territoriesList[27].addBorderTerritories(territoriesList[29]);
    //Waustrialia
    territoriesList[28].addBorderTerritories(territoriesList[29]);
    territoriesList[28].addBorderTerritories(territoriesList[26]);
    //Eaustralia
    territoriesList[29].addBorderTerritories(territoriesList[27]);
    territoriesList[29].addBorderTerritories(territoriesList[28]);
    territoriesList[29].addBorderTerritories(territoriesList[30]);
    //NewZealand
    territoriesList[30].addBorderTerritories(territoriesList[29]);
    //middleEast
    territoriesList[31].addBorderTerritories(territoriesList[14]);
    territoriesList[31].addBorderTerritories(territoriesList[15]);
    territoriesList[31].addBorderTerritories(territoriesList[24]);
    territoriesList[31].addBorderTerritories(territoriesList[21]);
    territoriesList[31].addBorderTerritories(territoriesList[32]);
    territoriesList[31].addBorderTerritories(territoriesList[35]);
    //afghan
    territoriesList[32].addBorderTerritories(territoriesList[21]);
    territoriesList[32].addBorderTerritories(territoriesList[34]);
    territoriesList[32].addBorderTerritories(territoriesList[35]);
    territoriesList[32].addBorderTerritories(territoriesList[31]);
    //jap
    territoriesList[33].addBorderTerritories(territoriesList[36]);
    territoriesList[33].addBorderTerritories(territoriesList[0]);
    //china
    territoriesList[34].addBorderTerritories(territoriesList[26]);
    territoriesList[34].addBorderTerritories(territoriesList[32]);
    territoriesList[34].addBorderTerritories(territoriesList[35]);
    territoriesList[34].addBorderTerritories(territoriesList[36]);
    //India
    territoriesList[35].addBorderTerritories(territoriesList[31]);
    territoriesList[35].addBorderTerritories(territoriesList[32]);
    territoriesList[35].addBorderTerritories(territoriesList[34]);
    //Mongolia
    territoriesList[36].addBorderTerritories(territoriesList[34]);
    territoriesList[36].addBorderTerritories(territoriesList[33]);


  }
  /**
   * Method to return the Territory list in the Class
   * @return territoriesList
   */
  public Territory[] getTerritoriesList() {
    return territoriesList;
  }

  /**
   * Method to return the Continent list in the Class
   * @return territoriesList
   */
  public ArrayList<Continent> getContinentList() {
    return continentsList;
  }


}

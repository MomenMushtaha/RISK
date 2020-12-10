package Objects;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Board Class: This class creates the Territories, Continents, Cards, Deck, and Hands
 * @author Momin Mushtaha
 */
public class Board implements Serializable {
  public final Objects.Deck deck;
  public Territory[] territoriesList;
  private ArrayList<Continent> continentsList;


  /**
   * Constructor for board class
   */
  public Board(File xml) {
    setContinentsandTerritories(xml);
    addBorderTerritories(xml);
    this.deck = new Deck(territoriesList);
  }


  /**
   * Creates the continents and territories for the game and assigns the territories to each Continent
   */
  private void setContinentsandTerritories(File xml) {
    try {
      continentsList = new ArrayList<>();
      String attribute1;
      String attribute2;
      ArrayList<Territory> tempTerritores = new ArrayList<>();
      DocumentBuilderFactory docBldrFctry = DocumentBuilderFactory.newInstance();
      DocumentBuilder xmlBldr = docBldrFctry.newDocumentBuilder();
      Document xmlFile = xmlBldr.parse(xml);
      xmlFile.getDocumentElement().normalize();
      NodeList mList = xmlFile.getElementsByTagName("Map");
      Node xmlMap = mList.item(0);
      if (xmlMap.getNodeType() == Node.ELEMENT_NODE) {
        Element mapElement = (Element) xmlMap;
        //figures out the size of the map
        territoriesList = new Territory[Integer.parseInt(mapElement.getAttribute("territories"))];
        NodeList cList = mapElement.getElementsByTagName("Continent");
        for (int temp = 0; temp < cList.getLength(); temp++) {
          Node nodeXml = cList.item(temp);
          if (nodeXml.getNodeType() == Node.ELEMENT_NODE) {
            Element nodeElement = (Element) nodeXml;
            NodeList tList = nodeElement.getElementsByTagName("Territory");
            for (int l = 0; l < tList.getLength(); l++) {
              Node nodeXmlT = tList.item(l);
              if (nodeXmlT.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement2 = (Element) nodeXmlT;
                attribute1 = nodeElement2.getAttribute("name");
                attribute2 = nodeElement2.getAttribute("number");
                //figures out the name and the number of the territory
                territoriesList[Integer.parseInt(attribute2)] = new Territory(attribute1);
                tempTerritores.add(territoriesList[Integer.parseInt(attribute2)]);
              }
            }
            attribute1 = nodeElement.getAttribute("name");
            attribute2 = nodeElement.getAttribute("bonus");
            //figures out the name and the number of bonus troops of the continent
            Continent con = new Continent(attribute1,Integer.parseInt(attribute2),tempTerritores);
            continentsList.add(con);
            /*
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println(con.getName());
            System.out.println("######################################################");
            System.out.println("######################################################");
            for(int r =0;r< continentsList.size();r++)
            {
              System.out.println(continentsList.get(r));
            }*/
            tempTerritores.clear();
          }}}} catch (Exception e) {
      e.printStackTrace();
    }
  }

/**
sets the bordering territories for each territory
 */
  private void addBorderTerritories(File xml)
  {
  try{
    String attribute1;
    String attribute2;
    DocumentBuilderFactory docBldrFctry = DocumentBuilderFactory.newInstance();
    DocumentBuilder xmlBldr = docBldrFctry.newDocumentBuilder();
    Document xmlFile = xmlBldr.parse(xml);
    xmlFile.getDocumentElement().normalize();
    System.out.println("parsing now!");
    NodeList pTList = xmlFile.getElementsByTagName("Territory");
    for (int p = 0; p < pTList.getLength(); p++) {
      Node nodeXmlPT = pTList.item(p);
      if (nodeXmlPT.getNodeType() == Node.ELEMENT_NODE) {
        Element nodeElement2 = (Element) nodeXmlPT;
        attribute1 = nodeElement2.getAttribute("number");
        NodeList pTBList = nodeElement2.getElementsByTagName("bordering");
        for (int b =0;b < pTBList.getLength();b++) {
          Node nodeXmlPTB = pTBList.item(b);
          if (nodeXmlPTB.getNodeType() == Node.ELEMENT_NODE) {
            Element nodeElement3 = (Element) nodeXmlPTB;
            attribute2 = nodeElement3.getTextContent();
            territoriesList[Integer.parseInt(attribute1)].addBorderTerritories(territoriesList[Integer.parseInt(attribute2)]);
  }
        }
      }
    }
  } catch (Exception e) {
    e.printStackTrace();}
  }

  /**
   * Method to return the Territory list in the Class
   *
   * @return territoriesList
   */
  public Territory[] getTerritoriesList() {
    return territoriesList;
  }


  /**
   * Method to return the Continent list in the Class
   *
   * @return continentsList
   */
  public ArrayList<Continent> getContinentList() {
    return continentsList;
  }
}

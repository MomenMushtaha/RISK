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
  public Boolean valid;
  public Territory[] territoriesList;
  private ArrayList<Continent> continentsList;


  /**
   * Constructor for board class
   */
  public Board(File xml) {
    valid = setContinentsandTerritories(xml);
    valid = addBorderTerritories(xml);
    this.deck = new Deck(territoriesList);
  }


  /**
   * Creates the continents and territories for the game and assigns the territories to each Continent
   */
  private boolean setContinentsandTerritories(File xml) {
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
      if (xmlMap == null)
      {
        System.out.println("this is an invalid map, failed to find map element");
        return false;
      }
      else{
      if (xmlMap.getNodeType() == Node.ELEMENT_NODE) {
        Element mapElement = (Element) xmlMap;
        //figures out the size of the map
        if (mapElement.getAttribute("territories") == null)
        {
          System.out.println("this is an invalid map, failed to find map attributes");
          return false;
        }
        else{
        territoriesList = new Territory[Integer.parseInt(mapElement.getAttribute("territories"))];
        NodeList cList = mapElement.getElementsByTagName("Continent");
        for (int temp = 0; temp < cList.getLength(); temp++) {
          Node nodeXml = cList.item(temp);
          if (nodeXml == null)
          {
            System.out.println("this is an invalid map, failed to find Continent element");
            return false;
          }
          else{
          if (nodeXml.getNodeType() == Node.ELEMENT_NODE) {
            Element nodeElement = (Element) nodeXml;
            if ( nodeElement == null)
            {
              System.out.println("this is an invalid map, failed to find Continent attributes");
              return false;
            }
            else{
            NodeList tList = nodeElement.getElementsByTagName("Territory");
            for (int l = 0; l < tList.getLength(); l++) {
              Node nodeXmlT = tList.item(l);
              if ( nodeXmlT == null)
              {
                System.out.println("this is an invalid map, failed to find Territory element");
                return false;
              }
              else{
              if (nodeXmlT.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement2 = (Element) nodeXmlT;
                if (nodeElement2 == null)
                {
                  System.out.println("this is an invalid map, failed to find territory attributes");
                  return false;
                }
                else{
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
            tempTerritores.clear();
          }}}}}}}}}} catch (Exception e) {
      System.out.println("INVALID MAP");
      return false;
    }
    return true;
  }

/**
sets the bordering territories for each territory
 */
  private boolean addBorderTerritories(File xml)
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
        if ( pTBList == null)
        {
          System.out.println("this is an invalid map, failed to find any bordering territory for each territory element");
          return false;
        }else{
        for (int b =0;b < pTBList.getLength();b++) {
          Node nodeXmlPTB = pTBList.item(b);
          if (nodeXmlPTB.getNodeType() == Node.ELEMENT_NODE) {
            Element nodeElement3 = (Element) nodeXmlPTB;
            attribute2 = nodeElement3.getTextContent();
            if (Integer.parseInt(attribute2) >= territoriesList.length)
            {
              System.out.println("this is an invalid map, failed to find any bordering territory is out of bound");
              return false;
            }else{
            territoriesList[Integer.parseInt(attribute1)].addBorderTerritories(territoriesList[Integer.parseInt(attribute2)]);
  }}}}}}} catch (Exception e) {
    System.out.println("INVALID MAP");
    return false;
  }
  return true;}

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

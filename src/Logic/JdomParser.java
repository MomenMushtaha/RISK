package Logic;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Objects.Territory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class JdomParser {

    public void parse(File xml)
    {
        try {
            DocumentBuilderFactory docBldrFctry = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlBldr = docBldrFctry.newDocumentBuilder();
            Document xmlFile = xmlBldr.parse(xml);
            xmlFile.getDocumentElement().normalize();
            System.out.println("parsing now!");
            System.out.println();
            System.out.println();
            NodeList continentList = xmlFile.getElementsByTagName("Continent");
            for (int temp = 0; temp < continentList.getLength(); temp++) {
                Node nodeXml = continentList.item(temp);
                if (nodeXml.getNodeType() == Node.ELEMENT_NODE) {
                    Element nodeElement = (Element) nodeXml;
                    System.out.println("Continent : "
                            + nodeElement.getAttribute("name"));
                    System.out.println("#####################################");
                    NodeList territoryList = nodeElement.getElementsByTagName("Territory");
                    for (int l = 0; l < territoryList.getLength(); l++) {
                        Node nodeXmlT = territoryList.item(l);
                    if (nodeXmlT.getNodeType() == Node.ELEMENT_NODE) {
                            Element nodeElement2 = (Element) nodeXmlT;
                            System.out.println("Territory #" + nodeElement2.getAttribute("number")+ " : "
                                    + nodeElement2.getAttribute("name"));
                        NodeList borderList = nodeElement.getChildNodes();
                        for (int b = 0; b < borderList.getLength(); b++) {
                            Node nodeXmlTb = borderList.item(b);
                            if (nodeXmlTb.getNodeType() == Node.ELEMENT_NODE) {
                                Element nodeElement3 = (Element) nodeXmlTb;
                                System.out.println("bordering : "
                                        + nodeElement3.getTextContent());
                            }
                        }
                    System.out.println();
                    System.out.println();
                }
            }
        }}} catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JdomParser p = new JdomParser();
        p.parse(new File("Continents.xml"));
    }
    }
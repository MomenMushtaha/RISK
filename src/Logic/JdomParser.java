package Logic;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
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
            NodeList xmlList = xmlFile.getElementsByTagName("Continent");
            for (int temp = 0; temp < xmlList.getLength(); temp++) {
                Node nodeXml = xmlList.item(temp);
                if (nodeXml.getNodeType() == Node.ELEMENT_NODE) {
                    Element nodeElement = (Element) nodeXml;
                    System.out.println("Continent : "
                            + nodeElement.getAttribute("name"));
                    System.out.println("#####################################");
                    for (int l = 0;l<nodeElement.getElementsByTagName("Territory").getLength();l++)
                    System.out.println("Territory : "
                            + nodeElement.getElementsByTagName("Territory").item(l).getTextContent());
                }
                System.out.println();
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
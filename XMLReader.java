import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	public static void main(String[] args) {
		
		//creating a Document Builder Factory to build a Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			
			//creating a Document Builder with our Document Builder Factory
			DocumentBuilder builder = factory.newDocumentBuilder();
			//In turn, I create a Document Object with the builder
			Document doc = builder.parse("test.xml");
			
			//get elements by the type of tag
			NodeList reference = doc.getElementsByTagName("Reference");
			//System.out.println(reference.getLength());
			
			//iterate through the list of elements
			for (int i=0; i<reference.getLength(); i++) {
				Node r = reference.item(i);
				if (r.getNodeType() == Node.ELEMENT_NODE) {
					
					//cast the node as an Element object
					Element el = (Element)r;
					//System.out.println(rr.getAttribute("RefCode"));
					
					//checking for reference codes that we wish to capture
					if(el.getAttribute("RefCode").equals("MWB") 
							|| el.getAttribute("RefCode").equals("TRV") 
							|| el.getAttribute("RefCode").equals("CAR")) {
						
							//getting child nodes from the reference elements
							NodeList textList = el.getChildNodes();
							
							for (int j=0; j<textList.getLength(); j++) {
								//Getting the reference text elements
								Node t = textList.item(j);
								t.getNodeType();
								
								if (t.getNodeType() == Node.ELEMENT_NODE) {
									Element text = (Element) t;
									//printing the text to the console
									System.out.println("RefCode: " + el.getAttribute("RefCode") + " - " + text.getTextContent());
								}
							}
						
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

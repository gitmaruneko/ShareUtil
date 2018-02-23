import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

public class TXTToXML {
    BufferedReader in;
    StreamResult out;
    Document xmldoc;
    Element root;

    public static void main (String args[]) {
        String txtFilePath = args[0];
        String xmllFilePath = args[1];
        new TXTToXML().convertTXTToXML(txtFilePath, xmllFilePath);
    }

    private void convertTXTToXML (String txtFilePath, String xmllFilePath) {
        try{
            in = new BufferedReader(new FileReader(txtFilePath));
            out = new StreamResult(xmllFilePath);
            initXML();
            String str;
            while ((str = in.readLine()) != null) {
                process(str);
            }
            in.close();
            writeXML();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    private void initXML() throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
        xmldoc = impl.createDocument(null, "ITEGRATIONTEST", null);
        root = xmldoc.getDocumentElement();
    }

    private void process(String s) {
        String [] parseString = s.split("\\[");
        String parseResult = parseString[1];
        String [] elementItem = parseResult.split("\\] ");
        Element e0 = xmldoc.createElement("ITEM");
        Element e1 = xmldoc.createElement("RESULT");
        Node n1 = xmldoc.createTextNode(elementItem[0]);
        e1.appendChild(n1);

        Element e2 = xmldoc.createElement("TESTCASE");
        Node  n2 = xmldoc.createTextNode(elementItem[1]);
        e2.appendChild(n2);
        e0.appendChild(e1);
        e0.appendChild(e2);
        root.appendChild(e0);
    }

    private void writeXML() throws TransformerConfigurationException, TransformerException {
        DOMSource domSource = new DOMSource(xmldoc);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, out);
    }
}
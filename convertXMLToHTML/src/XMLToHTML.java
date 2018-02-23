import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLToHTML {

    public static void main(String args[]) throws IOException {
        String xmlFilePath = args[0];
        String htmlFilePath = args[1];
        convertXMLToHTML(xmlFilePath, htmlFilePath);
    }

    public static void convertXMLToHTML(String xmlFilePath, String htmlFilePath) {
        StringWriter sw = new StringWriter();
        Source xslt = new StreamSource("xsl file path");

        try {
            Source xml = new StreamSource(new File(xmlFilePath));
            FileWriter fw = new FileWriter(htmlFilePath);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trasform = tFactory.newTransformer(xslt);
            trasform.transform(xml, new StreamResult(sw));
            fw.write(sw.toString());
            fw.close();
            System.out.println("integrationTest.html generated successfully!");
        } catch (IOException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
}

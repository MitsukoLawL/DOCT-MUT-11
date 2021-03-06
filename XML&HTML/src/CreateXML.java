import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by Salah on 04/03/2016.
 */
public class CreateXML {

    public static void main(String[] args) {
        /**
         * Liste des argumments necessaire
         *
         * 0 l'operateur de mutation appliqué
         * 1 le selecteur appliqué
         * 2 repértoire destination
         * 3 nom fichier txt
         *
         * */
        String txt = fileReader(args[2] + args[0]+"-"+args[1]+".txt");
        try {
            Create(args[0], args[1],args[2], txt);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        //TODO chemin du html

    }

    public static String fileReader(String nomfichier) {

        String chaine="";
        String fichier =nomfichier;

        //lecture du fichier texte
        try{
            InputStream ips=new FileInputStream(fichier);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null){
                chaine+=ligne+"\n";
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return chaine;
    }

    private static void Create(String op, String select, String path, String txt) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        String fileName = op +"-"+ select +".xml ";

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("testsuite");
        doc.appendChild(rootElement);

        Element nElem = doc.createElement("Mutation");
        nElem.setAttribute("operateur", op );
        nElem.setAttribute("selecteur", select );
        nElem.setAttribute("errors", "1");
        Element childE = doc.createElement("testcase");
        Element childR = doc.createElement("compilation_error");
        childR.setTextContent("compilation error");
        childE.appendChild(childR);
        nElem.appendChild(childE);
        Element txtE = doc.createElement("diffs");
        txtE.setTextContent(txt);
        nElem.appendChild(txtE);
        rootElement.appendChild(nElem);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        Writer output = null;
        output = new BufferedWriter(new FileWriter(path +fileName));
        String xmlOutput = result.getWriter().toString();
        output.write(xmlOutput);
        output.close();
        System.out.println("merge complete");




    }
    /*<Mutation operateur="op12" selecteur="select23">
    <testcase classname="org.mutation11.maven.transformation.MutationTesterTest" name="testMutationTester" time="2.171">
    <failure type="CompilationFail">java.lang.AssertionError: null
    at org.junit.Assert.fail(Assert.java:86)
    at org.junit.Assert.fail(Assert.java:95)
    at org.mutation11.maven.transformation.MutationTesterTest.testMutationTester(MutationTesterTest.java:68)
    </failure>
    </testcase>
    </Mutation>*/
}

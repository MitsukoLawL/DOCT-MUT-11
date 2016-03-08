/**
 * Created by Salah on 25/02/2016.
 */

//import org.jdom.*;
//import org.jdom.filter.*;
//import org.jdom.input.*;
//import org.jdom.output.Format;
//import org.jdom.output.XMLOutputter;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MergeXML {

    public static void main(String[] args) {
        /**
         * Liste des argumments necessaire
         * 0 dossier contenant les .xml
         * 1 l'operateur de mutation appliqué
         * 2 le selecteur appliqué
         * 3 repértoire destination
         *
         * */

        try {
            Merge(getAllXML(args[0], ".\\.xml" ), args[0], args[1], args[2], args[3]);
            //TODO chimin du html
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }



    }

    public static List<String> getAllXML(String path, String regx ) {


        Pattern p = Pattern.compile(regx);
        String[] s = new File(path).list();
        List<String> listeFichiers = new ArrayList<>();
        for (String value : s) {
            Matcher m = p.matcher(value);
            if (m.find()) {
                listeFichiers.add(value);
            }
        }

        return listeFichiers;
    }

    public static String Merge(List<String> allXML, String path, String op, String select, String resultPath ) throws TransformerException, IOException, SAXException, ParserConfigurationException {
        String fileName = resultPath + op +"-"+select +".xml ";
        //System.out.println(allXML.toString());
        String fileresult = allXML.get(0);
        //System.out.println(fileresult);
        allXML.remove(0);
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setIgnoringComments(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(new File(path + fileresult));
        //System.out.println(doc.toString());
        NodeList nodes = doc.getElementsByTagName("testcase");
        //TODO
        int failure = Integer.parseInt(nodes.item(0).getParentNode().getAttributes().getNamedItem("failures").getNodeValue());
        //int failure = Integer.parseInt(((Node) doc.getElementsByTagName("testsuite"))..getAttributes().getNamedItem("failures").getNodeValue());

        for(String tmp : allXML) {
            Document doc1 = builder.parse(new File(path + tmp));
            //TODO
            //failure = Integer.parseInt(((Node) doc.getElementsByTagName("testsuite")).getAttributes().getNamedItem("failures").getNodeValue());
            NodeList nodes1 = doc1.getElementsByTagName("testcase");
            failure += Integer.parseInt(nodes1.item(0).getParentNode().getAttributes().getNamedItem("failures").getNodeValue());
            for (int i = 0; i < nodes1.getLength(); i = i + 1) {

                Node n = doc.importNode(nodes1.item(i), true);
                nodes.item(i).getParentNode().appendChild(n);

            }
        }

        Element nElem = doc.createElement("Mutation");
        nElem.setAttribute("operateur", op );
        nElem.setAttribute("selecteur", select );
        nElem.setAttribute("failures",  ""+ failure);
        nodes.item(0).getParentNode().insertBefore(nElem, nodes.item(0));

        for(int i = 0; i < nodes.getLength(); i++){
            nElem.appendChild(nodes.item(i));
        }


        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        Writer output = null;
        output = new BufferedWriter(new FileWriter(fileName));
        String xmlOutput = result.getWriter().toString();
        output.write(xmlOutput);
        output.close();
        System.out.println("merge complete");
        return path + fileresult;
    }

    public static void traitement(String xmlFile, String resultPath, String xslFile ) {
        try
        {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Source xmlDoc = new StreamSource(xmlFile);
            Source xslDoc = new StreamSource(xslFile);

            String outputFileName = resultPath;
            OutputStream htmlFile = new FileOutputStream(outputFileName);

            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}

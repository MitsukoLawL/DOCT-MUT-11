/**
 * Created by Salah on 12/02/2016.
 *
 * XML to HTML whit XSL
 */
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class toHTML {

    public static void main(String[] args)
    {
        Traitement(getAllXML(args[0], ".\\.xml" ),args[0], "./XML&HTML/test.xsl");
    }



    public static void Traitement(List<String> ListeFicher,String path, String xslFile ) {
        try
        {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Source xmlDoc = new StreamSource(path + ListeFicher.get(0));
            Source xslDoc = new StreamSource(xslFile);

            String outputFileName = "./XML&HTML/test.html";
            OutputStream htmlFile = new FileOutputStream(outputFileName);

            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<String> getAllXML(String path, String regx ) {


        Pattern p = Pattern.compile(regx);
        String[] s = new File(path).list();
        List<String> listeFichiers = new ArrayList<>();
        for (String value : s) {
            System.out.println(value);
            Matcher m = p.matcher(value);
            if (m.find()) {
                System.out.println(value);
                listeFichiers.add(value);
            }
        }

        return listeFichiers;
    }
}
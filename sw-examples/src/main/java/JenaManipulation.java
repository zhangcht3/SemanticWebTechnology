import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lenovo on 2015/9/5.
 */
public class JenaManipulation {
    public static void main(String[] args) throws IOException {

        String studentUrl = "http://example.org/ChuangtaoZhang";
        String foafUrl = "http://www.xmlns.com/foaf/spec/";
        String xmlPath = "src/main/resources/result.xml";
        String ttlPath = "src/main/resources/result.ttl";
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
        String inputFileName = "src/main/resources/example.ttl";
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }

        // read the TURTLE file
        model.read(in, null, "TURTLE");

        Property knowsPredicate = model.createProperty(foafUrl + "knows");

        Resource friend = (Resource) model.getResource(studentUrl).getProperty(knowsPredicate).getObject();

        //add nick name
        Property friendNickname = model.createProperty(foafUrl + "nick");
        friend.addProperty(friendNickname, "Boss");

        //show in RDF
        System.out.println("# -- Result(RDF/XML)");
        model.write(System.out);

        //show in TURTLE
        System.out.println("# -- Result(TURTLE)");
        model.write(System.out, "TURTLE");

        // write to xml file
        FileOutputStream fileStream = new FileOutputStream(xmlPath);
        model.write(fileStream);
        fileStream.close();

        // write to turtle file
        fileStream = new FileOutputStream(ttlPath);
        model.write(fileStream, "TURTLE");
        fileStream.close();

    }
}

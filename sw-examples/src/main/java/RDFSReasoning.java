import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RDFSReasoning {
    public static void main(String[] args) throws IOException {

        String MuseionUrl = "http://example.org/inst/Museion";
        String ChickenHutUrl = "http://example.org/inst/ChickenHut";

        // create an empty model
        Model exampleModel = ModelFactory.createDefaultModel();
        Model resultModel = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
        String inputFileName = "src/main/resources/tourism.ttl";
        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }

        // read the turtle file
        exampleModel.read(in, null, "TURTLE");

        InfModel inf = ModelFactory.createRDFSModel(exampleModel);

        /*Since I don't know how to use sparql to get the result,
            I use an iterator to check each triple.*/
        StmtIterator iter = inf.listStatements();

        while (iter.hasNext()) {
            Statement stmt      = iter.nextStatement();  // get next statement
            Resource  subject   = stmt.getSubject();     // get the subject
            RDFNode object    = stmt.getObject();      // get the object
            if (subject.toString().equals(MuseionUrl) || subject.toString().equals(ChickenHutUrl)
                    || object.toString().equals(MuseionUrl) || object.toString().equals(ChickenHutUrl)) {
                resultModel.add(stmt);
            }
        }

        String ttlPath = "src/main/resources/new-triples.ttl";

        FileOutputStream fileStream = new FileOutputStream(ttlPath);
        resultModel.write(fileStream, "TURTLE");
        fileStream.close();
    }
}

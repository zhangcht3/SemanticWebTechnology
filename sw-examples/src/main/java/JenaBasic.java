import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by lenovo on 2015/9/5.
 */
public class JenaBasic {
    public static void main(String[] args) {
        Model m = ModelFactory.createDefaultModel();
        String studentSubject = "http://example.org/resource/chuangtaozhang";
        String studentPredicate = "http://example.org/property/said";
        Resource s = m.createResource(studentSubject);
        Property p = m.createProperty(studentPredicate);
        m.add(s, p, "Hello World!");
        System.out.println("# -- Result(RDF/XML)");
        m.write(System.out);
        System.out.println("# -- Result(TURTLE)");
        m.write( System.out, "TURTLE" );
    }
}

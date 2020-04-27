package id.semantics.carml.carml;

import com.taxonic.carml.engine.RmlMapper;
import com.taxonic.carml.logical_source_resolver.CsvResolver;
import com.taxonic.carml.model.TriplesMap;
import com.taxonic.carml.util.RmlMappingLoader;
import com.taxonic.carml.vocab.Rdf;
import id.semantics.carml.helper.CarmlFunctions;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import java.io.*;
import java.util.Set;

public class CSVParser {

    /**
     * transform CSV file with caRML and RML mappings.
     *
     * @param csvFileName
     * @param rmlFile
     * @param outputFile
     * @return
     * @throws IOException
     */
    public static void parse(String csvFileName, String rmlFile, String outputFile) throws IOException {

        // load RML file and all supporting functions

        InputStream is = new FileInputStream(rmlFile);
        Set<TriplesMap> mapping = RmlMappingLoader.build().load(RDFFormat.TURTLE, is);
        RmlMapper mapper = RmlMapper.newBuilder().setLogicalSourceResolver(Rdf.Ql.Csv, new CsvResolver())
                .addFunctions(new CarmlFunctions()).build();

        // load input file and convert it to RDF
        InputStream instances = new FileInputStream(csvFileName);
        mapper.bindInputStream(instances);

        // write it out to an turtle file
        Model sesameModel = mapper.map(mapping);

        sesameModel.setNamespace("core", "http://w3id.org/sepses/log/core#");
        sesameModel.setNamespace("ex", "http://w3id.org/sepses/slogert/");
        sesameModel.setNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        sesameModel.setNamespace("xsd", "http://www.w3.org/2001/XMLSchema#");
        sesameModel.setNamespace("owl", "http://www.w3.org/2002/07/owl#");
        sesameModel.setNamespace("foaf", "http://xmlns.com/foaf/0.1/");

        is.close();
        instances.close();

        // write to file
        OutputStream tempOutput = new FileOutputStream(outputFile);
        Rio.write(sesameModel, tempOutput, RDFFormat.TURTLE); // write mapping
        sesameModel.clear();
        tempOutput.flush();
        tempOutput.close();
    }
}

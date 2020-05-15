package id.semantics.carml;

import id.semantics.carml.carml.CSVParser;
import id.semantics.carml.carml.JSONParser;
import id.semantics.carml.carml.XMLParser;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.liu.ida.rdfstar.tools.conversion.RDF2RDFStar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        String finalFile = "./output/slogert-bkg.ttl";
        Model model = ModelFactory.createDefaultModel();

        String inputFile = "./input/slogertV2/user.csv";
        String outputFile = "./output/slogert-user.ttl";
        String rmlFile = "./rml/slogertV2/user.csv.rml";
        String rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);
        RDFDataMgr.read(model, new FileInputStream(outputFile), Lang.TURTLE);

        inputFile = "./input/slogertV2/network.csv";
        outputFile = "./output/slogert-network.ttl";
        rmlFile = "./rml/slogertV2/network.csv.rml";
        rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);
        RDFDataMgr.read(model, new FileInputStream(outputFile), Lang.TURTLE);

        inputFile = "./input/slogertV2/device.csv";
        outputFile = "./output/slogert-device.ttl";
        rmlFile = "./rml/slogertV2/device.csv.rml";
        rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);
        RDFDataMgr.read(model, new FileInputStream(outputFile), Lang.TURTLE);

        inputFile = "./input/slogertV2/service.csv";
        outputFile = "./output/slogert-service.ttl";
        rmlFile = "./rml/slogertV2/service.csv.rml";
        rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);
        RDFDataMgr.read(model, new FileInputStream(outputFile), Lang.TURTLE);

        inputFile = "./input/slogertV2/person.csv";
        outputFile = "./output/slogert-person.ttl";
        rmlFile = "./rml/slogertV2/person.csv.rml";
        rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);
        RDFDataMgr.read(model, new FileInputStream(outputFile), Lang.TURTLE);

        RDFDataMgr.write(new FileOutputStream(finalFile), model, Lang.TURTLE);
        model.close();


        // transform to RDF*
        RDF2RDFStar rdf2RDFStar = new RDF2RDFStar();
        rdf2RDFStar.convert(finalFile, new FileOutputStream(finalFile + "s"));



    }

    public static void executeConversion(String inputFile, String rmlType, String rmlFile, String outputFile)
            throws IOException {

        log.info("Main process started");

        long start = System.currentTimeMillis();

        conversion(inputFile, rmlType, rmlFile, outputFile);

        log.info("Main process is done in '" + (System.currentTimeMillis() - start) / 1000 + "' seconds");
    }

    public static void conversion(String inputFile, String rmlType, String rmlFile, String outputFile)
            throws IOException {
        File input = new File(inputFile);
        File output = new File(outputFile);

        if (rmlType.equalsIgnoreCase("csv")) {
            CSVParser.parse(inputFile, rmlFile, outputFile);
        } else if (rmlType.equalsIgnoreCase("json")) {
            JSONParser.parse(inputFile, rmlFile, outputFile);
        } else if (rmlType.equalsIgnoreCase("xml")) {
            XMLParser.parse(inputFile, rmlFile, outputFile);
        } else {
            log.warn(rmlType + " is not yet supported");
        }
    }
}

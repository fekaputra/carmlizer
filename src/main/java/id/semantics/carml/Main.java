package id.semantics.carml;

import id.semantics.carml.carml.CSVParser;
import id.semantics.carml.carml.JSONParser;
import id.semantics.carml.carml.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.liu.ida.rdfstar.tools.conversion.RDF2RDFStar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        String inputFile = "./input/slogert/account.csv";
        String outputFile = "./output/slogert-account.ttl";
        String rmlFile = "./rml/slogert/account.csv.rml";
        String rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);

        inputFile = "./input/slogert/client.csv";
        outputFile = "./output/slogert-client.ttl";
        rmlFile = "./rml/slogert/client.csv.rml";
        rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);

        inputFile = "./input/slogert/network.csv";
        outputFile = "./output/slogert-network.ttl";
        rmlFile = "./rml/slogert/network.csv.rml";
        rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);

        inputFile = "./input/slogert/person.csv";
        outputFile = "./output/slogert-person.ttl";
        rmlFile = "./rml/slogert/person.csv.rml";
        rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);

        inputFile = "./input/slogert/service.csv";
        outputFile = "./output/slogert-service.ttl";
        rmlFile = "./rml/slogert/service.csv.rml";
        rmlType = "csv";
        executeConversion(inputFile, rmlType, rmlFile, outputFile);

    }

    public static void executeConversion(String inputFile, String rmlType, String rmlFile, String outputFile)
            throws IOException {

        log.info("Main process started");

        long start = System.currentTimeMillis();

        conversion(inputFile, rmlType, rmlFile, outputFile);

        // transform to RDF*
        RDF2RDFStar rdf2RDFStar = new RDF2RDFStar();
        rdf2RDFStar.convert(outputFile, new FileOutputStream(outputFile + "star.ttl"));

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

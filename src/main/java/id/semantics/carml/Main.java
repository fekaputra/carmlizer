package id.semantics.carml;

import id.semantics.carml.carml.CSVParser;
import id.semantics.carml.carml.JSONParser;
import id.semantics.carml.carml.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        log.info("Main process started");

        long start = System.currentTimeMillis();

        String inputFile = "./input/aml_vdma.aml";
        String outputFile = "./output/aml_vdma.ttl";
        String rmlFile = "./rml/aml.rml";
        String rmlType = "xml";

        // start carmlizer
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

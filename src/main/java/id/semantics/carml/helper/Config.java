package id.semantics.carml.helper;

public final class Config {
    /**
     * XES Config
     */
    public static final String TRACE_NS = "http://w3id.org/shaclare/id/trace/";
    public static final String EVENT_NS = "http://w3id.org/shaclare/id/event/";
    public static final String XES_NS = "http://semantics.id/ns/xes#";
    public static final String XES_TEMP_FOLDER = "xes-temp/";
    /**
     * Carmlizer config
     */
    public static final String TEMP_FILE = "temp_";
    public static final String TEMP_FOLDER = "temp/";
    public static final String TEMP_FILE_SUFFIX = ".ttl";
    /**
     * MainXES config
     */
    public static final String INPUT_DIR = "input-dir";
    public static final String OUTPUT_DIR = "output-dir";
    public static final String RML_TYPE = "rml-type";
    public static final String FILE_LIMIT = "file-limit";
    public static final String RML_FILE = "rml-file";
    /**
     * SPARQL Endpoint config
     */
    public static final String ENDPOINT = "endpoint";
    public static final String GRAPH = "graph";
    public static final String ENDPOINT_TYPE = "endpoint-type";
    public static final String USE_AUTH = "use-auth";
    public static final String USER = "user";
    public static final String PASS = "pass";

    public enum TRIPLESTORE {
        DUMMY, TDB, VIRTUOSO
    }

}

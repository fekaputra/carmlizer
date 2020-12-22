package id.semantics.carml.helper;

import com.taxonic.carml.engine.function.FnoFunction;
import com.taxonic.carml.engine.function.FnoParam;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarmlFunctions {

    public static final String FUNCTION_NS = "http://semantics.id/ns/function#";
    public static final String PARAM_NS = "http://semantics.id/ns/parameter#";

    private static final String SECONDS = "SECONDS";
    private static final String TIME_ZONE = "Europe/Vienna";

    /**
     * Format input time to conform with the XSD datetime format.
     * Special formats: (i) "seconds" to deal with seconds since epoch;
     *
     * @param time
     * @param timeFormat
     * @return String XSDDateTime formatted datetime
     */
    @FnoFunction(FUNCTION_NS + "timeConversion") public String timeConversion(
            @FnoParam(PARAM_NS + "time") String time, @FnoParam(PARAM_NS + "timeFormat") String timeFormat) {

        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        ZoneOffset zoneOffSet = zoneId.getRules().getOffset(LocalDateTime.now());
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);

        if (timeFormat.equalsIgnoreCase(SECONDS)) {
            dateTime = LocalDateTime.ofEpochSecond(Integer.parseInt(time), 0, zoneOffSet);

        } else {

            DateTimeFormatter fromFormatter;
            try {
                fromFormatter = DateTimeFormatter.ofPattern(timeFormat);
                dateTime = LocalDateTime.parse(time, fromFormatter);
            } catch (IllegalArgumentException e) {
                // e.printStackTrace();
            } catch (DateTimeParseException e) {
                // e.printStackTrace();
            }
        }

        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    /**
     * Split by comma -> values
     *
     * @param data
     *
     * @return list of string values
     */
    @FnoFunction(FUNCTION_NS + "split") public List<String> split(@FnoParam(PARAM_NS + "data") String data) {
        return Arrays.asList(data.split("\\s*,\\s*"));
    }

    /**
     * Split by comma -> URLs
     *
     * @param data
     * @param ns
     *
     * @return list of string URLs
     */
    @FnoFunction(FUNCTION_NS + "splitToURL")
    public List<String> splitToURL(
            @FnoParam(PARAM_NS + "data") String data,
            @FnoParam(PARAM_NS + "ns") String ns) {
        List<String> values = Arrays.asList(data.split("\\s*,\\s*"));
        values = values.stream().map(value -> ns + value).collect(Collectors.toList());

        return values;
    }

}

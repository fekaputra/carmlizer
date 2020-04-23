import id.semantics.carml.helper.CarmlFunctions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class CarmlFunctionsTest {

    private static final Logger log = LoggerFactory.getLogger(CarmlFunctionsTest.class);
    private static CarmlFunctions timeHelper;

    @BeforeClass public static void setUp() throws Exception {
        timeHelper = new CarmlFunctions();
    }

    @AfterClass public static void tearDown() throws Exception {
        // do nothing
    }

    @Test public void testTimeConversionStreaming() {
        String result = timeHelper.timeConversion("2019-05-05 07:26", "yyyy-MM-dd HH:mm");
        assertEquals("2019-05-05T07:26:00", result);
    }

    @Test public void testTimeConversionMood() {
        String result = timeHelper.timeConversion("1524293946", "seconds");
        assertEquals("2018-04-21T08:59:06", result);
    }

    @Test public void testTimeConversionLocation() {
        String result = timeHelper.timeConversion("2018-05-09T23:05:37.647Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        assertEquals("2018-05-09T23:05:37", result);
    }
}

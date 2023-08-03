import org.deliveroo.CronParseRangeException;
import org.deliveroo.CronParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CronParserTest {

    @Test
    void testValidCronEveryStepInterval() throws CronParseRangeException {

        //Test for minute
        String resolvedIntervals = CronParser.parseIndividualInterval("*/15",0,59);
        Assertions.assertEquals(resolvedIntervals,"0 15 30 45 ");

        //Test for day of week
        resolvedIntervals = CronParser.parseIndividualInterval("*/1",0,6);
        Assertions.assertEquals(resolvedIntervals,"0 1 2 3 4 5 6 ");

        //Test for hour
        resolvedIntervals = CronParser.parseIndividualInterval("*/12",0,23);
        Assertions.assertEquals(resolvedIntervals,"0 12 ");

        //Test for day of month
        resolvedIntervals = CronParser.parseIndividualInterval("*/10",1,31);
        Assertions.assertEquals(resolvedIntervals,"1 11 21 31 ");

        //Test for month
        resolvedIntervals = CronParser.parseIndividualInterval("*/6",1,12);
        Assertions.assertEquals(resolvedIntervals,"1 7 ");

    }

    @Test
    void testValidWildCardInterval() throws CronParseRangeException {

        //Test for day of week
        String resolvedIntervals = CronParser.parseIndividualInterval("*",0,6);
        Assertions.assertEquals(resolvedIntervals,"0 1 2 3 4 5 6 ");

    }

    @Test
    void testInValidEveryInterval() throws CronParseRangeException {

        Boolean exceptionThrown = false;

        //Test for day of week
        try {
            String resolvedIntervals = CronParser.parseIndividualInterval("*/8",0,6);
        } catch (CronParseRangeException e) {
            Assertions.assertEquals("Invalid step for every provided. Range should be between 1 and 6, Given every step: */8",e.getMessage());
            exceptionThrown = true;

        }
        Assertions.assertTrue(exceptionThrown);
    }

    @Test
    void testInValidRangeInterval() throws CronParseRangeException {

        Boolean exceptionThrown = false;

        //Test for day of week
        try {
            String resolvedIntervals = CronParser.parseIndividualInterval("1-17",1,7);
        } catch (CronParseRangeException e) {
            Assertions.assertEquals("Invalid range provided. Range should be between 1 and 7 Given Range 1-17",e.getMessage());
            exceptionThrown = true;

        }
        Assertions.assertTrue(exceptionThrown);
    }

    @Test
    void testInValidIndividualNumber() throws CronParseRangeException {

        Boolean exceptionThrown = false;

        //Test for day of week
        try {
            String resolvedIntervals = CronParser.parseIndividualInterval("177",1,7);
        } catch (CronParseRangeException e) {
            Assertions.assertEquals("Invalid individual number provided. Range should be between 1 and 7, Given number: 177",e.getMessage());
            exceptionThrown = true;

        }
        Assertions.assertTrue(exceptionThrown);
    }

}

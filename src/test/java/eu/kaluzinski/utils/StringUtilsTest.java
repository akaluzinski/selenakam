package eu.kaluzinski.utils;

import org.junit.Test;

import java.util.Arrays;

import static eu.kaluzinski.utils.StringUtils.getNumberOfElementsWithSequence;
import static eu.kaluzinski.utils.StringUtils.isNullOrEmpty;
import static eu.kaluzinski.utils.StringUtils.extractFirstIntegerFromString;
import static org.junit.Assert.*;

/**
 * Classes containing unit tests of utility methods
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class StringUtilsTest {

    @Test
    public void nullOrEmptyTest() {
        assertTrue(isNullOrEmpty(null));
        assertTrue(isNullOrEmpty(""));
        assertFalse(isNullOrEmpty(" "));
        assertFalse(isNullOrEmpty("test"));
    }

    @Test(expected = NullPointerException.class)
    public void getNumberOfElementsWithSequenceNullSequenceTest() {
        getNumberOfElementsWithSequence(Arrays.asList("Some Value"), null);
    }

    @Test(expected = NullPointerException.class)
    public void getNumberOfElementsWithSequenceNullListTest() {
        getNumberOfElementsWithSequence(null, "value to search");
    }

    @Test
    public void getNumberOfElementsWithSequenceEmptyListEmptySequenceTest() {
        assertEquals(0, getNumberOfElementsWithSequence(Arrays.asList(), ""));
    }

    @Test
    public void getNumberOfElementsWithSequenceNoOccurrencesTest() {
        assertEquals(0, getNumberOfElementsWithSequence(Arrays.asList("Text1", "Careers"), "test"));
    }

    @Test
    public void getNumberOfElementsWithSequenceEmptyListTest() {
        assertEquals(0, getNumberOfElementsWithSequence(Arrays.asList(), "test"));
    }

    @Test
    public void getNumberOfElementsWithSequenceSingleOccurrenceTest() {
        assertEquals(1, getNumberOfElementsWithSequence(Arrays.asList("test"), "test"));
        assertEquals(1, getNumberOfElementsWithSequence(Arrays.asList("Subtest of tests", "Other message"), "test"));
    }

    @Test
    public void getNumberOfElementsWithSequenceMultipleElementsTest() {
        //Check word duplication in element
        assertEquals(2, getNumberOfElementsWithSequence(Arrays.asList("Word", "Java Java Java", "Junit", "Java Island"), "Java"));
        //Check letter cases
        assertEquals(1,
                getNumberOfElementsWithSequence(Arrays.asList("Testing Career", "Subtest of tests", "Other message"), "Test"));
        //Check ignoring null elements
        assertEquals(1,
                getNumberOfElementsWithSequence(Arrays.asList("Content Delivery Network", null, "Lombok", "Java Island"), "Java"));
    }


    @Test(expected = NullPointerException.class)
    public void extractFirstIntegerFromStringNullTest(){
        extractFirstIntegerFromString(null);
    }

    @Test
    public void extractFirstIntegerFromStringEmptyStringTest(){
        assertNull(extractFirstIntegerFromString(""));
    }

    @Test
    public void extractFirstIntegerFromStringJustNumberTest(){
        assertEquals(12, (int) extractFirstIntegerFromString("12"));
    }

    @Test
    public void extractFirstIntegerFromStringNumberWithTextTest(){
        assertEquals(12, (int) extractFirstIntegerFromString("12"));
    }

    @Test
    public void extractFirstIntegerFromStringMultipleNumbersTest(){
        assertEquals(3, (int) extractFirstIntegerFromString("3 11 12"));
    }

    @Test
    public void extractFirstIntegerFromStringNumberWrappedWithTextTest(){
        assertEquals(10, (int) extractFirstIntegerFromString("Team won with 10 points score."));
        assertEquals(-100, (int) extractFirstIntegerFromString("Value is -100."));
        assertEquals(7, (int) extractFirstIntegerFromString("A7M8"));
    }

}

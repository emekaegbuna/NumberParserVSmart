package com.example.numberparservsmart;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class NumberParserTest {

    private Map<String, Integer> countryCodes;
    private Map<String, String> prefixes;
    private NumberParser parser;

    @Before
    public void init() {
        countryCodes = new HashMap<>();
        prefixes = new HashMap<>();
        countryCodes.put("GB", 44); prefixes.put("GB", "0");
        countryCodes.put("US", 1); prefixes.put("US", "1");
        parser = new NumberParser(countryCodes, prefixes);
    }

    @Test
    public void convertNationalDialledNumberToInternationalFormat(){
        assertEquals("+442079460056", parser.parse("02079460056", "+441614960148"));
    }

    @Test
    public void returnInternationalDialledNumberForInternationalFormatInput(){
        assertEquals("+442079460056", parser.parse("+442079460056", "+441614960148"));
    }

    @Test
    public void warnUnknownCountryCodeFromInputDialledNumber(){
        assertEquals("Non existing CollingCode with input dialled number", parser.parse("+522079418573", "+441614960148"));
    }

    @Test
    public void warnInputDiealledNumberTooShort(){
        assertEquals("Invalid length of dialed number", parser.parse("208593843", "+441614960148"));
    }

    @Test
    public void warnCallingCodeStartWithZero(){
        assertEquals("Calling code cannot start with 0", parser.parse("+012079418573", "+441614960148"));
    }

    @Test
    public void warnCallingCodeNotBetweenOneAndFourDigits(){
        assertEquals("Calling code has to be between 1 and 4 digits long", parser.parse("+012342079418573", "+441614960148"));
        assertEquals("Calling code has to be between 1 and 4 digits long", parser.parse("+2079418573", "+441614960148"));
    }

    @Test
    public void warnNoMatchingPrefixAndCallingCodeInDatabase(){
        assertEquals("Unable to find matching CountryCode with Prefix of dialled number", parser.parse("62079418573", "+441614960148"));
    }
}

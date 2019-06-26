package model.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {
    private final String nullString = null;
    private final String emptyString = "";
    private final String blankString = " ";
    private final String alphabeticString = "KingKong";
    private final String alphabeticStringWithSpace = "Bat Man";
    private final String zeroString = "0";
    private final String numberGreaterZero = "1";

    @Test
    public void verifyEMPTY() {
        assertEquals("", StringUtils.EMPTY);
    }

    @Test
    public void verifyUNKOWN() {
        assertEquals("Unknown", StringUtils.UNKNOWN);
    }

    /*
     * isEmpty
     */
    @Test
    public void checkNullString_isEmpty_returnTrue() {
        assertTrue(StringUtils.isEmpty(nullString));
    }

    @Test
    public void checkBlankString_isEmpty_returnFalse() {
        assertFalse(StringUtils.isEmpty(blankString));
    }

    @Test
    public void checkEmptyString_isEmpty_returnTrue() {
        assertTrue(StringUtils.isEmpty(emptyString));
    }

    @Test
    public void checkFilledString_isEmpty_returnFalse() {
        assertFalse(StringUtils.isEmpty(alphabeticString));
    }

    /*
     * isNotEmpty
     */
    @Test
    public void checkNullString_returnFalse() {
        assertFalse(StringUtils.isNotEmpty(nullString));
    }

    @Test
    public void checkEmptyString_returnFalse() {
        assertFalse(StringUtils.isNotEmpty(emptyString));
    }

    @Test
    public void checkFilledString_returnTrue() {
        assertTrue(StringUtils.isNotEmpty(alphabeticString));
    }

    @Test
    public void checkBlackString_isNotEmptyReturnsTrue() {
        assertTrue(StringUtils.isNotEmpty(blankString));
    }

    /*
     * hasContent
     */
    @Test
    public void nullString_hasContentReturnsFalse() {
        assertFalse(StringUtils.hasContent(nullString));
    }

    @Test
    public void emptyString_hasContentReturnsFalse() {
        assertFalse(StringUtils.hasContent(emptyString));
    }

    @Test
    public void checkBlankString_hasContentReturnsFalse() {
        assertFalse(StringUtils.hasContent(blankString));
    }

    @Test
    public void checkFilledString_hasContentReturnsTrue() {
        assertTrue(StringUtils.hasContent(alphabeticString));
    }

    @Test
    public void checkFilledStringWithSpace() {
        assertTrue(StringUtils.hasContent(alphabeticStringWithSpace));
    }

    /*
     * hasContentWithNoWhiteSpaces
     */
    @Test
    public void nullString_hasContentWithNoWhiteSpaces_returnsFalse() {
        assertFalse(StringUtils.hasContentWithNoWhiteSpaces(nullString));
    }

    @Test
    public void emptyString_hasContentWithNoWhiteSpaces_returnsFalse() {
        assertFalse(StringUtils.hasContentWithNoWhiteSpaces(emptyString));
    }

    @Test
    public void checkBlankString_hasContentWithNoWhiteSpaces_returnsFalse() {
        assertFalse(StringUtils.hasContentWithNoWhiteSpaces(blankString));
    }

    @Test
    public void checkFilledString_hasContentWithNoWhiteSpaces_returnsTrue() {
        assertTrue(StringUtils.hasContentWithNoWhiteSpaces(alphabeticString));
    }

    @Test
    public void checkFilledStringWithWhiteSpace_hasContentWithNoWhiteSpaces_retrunsFalse() {
        assertFalse(StringUtils.hasContentWithNoWhiteSpaces(alphabeticStringWithSpace));
    }

    /*
     * safeEquals
     */
    @Test
    public void bothStringNull_safeEqualsFalse() {
        assertFalse(StringUtils.safeEquals(nullString, nullString));
    }

    @Test
    public void onlyStringAIsNull_safeEqualsFalse() {
        assertFalse(StringUtils.safeEquals(nullString, alphabeticString));
    }

    @Test
    public void onlyStringBIsNull_safeEqualsFalse() {
        assertFalse(StringUtils.safeEquals(alphabeticString, nullString));
    }

    @Test
    public void bothStringEmpty_safeEqualsTrue() {
        assertTrue(StringUtils.safeEquals(emptyString, emptyString));
    }

    @Test
    public void bothStringNotEqual_safeEqualsFalse() {
        assertFalse(StringUtils.safeEquals(alphabeticString, alphabeticStringWithSpace));
    }

    /*
     * intGreaterZero
     */
    @Test
    public void checkNullString_isIntegerGreaterZero_returnFalse() {
        assertFalse(StringUtils.isIntegerGreaterZero(nullString));
    }

    @Test
    public void checkEmptyString_isIntegerGreaterZero_returnFalse() {
        assertFalse(StringUtils.isIntegerGreaterZero(emptyString));
    }

    @Test
    public void checkBlankString_isIntegerGreaterZero_returnFalse() {
        assertFalse(StringUtils.isIntegerGreaterZero(blankString));
    }

    @Test
    public void checkAlphabeticString_isIntegerGreaterZero_returnFalse() {
        assertFalse(StringUtils.isIntegerGreaterZero(alphabeticString));
    }

    @Test
    public void checkAlphabeticStringWithWhiteSpace_isIntegerGreaterZero_returnFalse() {
        assertFalse(StringUtils.isIntegerGreaterZero(alphabeticStringWithSpace));
    }

    @Test
    public void checkZeroString_isIntegerGreaterZero_returnFalse() {
        assertFalse(StringUtils.isIntegerGreaterZero(zeroString));
    }

    @Test
    public void checkNumberGreaterZero_isIntegerGreaterZero_returnTrue() {
        assertTrue(StringUtils.isIntegerGreaterZero(numberGreaterZero));
    }

    /*
     * minLength
     */
    @Test
    public void checkNullString_hasMinLength_returnFalse() {
        assertFalse(StringUtils.hasMinLength(nullString, 1));
    }

    @Test
    public void checkEmptyStringAndMinLengthZero_hasMinLength_returnFalse() {
        assertFalse(StringUtils.hasMinLength(emptyString, 0));
    }

    @Test
    public void checkAlphabeticStringWithLengthEight_minLengthIsNine_hasMinLengthReturnsFalse() {
        assertFalse(StringUtils.hasMinLength(alphabeticString, 9));
    }

    @Test
    public void checkEmptyStringAndMinLengthOfMinusOne_hasMinLength_returnFalse() {
        assertFalse(StringUtils.hasMinLength(emptyString, -1));
    }

    @Test
    public void checkBlankStringWithLengthOneAndMinLengthOfOne_hasMinLength_returnTrue() {
        assertTrue(StringUtils.hasMinLength(blankString, 1));
    }

    @Test
    public void checkAlphabeticStringWithLengthEight_minLengthOfOne_hasMinLengthReturnsTrue() {
        assertTrue(StringUtils.hasMinLength(alphabeticString, 1));
    }

    /*
     * maxLength
     */
    @Test
    public void checkNullString_hasMaxLength_returnFalse() {
        assertFalse(StringUtils.hasMaxLength(nullString, 1));
    }

    @Test
    public void checkEmptyStringAndHasMaxLengthOfMinusOne_hasMaxLenght_returnFalse() {
        assertFalse(StringUtils.hasMaxLength(emptyString, -1));
    }

    @Test
    public void checkStringWithLengthSevenAndMaxLengthOfSix_hasMaxLength_returnFalse() {
        assertFalse(StringUtils.hasMaxLength(alphabeticStringWithSpace, 6));
    }

    @Test
    public void checkBlankStringWithLengthOneAndMaxLengthOfOne_hasMaxLength_returnTrue() {
        assertTrue(StringUtils.hasMaxLength(blankString, 1));
    }

    @Test
    public void checkStringWithLengthEightAndMaxLengthMaximum_hasMaxLength_returnTrue() {
        assertTrue(StringUtils.hasMaxLength(alphabeticString, Integer.MAX_VALUE));
    }

    /*
     * safeString
     */
    @Test
    public void verify_safeStringReturnsEmptyString_whenGivenNull() {
        assertEquals("", StringUtils.safeString(nullString));
    }

    @Test
    public void verify_safeStringReturnsGivenString_whenGivenStringNull() {
        assertEquals("Batman", StringUtils.safeString("Batman"));
    }

}

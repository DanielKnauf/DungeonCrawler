package model.utils;

public final class StringUtils {

    public static final String UNKNOWN = "Unknown";
    public static final String EMPTY = "";

    /**
     * @return <code>true</code> if the given {@link String} is
     * <code>non-null</code> and <code>empty</code>.
     */
    public static boolean isEmpty(String s) {
        return s != null && s.isEmpty();
    }

    /**
     * @return <code>true</code> if the given {@link String} is
     * <code>non-null</code> and <code>non-empty</code>.
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    /**
     * @return <code>true</code> if the given {@link String} has at least one
     * {@link char}.
     */
    public static boolean hasContent(String s) {
        return isNotEmpty(s) && !s.trim().isEmpty();
    }

    /**
     * @return <code>true</code> if the given {@link String} has at least one
     * {@link char} and no white spaces between characters.
     */
    public static boolean hasContentWithNoWhiteSpaces(String s) {
        return hasContent(s) && !s.contains(" ");
    }

    public static boolean safeEquals(String a, String b) {
        if (a == null || b == null)
            return false;
        else
            return a.equals(b);
    }

    public static boolean isIntegerGreaterZero(String s) {
        try {
            int intValue = Integer.valueOf(s);
            return intValue > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean hasMinLength(String s, int minLength) {
        return isNotEmpty(s) && s.length() >= minLength;
    }

    public static boolean hasMaxLength(String s, int maxLength) {
        return s != null && s.length() <= maxLength;
    }

    public static String safeString(String s) {
        return s != null ? s : EMPTY;
    }
}


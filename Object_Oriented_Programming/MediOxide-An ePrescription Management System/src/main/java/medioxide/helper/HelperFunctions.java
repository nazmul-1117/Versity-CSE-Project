package medioxide.helper;

public class HelperFunctions {
    public static int stringToInt(String s) {
        int n;
        try {
            n = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            n = 0;
        }
        return n;

    }
}

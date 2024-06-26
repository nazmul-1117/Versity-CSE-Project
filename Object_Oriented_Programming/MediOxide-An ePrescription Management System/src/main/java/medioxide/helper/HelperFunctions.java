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

    public static float stringToFloat(String s){
        float n;
        try {
            n = Float.parseFloat(s);
        }catch (NumberFormatException nx){
            n = 0.0F;
        }
        return n;
    }
}

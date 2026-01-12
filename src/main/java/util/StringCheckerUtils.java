package util;

public class StringCheckerUtils {

    private StringCheckerUtils() {}

    public static boolean isNullOrEmpty(String ...values) {
        for(String i: values)
        {
            if(i == null || i.isEmpty()) return true;
        }
        return false;
    }
}

package site.ugaeng.localhosting.util;

public class StringUtils {

    public static boolean hasText(String text) {
        if (text != null && text.length() != 0) {
            return true;
        }
        return false;
    }
}

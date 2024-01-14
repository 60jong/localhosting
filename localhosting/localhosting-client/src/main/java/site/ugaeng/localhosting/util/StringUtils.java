package site.ugaeng.localhosting.util;

public class StringUtils {

    public static boolean hasText(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        return true;
    }
}

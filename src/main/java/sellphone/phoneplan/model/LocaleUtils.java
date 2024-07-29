package sellphone.phoneplan.model;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class LocaleUtils {
    private static MessageSource messageSource;

    private LocaleUtils() {
    }

    public static void setMessageSource(MessageSource messageSource) {
        LocaleUtils.messageSource = messageSource;
    }

    public static String get(String msgKey) {
        return LocaleUtils.get(msgKey, (Object) null);
    }

    public static String get(String msgKey, Object... args) {
        try {
            return messageSource.getMessage(msgKey, args, getLocale());
        } catch (Exception e) {
            throw new RuntimeException("翻譯失敗:" + msgKey + ", " + e.getMessage());
        }
    }

    private static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
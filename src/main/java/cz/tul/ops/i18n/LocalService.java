package cz.tul.ops.i18n;

import cz.tul.ops.log.Logger;

import java.util.ResourceBundle;

public class LocalService {

    private static final ResourceBundle RESOURCE_BUNDLE;
    static {
        RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");
        Logger.debug(String.format("User is using locale %s.", RESOURCE_BUNDLE.getLocale()));
    }

    public static ResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;
    }
}

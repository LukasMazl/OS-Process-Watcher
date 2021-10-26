package cz.tul.ops.i18n;

import java.util.ResourceBundle;

public class LocalService {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");

    public static ResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;
    }
}

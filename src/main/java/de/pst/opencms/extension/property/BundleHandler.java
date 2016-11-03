package de.pst.opencms.extension.property;

import org.opencms.i18n.CmsMessages;

import java.util.Locale;

public class BundleHandler {

    public static String get(String key){
        CmsMessages messages = new CmsMessages("de.pst.opencms.repository.messages", Locale.ENGLISH);
        return messages.key(key);
    }

}

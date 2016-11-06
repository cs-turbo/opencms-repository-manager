package de.pst.opencms.extension.extension;

import de.pst.opencms.extension.property.BundleHandler;
import org.opencms.ui.apps.I_CmsAppCategory;

import java.util.Locale;

public class RepositoryManagerCategory implements I_CmsAppCategory{
    public static final String ID = "RepositoryManager";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName(Locale locale) {
        return BundleHandler.get("extension.name");
    }

    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}

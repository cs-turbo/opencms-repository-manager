package de.pst.opencms.extension.extension;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import org.opencms.file.CmsObject;
import org.opencms.ui.apps.CmsAppVisibilityStatus;
import org.opencms.ui.apps.I_CmsAppButtonProvider;
import org.opencms.ui.apps.I_CmsWorkplaceApp;
import org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration;

import java.util.Locale;

public class RepositoryManagerConfiguration implements I_CmsWorkplaceAppConfiguration {
    @Override
    public String getAppCategory() {
        return RepositoryManagerCategory.ID;
    }

    @Override
    public I_CmsWorkplaceApp getAppInstance() {
        return new RepositoryManager();
    }

    @Override
    public String getButtonStyle() {
        return I_CmsAppButtonProvider.BUTTON_STYLE_ORANGE;
    }

    @Override
    public String getHelpText(Locale locale) {
        return "Module Repository";
    }

    @Override
    public Resource getIcon() {
        return FontAwesome.ARCHIVE;
    }

    @Override
    public String getId() {
        return "RepositoryManager";
    }

    @Override
    public String getName(Locale locale) {
        return "Module Repository";
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public int getPriority() {
        return I_CmsWorkplaceAppConfiguration.DEFAULT_PRIORIY;
    }

    @Override
    public CmsAppVisibilityStatus getVisibility(CmsObject cmsObject) {
        return new CmsAppVisibilityStatus(true, true, null);
    }
}

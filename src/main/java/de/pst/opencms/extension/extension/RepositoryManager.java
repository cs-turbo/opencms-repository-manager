package de.pst.opencms.extension.extension;

import com.vaadin.ui.*;
import de.pst.opencms.extension.listener.FetchClickListener;
import de.pst.opencms.extension.property.BundleHandler;
import de.pst.opencms.extension.view.DropdownHandler;
import de.pst.opencms.extension.view.TableHandler;
import de.pst.opencms.extension.view.ViewHandler;
import org.opencms.ui.apps.A_CmsWorkplaceApp;

import java.util.LinkedHashMap;
import java.util.List;


public class RepositoryManager extends A_CmsWorkplaceApp {

    @Override
    protected LinkedHashMap<String, String> getBreadCrumbForState(String s) {
        LinkedHashMap<String, String> crumbs = new LinkedHashMap<String, String>();
        crumbs.put("->", "Module Repository");
        return crumbs;
    }

    @Override
    protected Component getComponentForState(String s) {
        ViewHandler viewHandler = new ViewHandler();
        return viewHandler.getRepositoryLayout();
    }

    @Override
    protected List<NavEntry> getSubNavEntries(String s) {
        return null;
    }

}

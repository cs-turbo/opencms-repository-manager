package de.pst.opencms.extension.extension;

import com.vaadin.ui.*;
import de.pst.opencms.extension.listener.FetchClickListener;
import de.pst.opencms.extension.property.BundleHandler;
import de.pst.opencms.extension.view.DropdownHandler;
import de.pst.opencms.extension.view.TableHandler;
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
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.setMargin(true);

        HorizontalLayout dropDownLayout = new HorizontalLayout();
        dropDownLayout.setSizeFull();

        DropdownHandler dropdownHandler = new DropdownHandler();
        ComboBox repoDropdown = dropdownHandler.get();

        Button fetchRepoButton = new Button(BundleHandler.get("button.fetch"));
        fetchRepoButton.setVisible(true);
        fetchRepoButton.setWidth("20%");

        dropDownLayout.addComponent(repoDropdown);
        dropDownLayout.addComponent(fetchRepoButton);
        dropDownLayout.setComponentAlignment(fetchRepoButton,Alignment.MIDDLE_LEFT);

        dropDownLayout.setExpandRatio(repoDropdown,0.75f);
        dropDownLayout.setExpandRatio(fetchRepoButton,0.45f);

        layout.addComponent(dropDownLayout);

        TableHandler handler = new TableHandler();
        Table table = handler.get();

        fetchRepoButton.addClickListener(new FetchClickListener());

        layout.addComponent(table);

        layout.setExpandRatio(dropDownLayout,0.05f);
        layout.setExpandRatio(table,0.95f);

        layout.setImmediate(true);
        layout.setVisible(true);

        return layout;
    }



    @Override
    protected List<NavEntry> getSubNavEntries(String s) {
        return null;
    }

}

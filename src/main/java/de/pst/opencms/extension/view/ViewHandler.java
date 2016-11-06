package de.pst.opencms.extension.view;

import com.vaadin.ui.*;
import de.pst.opencms.extension.listener.FetchClickListener;
import de.pst.opencms.extension.property.BundleHandler;

public class ViewHandler {
    private TableHandler tableHandler;
    private DropdownHandler dropdownHandler;


    public ViewHandler(){
        this.tableHandler = new TableHandler();
        this.dropdownHandler = new DropdownHandler();
    }

    public Component getRepositoryLayout(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.setMargin(true);

        Component dropdownLayout = getDropdownLayout();
        layout.addComponent(dropdownLayout);

        Table table = tableHandler.get();
        layout.addComponent(table);

        layout.setExpandRatio(dropdownLayout,0.05f);
        layout.setExpandRatio(table,0.95f);

        layout.setImmediate(true);
        layout.setVisible(true);
        return layout;
    }

    private Component getDropdownLayout(){
        HorizontalLayout dropDownLayout = new HorizontalLayout();
        dropDownLayout.setSizeFull();

        ComboBox repoDropdown = dropdownHandler.get();

        Button fetchRepoButton = new Button(BundleHandler.get("button.fetch"));
        fetchRepoButton.setVisible(true);
        fetchRepoButton.setWidth("20%");

        dropDownLayout.addComponent(repoDropdown);
        dropDownLayout.addComponent(fetchRepoButton);
        dropDownLayout.setComponentAlignment(fetchRepoButton,Alignment.MIDDLE_LEFT);

        dropDownLayout.setExpandRatio(repoDropdown,0.85f);
        dropDownLayout.setExpandRatio(fetchRepoButton,0.75f);

        fetchRepoButton.addClickListener(new FetchClickListener());

        return dropDownLayout;
    }

    public TableHandler getTableHandler() {
        return tableHandler;
    }

    public void setTableHandler(TableHandler tableHandler) {
        this.tableHandler = tableHandler;
    }

    public DropdownHandler getDropdownHandler() {
        return dropdownHandler;
    }

    public void setDropdownHandler(DropdownHandler dropdownHandler) {
        this.dropdownHandler = dropdownHandler;
    }
}

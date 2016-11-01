package de.pst.opencms.extension.view;

import com.vaadin.data.Item;
import com.vaadin.ui.*;
import de.pst.opencms.extension.entity.RemoteModule;
import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.listener.InstallClickListener;
import de.pst.opencms.extension.listener.UninstallClickListener;
import de.pst.opencms.extension.module.ManagerWrapper;
import de.pst.opencms.extension.property.PropertyHandler;
import de.pst.opencms.extension.repository.RepositoryHandler;
import org.apache.commons.io.FilenameUtils;

import java.util.List;


public class TableHandler {
    private static Table table;
    private static List<RemoteModule> cache;

    public TableHandler(){
    }



    private void init(){
        table = new Table("Modules");
        table.addContainerProperty("Status", CssLayout.class, null);
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Version", String.class, null);
        table.addContainerProperty("Action",  CssLayout.class, null);

        RepositoryHandler handler = new RepositoryHandler();
        DropdownHandler dropdownHandler = new DropdownHandler();
        ComboBox box = dropdownHandler.get();
        List<RemoteModule> modules;
        if(box.getValue() != null){
            RepoEntry entry = (RepoEntry) box.getValue();
            modules = handler.getRepository(entry.getUrl());
        }else{
            // Fallback
            modules = handler.getRepository(PropertyHandler.get("de.pst.opencms.repository.url"));
        }
        cache = modules;

        table.setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {
                Item item = table.getItem(itemId);
                String moduleName = item.getItemProperty("Name").getValue().toString();

                boolean isInstalled = ManagerWrapper.isInstalled(moduleName);

                if(isInstalled && propertyId != null && propertyId.toString() == "Status")
                    return "highlight-green";
                else if(propertyId != null && propertyId.toString() == "Status")
                    return "highlight-red";

                return "";
            }
        });

        for(RemoteModule module : modules) {
            addRow(table, module);
        }

        table.setSizeFull();
    }

    private void addRow(Table table,RemoteModule module) {
        Object newItemId = table.addItem();
        Item row1 = table.getItem(newItemId);

        String moduleName = FilenameUtils.removeExtension(module.getName());
        moduleName = moduleName.replaceAll("_"+module.getVersion(),"");

        boolean isInstalled = ManagerWrapper.isInstalled(moduleName);

        CssLayout buttonsLayout = new CssLayout();
        Button installButton = new Button("install");
        buttonsLayout.addComponent(installButton);
        installButton.setVisible(true);
        installButton.setEnabled(!isInstalled);
        installButton.addClickListener(new InstallClickListener(module));

        Button uninstallButton = new Button("uninstall");
        buttonsLayout.addComponent(uninstallButton);
        uninstallButton.setVisible(true);
        uninstallButton.setEnabled(isInstalled);
        uninstallButton.addClickListener(new UninstallClickListener(module));

        CssLayout statusLayout = new CssLayout();
        CheckBox box = new CheckBox("installed",isInstalled);
        box.setEnabled(false);
        box.setVisible(true);
        statusLayout.addComponent(box);

        row1.getItemProperty("Status").setValue(statusLayout);
        row1.getItemProperty("Name").setValue(moduleName);
        row1.getItemProperty("Version").setValue(module.getVersion());
        row1.getItemProperty("Action").setValue(buttonsLayout);
    }

    public Table get(){
        if(table == null)
            init();
        return table;
    }

    public void addModules(List<RemoteModule> modules, boolean clean){
        cache = modules;
        if(clean)
            table.removeAllItems();
        for(RemoteModule module : modules)
            addRow(table,module);
    }

    public void refresh(){
        if(table != null && cache != null){
            addModules(cache,true);
        }
    }

}

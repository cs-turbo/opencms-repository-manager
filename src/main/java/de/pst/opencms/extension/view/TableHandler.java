package de.pst.opencms.extension.view;

import com.vaadin.data.Item;
import com.vaadin.ui.*;
import de.pst.opencms.extension.entity.RemoteModule;
import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.listener.InstallClickListener;
import de.pst.opencms.extension.listener.UninstallClickListener;
import de.pst.opencms.extension.module.ModuleWrapper;
import de.pst.opencms.extension.property.BundleHandler;
import de.pst.opencms.extension.property.PropertyHandler;
import de.pst.opencms.extension.repository.RepositoryHandler;
import org.apache.commons.io.FilenameUtils;

import java.util.List;


public class TableHandler {
    private static Table table;
    private static List<RemoteModule> cache;

    public TableHandler(){}

    private void init(){
        table = new Table(BundleHandler.get("table.label"));
        table.addContainerProperty(BundleHandler.get("table.col.status"), CssLayout.class, null);
        table.addContainerProperty(BundleHandler.get("table.col.name"), String.class, null);
        table.addContainerProperty(BundleHandler.get("table.col.version"), String.class, null);
        table.addContainerProperty(BundleHandler.get("table.col.action"),  CssLayout.class, null);

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

        boolean isInstalled = ModuleWrapper.isInstalled(moduleName);

        CssLayout buttonsLayout = new CssLayout();
        Button installButton = new Button(BundleHandler.get("button.install"));
        buttonsLayout.addComponent(installButton);
        installButton.setVisible(true);
        installButton.setEnabled(!isInstalled);
        installButton.addClickListener(new InstallClickListener(module));

        Button uninstallButton = new Button(BundleHandler.get("button.uninstall"));
        buttonsLayout.addComponent(uninstallButton);
        uninstallButton.setVisible(true);
        uninstallButton.setEnabled(isInstalled);
        uninstallButton.addClickListener(new UninstallClickListener(module));

        CssLayout statusLayout = new CssLayout();
        CheckBox box = new CheckBox(BundleHandler.get("checkbox.installed"),isInstalled);
        box.setEnabled(false);
        box.setVisible(true);
        statusLayout.addComponent(box);

        row1.getItemProperty(BundleHandler.get("table.col.status")).setValue(statusLayout);
        row1.getItemProperty(BundleHandler.get("table.col.name")).setValue(moduleName);
        row1.getItemProperty(BundleHandler.get("table.col.version")).setValue(module.getVersion());
        row1.getItemProperty(BundleHandler.get("table.col.action")).setValue(buttonsLayout);
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

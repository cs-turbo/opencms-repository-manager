package de.pst.opencms.extension.view;

import com.vaadin.ui.ComboBox;
import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.module.ModuleWrapper;

import java.util.Collections;
import java.util.List;

public class DropdownHandler {

    private static ComboBox dropdown;

    public DropdownHandler(){

    }

    private void init(){
        dropdown = new ComboBox();
        List<RepoEntry> repoEntries = ModuleWrapper.getRepoResources();
        Collections.reverse(repoEntries);
        for(RepoEntry entry : repoEntries){
            dropdown.addItem(entry);
        }

        dropdown.setVisible(true);
        dropdown.setWidth("80%");

        if(!repoEntries.isEmpty()) {
            Object firstSelect = repoEntries.get(0);
            dropdown.setValue(firstSelect);
            dropdown.setNullSelectionAllowed(false);
        }
    }

    public ComboBox get(){
        if(dropdown == null)
            init();
        return dropdown;
    }
}
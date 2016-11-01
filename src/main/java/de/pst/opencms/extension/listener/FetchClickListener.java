package de.pst.opencms.extension.listener;

import com.vaadin.ui.Button;
import de.pst.opencms.extension.entity.RemoteModule;
import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.repository.RepositoryHandler;
import de.pst.opencms.extension.view.DropdownHandler;
import de.pst.opencms.extension.view.TableHandler;

import java.util.List;

public class FetchClickListener implements Button.ClickListener {

    public FetchClickListener(){
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        DropdownHandler dropdownHandler = new DropdownHandler();
        RepoEntry entry = (RepoEntry) dropdownHandler.get().getValue();
        if(entry != null) {
            String url = entry.getUrl();
            RepositoryHandler handler = new RepositoryHandler();
            List<RemoteModule> modules = handler.getRepository(url);
            TableHandler tableHandler = new TableHandler();
            tableHandler.addModules(modules, Boolean.TRUE);
        }
    }
}

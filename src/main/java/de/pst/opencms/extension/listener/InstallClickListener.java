package de.pst.opencms.extension.listener;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;
import de.pst.opencms.extension.entity.RemoteModule;
import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.module.ModuleHandler;
import de.pst.opencms.extension.property.PropertyHandler;
import de.pst.opencms.extension.view.DropdownHandler;
import de.pst.opencms.extension.view.TableHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.opencms.main.CmsException;
import org.opencms.main.OpenCms;

import java.io.IOException;
import java.net.URL;

public class InstallClickListener implements ClickListener {

    private RemoteModule module;

    public InstallClickListener(RemoteModule module) {
        this.module = module;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        DropdownHandler dropdownHandler = new DropdownHandler();

        RepoEntry repo = (RepoEntry) dropdownHandler.get().getValue();
        if (repo != null) {
            ModuleHandler.installModule(module, repo);

            Page.getCurrent().reload();

            TableHandler handler = new TableHandler();
            handler.refresh();
        }
    }
}

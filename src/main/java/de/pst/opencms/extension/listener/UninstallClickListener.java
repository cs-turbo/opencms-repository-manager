package de.pst.opencms.extension.listener;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import de.pst.opencms.extension.entity.RemoteModule;
import de.pst.opencms.extension.property.PropertyHandler;
import de.pst.opencms.extension.view.TableHandler;
import org.apache.commons.io.FilenameUtils;
import org.opencms.db.CmsDefaultUsers;
import org.opencms.file.CmsObject;
import org.opencms.main.CmsException;
import org.opencms.main.OpenCms;
import org.opencms.report.CmsLogReport;

import java.util.Locale;

public class UninstallClickListener implements Button.ClickListener {
    private RemoteModule module;

    public UninstallClickListener(RemoteModule module) {
        this.module = module;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String moduleName = FilenameUtils.removeExtension(module.getName());
        try {
            CmsObject cms = OpenCms.initCmsObject(new CmsDefaultUsers().getUserGuest());
            cms.loginUser(PropertyHandler.get("de.pst.opencms.repository.user"),PropertyHandler.get("de.pst.opencms.repository.password"));

            CmsLogReport report = new CmsLogReport(Locale.GERMAN,UninstallClickListener.class);
            OpenCms.getModuleManager().deleteModule(cms,moduleName,false,report);

            Page.getCurrent().reload();

            TableHandler handler = new TableHandler();
            handler.refresh();

        } catch (CmsException e) {
            e.printStackTrace();
        }
    }
}

package de.pst.opencms.extension.module;

import de.pst.opencms.extension.cms.CmsObjectWrapper;
import de.pst.opencms.extension.entity.RemoteModule;
import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.listener.UninstallClickListener;
import de.pst.opencms.extension.log.Logger;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.opencms.configuration.CmsConfigurationException;
import org.opencms.lock.CmsLockException;
import org.opencms.main.CmsException;
import org.opencms.main.OpenCms;
import org.opencms.report.CmsLogReport;
import org.opencms.security.CmsRoleViolationException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ModuleHandler {

    public static void installModule(RemoteModule module,RepoEntry repo){
        Logger.forClass(ModuleHandler.class);

        String moduleName = FilenameUtils.removeExtension(module.getName());
        String moduleUrl = String.format("%s%s",repo.getUrl(),module.getModuleUrl());
        try {
            byte[] modCont = IOUtils.toByteArray(new URL(moduleUrl));
            OpenCms.getModuleManager().getImportExportRepository().importModule(moduleName, modCont);
        } catch (CmsException e) {
            Logger.error(String.format("Couldn't import module %s. Error: %s",moduleName,e.getMessage()));
        } catch (MalformedURLException e) {
            Logger.error(String.format("Module URL incorrect: %s",e.getMessage()));
        } catch (IOException e) {
            Logger.error(String.format("Module contents couldn't be retrieved: %s",e.getMessage()));
        }
    }

    public static void uninstallModule(RemoteModule module){
        Logger.forClass(ModuleHandler.class);

        try {
            String moduleName = FilenameUtils.removeExtension(module.getName());
            CmsLogReport report = new CmsLogReport(CmsObjectWrapper.getLocale(),UninstallClickListener.class);
            OpenCms.getModuleManager().deleteModule(CmsObjectWrapper.getCmsObject(),moduleName,false,report);
        } catch (CmsRoleViolationException e) {
            Logger.error(String.format("User has wrong role: %s",e.getMessage()));
        } catch (CmsConfigurationException e) {
            Logger.error(String.format("The module to uninstall is not configured: %s",e.getMessage()));
        } catch (CmsLockException e) {
            Logger.error(String.format("The modules resources are locked: %s",e.getMessage()));
        }
    }

}

package de.pst.opencms.extension.module;

import de.pst.opencms.extension.cms.CmsObjectWrapper;
import de.pst.opencms.extension.entity.RemoteModule;
import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.listener.UninstallClickListener;
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
        String moduleName = FilenameUtils.removeExtension(module.getName());
        String moduleUrl = String.format("%s%s",repo.getUrl(),module.getModuleUrl());
        try {
            byte[] modCont = IOUtils.toByteArray(new URL(moduleUrl));
            OpenCms.getModuleManager().getImportExportRepository().importModule(moduleName, modCont);
        } catch (CmsException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uninstallModule(RemoteModule module){
        try {
            String moduleName = FilenameUtils.removeExtension(module.getName());
            CmsLogReport report = new CmsLogReport(CmsObjectWrapper.getLocale(),UninstallClickListener.class);
            OpenCms.getModuleManager().deleteModule(CmsObjectWrapper.getCmsObject(),moduleName,false,report);
        } catch (CmsRoleViolationException e) {
            e.printStackTrace();
        } catch (CmsConfigurationException e) {
            e.printStackTrace();
        } catch (CmsLockException e) {
            e.printStackTrace();
        }
    }

}

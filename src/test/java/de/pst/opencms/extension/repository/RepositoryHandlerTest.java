package de.pst.opencms.extension.repository;

import de.pst.opencms.extension.entity.RemoteModule;
import org.apache.commons.io.IOUtils;
import org.opencms.main.CmsException;
import org.opencms.main.OpenCms;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RepositoryHandlerTest {
    public static void main(String... args) {
        RepositoryHandler handler = new RepositoryHandler();
        String repoAddr = "http://localhost:8080/repo/";
        List<RemoteModule> modules = handler.getRepository(repoAddr);

        RemoteModule module = modules.get(0);

        try {
            byte[] modCon = IOUtils.toByteArray(new URL(repoAddr+module.getModuleUrl()));
            System.out.println(modCon.length);
            //OpenCms.getModuleManager().getImportExportRepository().importModule(module.getName(),modCon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(testUrl);


    }
}

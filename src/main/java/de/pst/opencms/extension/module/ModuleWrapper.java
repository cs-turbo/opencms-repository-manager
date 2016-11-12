package de.pst.opencms.extension.module;

import de.pst.opencms.extension.cms.CmsObjectWrapper;
import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.property.PropertyHandler;
import org.opencms.file.CmsObject;
import org.opencms.file.CmsResource;
import org.opencms.main.CmsException;
import org.opencms.main.OpenCms;
import org.opencms.module.CmsModule;
import org.opencms.module.CmsModuleManager;
import org.opencms.xml.content.CmsXmlContent;
import org.opencms.xml.content.CmsXmlContentFactory;

import java.util.*;

public class ModuleWrapper {
    static {
        ModuleWrapper._manager = OpenCms.getModuleManager();
    }

    private static CmsModuleManager _manager;

    private static final String REPO_PATH_FORMAT = "/system/modules/%s/repositories";

    public static CmsModule getRepoModule(){
        CmsModule module = _manager.getModule(PropertyHandler.get("de.pst.opencms.repository.module.name"));
        return module;
    }


    public static Map<String,String> getRepoResourcesMap(){
        HashMap<String,String> repos = new HashMap<String,String>();

        try {
            CmsObject cms = CmsObjectWrapper.getCmsObject();

            List<CmsResource> resources = cms.getFilesInFolder(String.format(REPO_PATH_FORMAT,PropertyHandler.get("de.pst.opencms.repository.module.name")));

            for (CmsResource res : resources) {
                CmsXmlContent xmlContent = CmsXmlContentFactory.unmarshal(cms, cms.readFile(res));

                Locale localeToUse = CmsObjectWrapper.getLocale();

                if(!hasCurrentLocale(xmlContent.getLocales())){
                    localeToUse = xmlContent.getLocales().get(0);
                }

                String moduleTitle = xmlContent.getStringValue(cms, PropertyHandler.get("de.pst.opencms.repository.xml.title"), localeToUse);
                String moduleUrl = xmlContent.getStringValue(cms, PropertyHandler.get("de.pst.opencms.repository.xml.link"), localeToUse);

                repos.put(moduleTitle, moduleUrl);
            }
        } catch (CmsException e) {
            e.printStackTrace();
        }

        return repos;
    }

    private static boolean hasCurrentLocale(List<Locale> locales){
        return locales.contains(CmsObjectWrapper.getLocale());
    }

    public static List<RepoEntry> getRepoResources(){
        List<RepoEntry> entries = new ArrayList<RepoEntry>();

        Map<String, String> map = getRepoResourcesMap();
        for(String key: map.keySet()){
            RepoEntry entry = new RepoEntry();
            entry.setName(key);
            entry.setUrl(map.get(key));
            entries.add(entry);
        }

        return entries;
    }

    public  static boolean isInstalled(String moduleName){
        return _manager.hasModule(moduleName);
    }


}

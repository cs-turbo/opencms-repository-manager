package de.pst.opencms.extension.module;

import de.pst.opencms.extension.entity.RepoEntry;
import de.pst.opencms.extension.property.PropertyHandler;
import org.opencms.file.CmsFile;
import org.opencms.file.CmsFolder;
import org.opencms.file.CmsObject;
import org.opencms.file.CmsResource;
import org.opencms.main.CmsException;
import org.opencms.main.OpenCms;
import org.opencms.module.CmsModule;
import org.opencms.module.CmsModuleManager;
import org.opencms.search.solr.CmsSolrIndex;
import org.opencms.search.solr.CmsSolrQuery;
import org.opencms.search.solr.CmsSolrResultList;
import org.opencms.xml.content.CmsXmlContent;
import org.opencms.xml.content.CmsXmlContentFactory;

import java.util.*;

public class ManagerWrapper {
    static {
        ManagerWrapper._manager = OpenCms.getModuleManager();
    }

    private static CmsModuleManager _manager;

    public static CmsModule getRepoModule(){
        CmsModule module = _manager.getModule(PropertyHandler.get("de.pst.opencms.repository.module.name"));
        return module;
    }


    public static Map<String,String> getRepoResourcesMap(){
        HashMap<String,String> repos = new HashMap<String,String>();

        try {
            CmsObject cms = OpenCms.initCmsObject("Guest");
            cms.loginUser(PropertyHandler.get("de.pst.opencms.repository.user"),PropertyHandler.get("de.pst.opencms.repository.password"));

            List<CmsResource> resources = cms.getFilesInFolder("/system/modules/" + PropertyHandler.get("de.pst.opencms.repository.module.name") + "/repositories");

            for (CmsResource res : resources) {
                CmsXmlContent xmlContent = CmsXmlContentFactory.unmarshal(cms, cms.readFile(res));

                //System.out.println(xmlContent.getValues(Locale.ENGLISH)+"");

                String moduleTitle = xmlContent.getStringValue(cms, "Title", Locale.ENGLISH);
                String moduleUrl = xmlContent.getStringValue(cms, "Link", Locale.ENGLISH);

                repos.put(moduleTitle, moduleUrl);
            }
        } catch (CmsException e) {
            e.printStackTrace();
        }

        return repos;
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

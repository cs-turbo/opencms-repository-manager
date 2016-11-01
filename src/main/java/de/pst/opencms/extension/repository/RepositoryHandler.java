package de.pst.opencms.extension.repository;

import de.pst.opencms.extension.entity.RemoteModule;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryHandler {

    public List<RemoteModule> getRepository(String repoAddr){
        ArrayList<RemoteModule> moduleList = new ArrayList<RemoteModule>();

        try {
            Document repo = Jsoup.connect(repoAddr).get();
            Elements nodes = repo.getElementsByClass("module");
            for(Element ele : nodes){
                RemoteModule module = new RemoteModule();
                module.setModuleUrl(ele.attr("href"));
                module.setName(ele.text());
                if(module.getName().indexOf("_") != -1) {
                    String version = FilenameUtils.removeExtension(module.getName());
                    version = version.substring(version.indexOf("_")+1);
                    module.setVersion(version);
                }
                else{
                    module.setVersion("0.1");
                }

                moduleList.add(module);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return moduleList;
    }

}

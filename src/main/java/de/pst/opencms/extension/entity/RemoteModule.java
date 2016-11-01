package de.pst.opencms.extension.entity;

public class RemoteModule {
    private String name;
    private String version;
    private String moduleUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleUrl() { return moduleUrl; }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

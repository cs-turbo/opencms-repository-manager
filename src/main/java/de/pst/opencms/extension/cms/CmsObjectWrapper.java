package de.pst.opencms.extension.cms;

import org.opencms.file.CmsObject;

import java.util.Locale;

public class CmsObjectWrapper {

    private static CmsObject currentCmsObject;
    private static Locale currentLocale;

    public static void setCmsObject(CmsObject cmsObject){
        currentCmsObject = cmsObject;
    }

    public static void setLocale(Locale locale){ currentLocale = locale; }

    public static Locale getLocale(){ return currentLocale; }

    public static CmsObject getCmsObject(){
        return currentCmsObject;
    }

}

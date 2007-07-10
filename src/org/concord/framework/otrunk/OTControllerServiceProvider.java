package org.concord.framework.otrunk;


public class OTControllerServiceProvider {
    private static OTControllerService service;
    
    public static OTControllerService getControllerService(OTObject object) {
        if (service == null) {
            service = object.getOTObjectService().createControllerService();
        }
        
        return service;
    }
}

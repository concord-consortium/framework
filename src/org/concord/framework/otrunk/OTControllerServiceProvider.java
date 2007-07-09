package org.concord.framework.otrunk;

public class OTControllerServiceProvider {
    private static OTControllerService service;
    
    public static interface ResourceSchema extends OTResourceSchema
    {
    }
    
    static ResourceSchema resources;
    
    public static OTControllerService getControllerService() {
        if (service == null) {
            service = createControllerService();
        }
        
        return service;
    }

    private static OTControllerService createControllerService() {
        
        DefaultOTObject obj = new DefaultOTObject(resources);
        
        OTObjectService objService = obj.getOTObjectService();
        return objService.createControllerService();
    }
}

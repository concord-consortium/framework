package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTControllerService;
import org.concord.framework.otrunk.OTObjectService;

public interface OTControllerServiceFactory 
{
    public OTControllerService createControllerService(OTObjectService objectService); 

    /**
     * @deprecated if the object service is not passed in then applications which use 
     * multiple overlays will not work properly. 
     * @param objectService
     * @return
     */
    public OTControllerService createControllerService(); 

}

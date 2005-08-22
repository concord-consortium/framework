/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2005-08-22 15:32:00 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;

public interface OTObjectService
{
    public OTObject createObject(Class objectClass) throws Exception;

    public OTObject getOTObject(OTID childID) throws Exception;

    /**
     * This will create an OTID from the passed in string.
     * 
     * @param otidStr
     * @return
     */
    public OTID getOTID(String otidStr);

    public OTWrapper getWrapper(Object wrappedObject);
    
    public OTWrapper putWrapper(Object wrappedObject, OTWrapper wrapper);
}

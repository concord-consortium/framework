/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2006-05-05 15:44:33 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;

public interface DataStoreCollection
{
    public void addDataStore(String name, DataStore dStore); 
    
    public WritableArrayDataStore createDataStore();
}

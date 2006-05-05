/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2006-05-05 15:44:33 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObject;


public interface OTAction
    extends OTObject
{
    public void doAction();
    
    public String getActionText();
}

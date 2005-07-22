/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2005-07-22 16:47:00 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import java.util.Vector;

import org.concord.framework.otrunk.OTrunk;

public interface OTMultiUserView
    extends OTView
{
    public void setUserList(OTrunk otrunk, Vector userList);
}

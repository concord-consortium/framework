/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2005-01-11 05:48:42 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;




/**
 * OTResourceSchema
 * Class name and description
 *
 * Date created: Nov 11, 2004
 *
 * @author scott<p>
 *
 */
public interface OTResourceSchema
{
	public OTID getGlobalId();
	
	public String getName();
	public void setName(String name);
}

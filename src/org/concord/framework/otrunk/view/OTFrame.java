/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2005-01-27 16:43:12 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObject;


/**
 * OTFrame
 * Class name and description
 *
 * Date created: Jan 20, 2005
 *
 * @author scott<p>
 *
 */
public interface OTFrame
	extends OTObject
{
	int getHeight();
	
	int getWidth();
	
	String getTitle();
}

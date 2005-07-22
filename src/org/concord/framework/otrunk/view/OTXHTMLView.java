/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2005-07-22 16:19:09 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObject;

public interface OTXHTMLView
    extends OTView
{
    String getXHTMLText(OTObject otObject);
}

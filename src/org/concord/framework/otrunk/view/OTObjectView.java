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

import javax.swing.JComponent;


/**
 * PortfolioView
 * Class name and description
 *
 * Date created: Sep 29, 2004
 *
 * @author scott<p>
 *
 */
public interface OTObjectView
{
	public JComponent getComponent(boolean editable);
}

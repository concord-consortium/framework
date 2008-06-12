/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01742
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * END LICENSE */

/*
 * Last modification information:
 * $Revision: 1.2 $
 * $Date: 2007-09-25 12:22:22 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import javax.swing.JComponent;

import org.concord.framework.otrunk.OTObject;


/**
 * PortfolioView
 * Class name and description
 *
 * Date created: Sep 29, 2004
 *
 * TODO all the methods should get the OTObject so the same view instance can possibly
 * be used for multiple object.
 * TODO there should be another method which is something like "save".  This is to handle
 * the case where a user decides to quit the application so the view needs to save its
 * state and then the the application can check if there have been any changes.
 * TODO this should be more unified with the OTController interface.
 *
 * @author scott<p>
 *
 */
public interface OTJComponentView extends OTView
{
	public JComponent getComponent(OTObject otObject);
	
	public void viewClosed();
	
}

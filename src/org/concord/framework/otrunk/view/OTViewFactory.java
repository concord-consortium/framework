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
 
package org.concord.framework.otrunk.view;

import java.util.Vector;

import javax.swing.JComponent;

import org.concord.framework.otrunk.OTObject;

public interface OTViewFactory {

    public OTViewFactory createChildViewFactory();

	public void setUserList(Vector userList);

	/* (non-Javadoc)
	 * @see org.concord.otrunk.view.OTViewFactoryImpl#getComponent(org.concord.framework.otrunk.OTObject, org.concord.framework.otrunk.view.OTViewContainer, boolean)
	 */
	public JComponent getComponent(OTObject otObject,
			OTViewContainer container, boolean editable);

	public OTView getView(OTObject otObject, Class viewInterface);

	public OTView getView(OTObject otObject, OTViewEntry viewEntry);
	
	/* (non-Javadoc)
	 * @see org.concord.otrunk.view.OTViewFactoryImpl#getObjectView(org.concord.framework.otrunk.OTObject, org.concord.framework.otrunk.view.OTViewContainer)
	 */
	public OTObjectView getObjectView(OTObject otObject,
			OTViewContainer container);	
}

/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
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
 */

/*
 * Last modification information:
 * $Revision: 1.2 $
 * $Date: 2005-01-11 07:50:46 $
 * $Author: maven $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
 */
package org.concord.framework.otrunk;

import java.util.Vector;


/**
 * OTObjectMap
 * Class name and description
 *
 * Date created: Jan 11, 2005
 *
 * @author scott<p>
 *
 */
public interface OTObjectMap
{

	public int getNumberOfObjects();

	public OTObject getObject(String key);

	public Vector getObjectKeys();

	public void putObject(String key, OTObject pfObj);

	/* (non-Javadoc)
	 * @see org.concord.portfolio.objects.PfFolder#addChild(int, org.concord.portfolio.PortfolioObject)
	 */public void addChild(int index, OTObject pfObject);

	/* (non-Javadoc)
	 * @see org.concord.portfolio.objects.PfFolder#addChild(org.concord.portfolio.PortfolioObject)
	 */public void addChild(OTObject pfObject);

	/* (non-Javadoc)
	 * @see org.concord.portfolio.objects.PfFolder#getChild(int)
	 */public Object getChild(int index);

	/* (non-Javadoc)
	 * @see org.concord.portfolio.objects.PfFolder#getChildCount()
	 */public int getChildCount();

	/* (non-Javadoc)
	 * @see org.concord.portfolio.objects.PfFolder#removeAllChildren()
	 */public void removeAllChildren();

	public Vector getChildVector();

	public void setChildVector(Vector childVector);
}
/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2005-01-11 07:49:48 $
 * $Author: scytacki $
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
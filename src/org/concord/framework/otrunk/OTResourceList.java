/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2005-01-12 04:16:36 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
 */
package org.concord.framework.otrunk;


/**
 * OTResourceList
 * Class name and description
 *
 * Date created: Nov 8, 2004
 *
 * @author scott<p>
 *
 */
public interface OTResourceList extends OTResourceCollection
{

	public abstract Object get(int index);

	public abstract void add(Object object);

	public abstract void add(int index, Object object);
}
/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2004-12-06 03:23:42 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
 */
package org.concord.framework.otrunk;

import java.util.Vector;


/**
 * OTObjectList
 * Class name and description
 *
 * Date created: Dec 5, 2004
 *
 * @author scott<p>
 *
 */
public interface OTObjectList
{

	public abstract OTObject get(int index);

	public abstract Vector getVector();

	public abstract void add(OTObject obj);

	public abstract void add(int index, OTObject obj);

	/*
	 * It might be best if the users of this list could have all ids hidden from
	 * them.  However sometimes it is more efficient to add ids directly
	 * to the list.  If this method wasn't here then the call would have to
	 * instanciate the object and then call the other add method.
	 */
	public abstract void add(OTID id);

	public abstract int size();

	public abstract void removeAll();
}
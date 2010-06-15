package org.concord.framework.otrunk.wrapper;

import org.concord.framework.otrunk.OTObjectInterface;
import org.concord.framework.otrunk.OTObjectList;
import org.concord.framework.otrunk.OTResourceList;
import org.concord.framework.otrunk.OTResourceMap;

/**
 * This is essentially just a wrapper for an OTObjectList, but it extends OTObjectInterface
 * and so (1) can be referenced in otml, and (2) can have a view.
 * 
 * @author sfentress
 *
 */
public interface OTObjectSet
    extends OTObjectInterface
{
	public OTObjectList getObjects();
	
	
	// more wrappers. No views for these (yet)
	public OTResourceList getList();
	
	public OTResourceMap getMap();
}

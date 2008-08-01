package org.concord.framework.otrunk.wrapper;

import org.concord.framework.otrunk.OTObjectInterface;
import org.concord.framework.otrunk.OTObjectList;

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
}

package org.concord.framework.otrunk;

import java.util.Iterator;

import org.concord.framework.otrunk.otcore.OTClassProperty;

/**
 * 
 * OTChangeLogger <br>
 * A interface for a service that views could use to look up the previous value of an OTrunk
 * property.  This service isn't properly implemented yet, the plan is to use the XMLChangeLogger to 
 * implement it.
 * 
 * <p>
 * Date created: Mar 3, 2008
 * 
 * @author scytacki<p>
 *
 */public interface OTChangeLogger 
{
	public Iterator getPreviousValues(OTObject otObject, OTClassProperty property);
}

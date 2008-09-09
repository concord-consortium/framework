package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObjectService;

/**
 * This service is used instead of OTLabbookManager, so that a specific objectService will
 * always be used.
 * 
 * @author sfentress
 *
 */
public interface OTLabbookManagerProvider
{
	public OTLabbookManager getLabbookManager(OTObjectService objectService);
	
}

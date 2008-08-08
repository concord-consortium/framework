/**
 * 
 */
package org.concord.framework.otrunk.view;

/**
 * @author scott
 *
 */
public interface OTJComponentServiceFactory
{
	OTJComponentService createOTJComponentService(OTViewFactory viewFactory, boolean maintainViewMap);
}

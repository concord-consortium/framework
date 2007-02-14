/**
 * 
 */
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObject;

/**
 * @author scott
 *
 */
public interface OTViewConfigAware extends OTView 
{
	public void setViewConfig(OTObject viewConfig);
}

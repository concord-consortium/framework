/**
 * 
 */
package org.concord.framework.otrunk.view;

import javax.swing.JComponent;

import org.concord.framework.otrunk.OTObject;

/**
 * @author scott
 *
 */
public abstract class AbstractOTJComponentView 
	extends AbstractOTView
implements OTJComponentView 
{
	public JComponent getChildComponent(OTObject otObject,
			OTViewContainer container, boolean editable)
	{
		OTJComponentService jComponentService = 
			(OTJComponentService)getViewService(OTJComponentService.class);
		return jComponentService.getComponent(otObject, container, editable);
	}
}

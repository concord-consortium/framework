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
	public OTJComponentService getJComponentService()
	{
		return (OTJComponentService)getViewService(OTJComponentService.class);		
	}
	
	public JComponent getChildComponent(OTObject otObject,
			OTViewContainer container, boolean editable)
	{
		OTJComponentService jComponentService = getJComponentService();
		return jComponentService.getComponent(otObject, container, editable);
	}
	
	public JComponent getChildComponent(OTObject otObject,
			OTViewContainer container, boolean editable, String mode)
	{
		OTJComponentService jComponentService = getJComponentService();
		OTJComponentView view = jComponentService.getObjectView(otObject, container, mode);
		return view.getComponent(otObject, editable);
	}
	
	public OTJComponentView getComponentView(OTObject otObject,
			OTViewContainer container, String mode)
	{
		OTJComponentService jComponentService = getJComponentService();
		return jComponentService.getObjectView(otObject, container, mode);
	}
}

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
	OTJComponentService jComponentService;
	
	public OTJComponentService getJComponentService()
	{
		// Instead of getting the OTJComponentService directly
		// we will get an intermediate object and then use that
		// get the OTJComponentService for this particular view
		if(jComponentService == null){
			OTJComponentServiceFactory serviceFactory = 
				(OTJComponentServiceFactory)getViewService(OTJComponentServiceFactory.class);
			jComponentService = serviceFactory.createOTJComponentService();
		}
		
		return jComponentService;
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
		return jComponentService.getComponent(otObject, view, editable);
	}
	
	public OTJComponentView getComponentView(OTObject otObject,
			OTViewContainer container, String mode)
	{
		OTJComponentService jComponentService = getJComponentService();
		return jComponentService.getObjectView(otObject, container, mode);
	}
}

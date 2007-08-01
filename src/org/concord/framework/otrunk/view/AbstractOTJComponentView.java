/**
 * 
 */
package org.concord.framework.otrunk.view;

import javax.swing.JComponent;

import org.concord.framework.otrunk.OTChangeEvent;
import org.concord.framework.otrunk.OTChangeListener;
import org.concord.framework.otrunk.OTControllerService;
import org.concord.framework.otrunk.OTObject;

/**
 * @author scott
 *
 */
public abstract class AbstractOTJComponentView 
	extends AbstractOTView
implements OTJComponentView, OTViewEntryAware, OTViewContainerAware
{
	OTJComponentService jComponentService;
	protected OTViewEntry viewConfig;
	protected OTViewContainer viewContainer;
	private boolean reloadOnViewEntryChange = false;
	private MyOTChangeListener otListener;
	private boolean alreadyAddedListener;
	
	public OTJComponentService getJComponentService()
	{
		// Instead of getting the OTJComponentService directly
		// we will get an intermediate object and then use that
		// get the OTJComponentService for this particular view
		if(jComponentService == null){
			OTJComponentServiceFactory serviceFactory = 
				(OTJComponentServiceFactory)getViewService(OTJComponentServiceFactory.class);
			jComponentService = serviceFactory.createOTJComponentService(getViewFactory());
		}
		
		return jComponentService;
	}
	
	public OTControllerService createControllerService()
	{
		OTControllerServiceFactory controllerServiceFactory = 
			(OTControllerServiceFactory) getViewService(OTControllerServiceFactory.class);
		
    	OTControllerService controllerService = controllerServiceFactory.createControllerService();
	
    	return controllerService;
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
	
	public void setViewEntry(OTViewEntry viewConfig){
		this.viewConfig = viewConfig;
		
		otListener = new MyOTChangeListener();
		
		if (reloadOnViewEntryChange){
			viewConfig.addOTChangeListener(otListener);
		}
	}
	
	public void setViewContainer(OTViewContainer container){
		this.viewContainer = container;
	}
	
	public void setReloadOnViewEntryChange(boolean reload){
		if (!reloadOnViewEntryChange && reload && !alreadyAddedListener && viewConfig != null){
			viewConfig.addOTChangeListener(otListener);
			alreadyAddedListener = true;
		} else if (!reload){
			viewConfig.removeOTChangeListener(otListener);
		}
		
		reloadOnViewEntryChange = reload;
	}
	
	private class MyOTChangeListener implements OTChangeListener{

		public void stateChanged(OTChangeEvent e) {
			if (viewContainer != null){
				viewContainer.reloadView();
			}
		}
		
	}
}

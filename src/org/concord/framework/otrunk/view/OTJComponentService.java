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
public interface OTJComponentService 
{
	public JComponent getComponent(OTObject otObject, 
		       OTJComponentView view);

	public JComponent getComponent(OTObject otObject,
			OTViewContainer container);
	
	public OTJComponentView getObjectView(OTObject otObject,
			OTViewContainer container);	
	
	public OTJComponentView getObjectView(OTObject otObject,
			OTViewContainer container, String mode);
	
	public OTJComponentView getObjectView(OTObject otObject,
	        OTViewContainer container, String mode, OTViewEntry viewEntry);
	
	/**
	 * This returns the OTJComponentViewContext that is passed to children
	 * views created by this service. 
	 * 
	 * @return
	 */
	public OTJComponentViewContext getJComponentViewContext();
	
	public OTViewFactory getViewFactory();
	
}

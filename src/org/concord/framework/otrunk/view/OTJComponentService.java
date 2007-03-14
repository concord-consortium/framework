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
			OTViewContainer container, boolean editable);
	
	public OTJComponentView getObjectView(OTObject otObject,
			OTViewContainer container);	
}

/**
 * 
 */
package org.concord.framework.otrunk.view;

import java.util.Vector;

import javax.swing.JComponent;

import org.concord.framework.otrunk.OTObject;

/**
 * @author scott
 *
 */
public interface OTJComponentViewContext
{
	/**
	 * this returns the first view in this context that is viewing
	 * the passed in obj
	 * 
	 * @param obj
	 * @return
	 */
	public OTView getViewByObject(OTObject obj);
	
	
	/**
	 * this returns the first component in this context that is viewing
	 * the passed in obj
	 * 
	 * It will return null, if the container using this context didn't use
	 * one of the OTJComponentService.getComponent methods.
	 * 
	 * @param obj
	 * @return
	 */
	public JComponent getComponentByObject(OTObject obj);
	
	/**
	 * This returns all objects within this viewContext
	 * 
	 * @return
	 */
	public Object[] getAllObjects();
}

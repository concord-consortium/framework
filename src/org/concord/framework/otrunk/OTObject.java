/*
 * Created on Aug 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.concord.framework.otrunk;



//import java.util.Vector;

/**
 * @author scott
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface OTObject 
{
	public OTID getGlobalId();
	
	public String getName();
	
	public void setName(String name);

	public boolean getInput();

	/**
	 * This method is called when an this object is created. 
	 * The method will not be called when the object has been
	 * stored and then re-instanciated.
	 */
	public void init();	
}

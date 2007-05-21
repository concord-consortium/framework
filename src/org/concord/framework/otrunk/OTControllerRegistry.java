/**
 * 
 */
package org.concord.framework.otrunk;

/**
 * @author scott
 *
 */
public interface OTControllerRegistry
{

	public void registerControllerClass(Class viewClass);

	public Class getControllerClassByOTObjectClass(Class otObjectClass);

	public Class getControllerClassByRealObjectClass(Class realObjectClass);

}
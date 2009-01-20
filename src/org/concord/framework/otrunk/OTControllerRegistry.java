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

	public void registerControllerClass(Class<? extends OTController> viewClass);

	public Class<? extends OTController> getControllerClassByOTObjectClass(Class<? extends OTObject> otObjectClass);

	public Class<? extends OTController> getControllerClassByRealObjectClass(Class<?> realObjectClass);

}
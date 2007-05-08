/**
 * 
 */
package org.concord.framework.otrunk;

/**
 * @author scott
 *
 */
public interface OTServiceContext
{
	public void addService(Class serviceClass, Object service);

	public Object getService(Class serviceClass);
}

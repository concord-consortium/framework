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
	public <T> void addService(Class<T> serviceClass, T service);

	public <T> T getService(Class<T> serviceClass);
}

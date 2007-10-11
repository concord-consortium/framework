/**
 * 
 */
package org.concord.framework.otrunk;


/**
 * @author scott
 *
 */
public interface OTPackage
{
	/**
	 * This should return a list of class which implement OTPackage.
	 * This method should be called before initialize.  However if there are circular
	 * dependencies then the order they are initialized is undetermined.
	 * 
	 * These packages will be initialized before this package is initialized.
	 * 
	 * @return null if there are no dependencies.
	 */
	public Class [] getPackageDependencies();
	
	public void initialize(OTrunk otrunk);
	
	public Class [] getOTClasses();
}

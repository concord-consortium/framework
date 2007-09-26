/**
 * 
 */
package org.concord.framework.otrunk.view;

/**
 * @author scott
 *
 */
public interface OTRequestedViewEntryAware extends OTView 
{
	/**
	 * pass the viewEntry that was requested to the newly created view
	 * this is useful for mode views that want to display other modes of
	 * the original view entry.
	 * this entry might have been specified by the user, or it could have 
	 * been determined by looking up an interface and object type.
	 */ 
	public void setRequestedViewEntry(OTViewEntry viewEntry);
}

package org.concord.framework.otrunk.view;

import java.awt.Component;

/**
 * This interface extends OTDefaultComponentProvider to allow authorability of
 * the snapshots. getMainComponent should return the top-level component in the
 * hierarchy, and getAllComponents should return an array of all snapshotable
 * components
 * 
 * @author sfentress
 *
 */
public interface OTMainComponentProvider extends OTDefaultComponentProvider {
	public Component getMainComponent();
	public Component[] getAllComponents();
}

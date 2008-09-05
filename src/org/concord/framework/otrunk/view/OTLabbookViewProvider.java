package org.concord.framework.otrunk.view;

import javax.swing.JComponent;

import org.concord.framework.otrunk.OTObject;

/**
 * This is used by both the labbook and the snapshot album to create entries or
 * snapshots by saving only the otobject, not an image.
 * 
 * Views which implement this  interface can be saved to the snapshot album or labbook as otObjects. 
 * The view will then be able to pass representations of the objects to be displayed and manipulated 
 * in the album/labbook
 * 
 * @author sfentress
 *
 */
public interface OTLabbookViewProvider
{
	/**
	 * This should return a component suitable to be in a labbook entry.
	 * It could either be a static component or a dynamic component that the
	 * user can navigate around and mark up.
	 * This should not try to create an image of itself, as this image may not
	 * be available when the album requests it.
	 * 
	 * @param otObject
	 * @return
	 */
	public JComponent getLabbookView(OTObject otObject);
	
	/**
	 * This should return a static component suitable to be in the thumbnails panel.
	 * This 
	 * 
	 * @param otObject
	 * @return
	 */
	public JComponent getThumbnailView(OTObject otObject, int height);
	
	/**
	 * @return True if snapshot album should wrap view with drawtool
	 */
	public boolean drawtoolNeededForAlbum();
	
	/**
	 * This should return the object to be added to the snapshot. In general, the
	 * object passed in should just be cloned, but some objects may wish to reduce the
	 * size by using references for parts of the object (e.g. a drawingtool's background)
	 * 
	 * @param otObject
	 * @return
	 */
	public OTObject copyObjectForSnapshot(OTObject otObject);
	
}

/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01742
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * END LICENSE */
 
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObject;

public interface OTViewFactory {
	
	public String getDefaultViewMode();
	
	public void setDefaultViewMode(String mode);
	
	public static String NO_VIEW_MODE = "_no_view_mode";

    public <T extends OTView> T getView(OTObject otObject, Class<T> viewInterface);

	/**
	 * @see #getView(OTObject, OTViewEntry, String)
	 * @param otObject
	 * @param viewInterface
	 * @param mode
	 * @return
	 */
	public <T extends OTView> T getView(OTObject otObject, Class<T> viewInterface, String modeStr);
	
	/**
	 * This method is the same as calling getView(otObject, viewEntry, null)
	 * so the current mode of the viewFactory is used. 
	 * 
	 * @param otObject
	 * @param viewEntry
	 * @return
	 */
	public OTView getView(OTObject otObject, OTViewEntry viewEntry);
	
	/**
	 * If the mode is null then the current mode of this viewFactory is used.
	 * If the mode of this viewFactory is null then the mode of the parent view
	 * factory is used. 
	 * If the mode is NO_VIEW_MODE then no mode will be used and the 
	 * viewEntry will be used directly.
	 * 
	 * If the looked up view is not null and is not DEFAULT_VIEW_MODE 
	 * then an OTViewMode is looked up with that
	 * name.  And a mapping between this viewEntry and another viewEntry is
	 * searched for.  If no mapping exists then, a default viewEntry is used.
	 * Initially there will only be one default per map but eventually 
	 * information from the otObject and the viewEntry could be used to 
	 * determine an appropriate default.
	 *   
	 * @param otObject
	 * @param viewEntry
	 * @param mode
	 * @return
	 */
	public OTView getView(OTObject otObject, OTViewEntry viewEntry, String mode);
	
	/**
	 * If all the use cases have been properly taken care of this shouldn't be 
	 * needed.  The viewContext should only be available to views which
	 * have been instanciated by a view factory.  However there are still 
	 * some cases where views are created manually.
	 * 
	 * @return
	 */
	public OTViewContext getViewContext();
	
	/**
	 * This will allow other viewBundles to add their view entries to existing
	 * set of view entries. If the view entries are added to the top of the list,
	 * they will effectively override existing view entries for the same class.
	 * 
	 * @param entry the view entry to be added
	 * @param addToTop whether the entry should be added to the top of the list
	 */
	public void addViewEntry(OTViewEntry entry, boolean addToTop);
	
	/**
	 * This returns a list of names of the available view modes 
	 */
	public String [] getModeNames();
	
}
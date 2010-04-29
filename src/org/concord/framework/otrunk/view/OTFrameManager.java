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

/*
 * Last modification information:
 * $Revision: 1.7 $
 * $Date: 2007-10-18 23:07:37 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import javax.swing.JFrame;

import org.concord.framework.otrunk.OTObject;


/**
 * OTFrameManager
 * Class name and description
 *
 * Date created: Jan 21, 2005
 *
 * @author scott<p>
 *
 */
public interface OTFrameManager
{
	/**
	 * This will use the viewFactory to find the default view for
	 * the otObject.
	 *  
	 * @param otObject
	 * @param otFrame
	 */
	public void putObjectInFrame(OTObject otObject, OTFrame otFrame);
	
	/**
	 * This will use the viewFactory to find the default view for
	 * the otObject.
	 *  
	 * @param otObject
	 * @param otFrame
	 * @param positionX frame's top-left corner x
	 * @param positionY frame's top-left corner y
	 */
	public void putObjectInFrame(OTObject otObject, OTFrame otFrame, int positionX, int positionY);
	
	/**
	 * This will use the viewEntry passed in to get the 
	 * view for the otObject.
	 *  
	 * @param otObject
	 * @param viewEntry
	 * @param otFrame
	 */
	public void putObjectInFrame(OTObject otObject, 
			OTViewEntry viewEntry, OTFrame otFrame);

	/**
	 * This will use the viewEntry passed in to get the 
	 * view for the otObject.
	 *  
	 * @param otObject
	 * @param viewEntry
	 * @param otFrame
	 * @param viewMode
	 */
	public void putObjectInFrame(OTObject otObject, 
			OTViewEntry viewEntry, OTFrame otFrame, String viewMode);
	
	/**
	 * This will use the viewEntry passed in to get the 
	 * view for the otObject.
	 *  
	 * @param otObject
	 * @param viewEntry
	 * @param otFrame
	 * @param viewMode
	 * @param positionX frame's top-left corner x
	 * @param positionY frame's top-left corner y
	 */
	public void putObjectInFrame(OTObject otObject, OTViewEntry viewEntry, 
			OTFrame otFrame, String viewMode, int positionX, int positionY);
	
	/**
	 * This will use the viewEntry passed in to get the 
	 * view for the otObject.
	 *  
	 * @param otObject
	 * @param viewEntry
	 * @param otFrame
	 * @param viewMode
	 * @param positionX frame's top-left corner x
	 * @param positionY frame's top-left corner y
	 * @param forceReloadOTObject indicates whether the frame should reload the
	 * 		ot object in case it was already created and it had the same ot object 
	 */
	public void putObjectInFrame(OTObject otObject, OTViewEntry viewEntry, 
			OTFrame otFrame, String viewMode, int positionX, int positionY, 
			boolean forceReloadOTObject);
	
	/**
	 * Gets the main frame
	 */
	public JFrame getMainFrame();
	
	/**
	 * Sets the main frame
	 */
	public void setMainFrame(JFrame frame);
	
	/**
	 * Disposes the frame and its contents
	 */
	public void destroyFrame();
}

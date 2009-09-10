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
 * $Revision: 1.8 $
 * $Date: 2007-10-18 17:32:59 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObjectInterface;


/**
 * OTFrame
 * Class name and description
 *
 * Date created: Jan 20, 2005
 *
 * @author scott<p>
 *
 */
public interface OTFrame
	extends OTObjectInterface
{
	public static int DEFAULT_height = 300;
	int getHeight();
	void setHeight(int height);
	
	public static int DEFAULT_width = 400;
	int getWidth();
	void setWidth(int width);	

	public static int DEFAULT_positionX = 50;
	int getPositionX();
	
	public static int DEFAULT_positionY = 50;
	int getPositionY();
	
	public static boolean DEFAULT_OpenMaximized = false;
	boolean getOpenMaximized();
	void setOpenMaximized(boolean openMaximized);

	public String getTitle();
	
	public static boolean DEFAULT_borderlessPopup = false;
	public boolean getBorderlessPopup();
	
	public static boolean DEFAULT_useScrollPane = true;
	public boolean getUseScrollPane();
	
	public boolean getAlwaysOnTop();
}

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
 * $Date: 2007-07-25 20:58:06 $
 * $Author: sfentress $
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
	int getHeight();
	
	int getWidth();
	
	public static int DEFAULT_height = 300;
	
	public static int DEFAULT_width = 400;
	
	String getTitle();
	
	boolean getBorderlessPopup();
	
	public static boolean DEFAULT_borderlessPopup = false;
	
	boolean getUseScrollPane();
	
	public static boolean DEFAULT_useScrollPane = true;
}

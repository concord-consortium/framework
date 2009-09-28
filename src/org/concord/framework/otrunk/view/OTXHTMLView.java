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
 * $Revision: 1.5 $
 * $Date: 2007-03-09 05:43:48 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObject;

/**
 * If a view implements this interface then the 
 * getXHTMLText method will be called by the OTCompoundDoc 
 * This text is then inserted into the document text where the
 * object reference was.  
 * 
 * This is useful if you want to let the document view handle the
 * layout of the text.  This is also useful if the document is 
 * exported to html.
 * 
 * In the future there should be support so that you can implement
 * just this type of view, and it will be wrapped with a JComponent
 * if that is what the container is expecting.
 * 
 * @author scott
 *
 */
public interface OTXHTMLView
    extends OTView
{
	/**
	 * This is a property that can be set in the viewContext.  
	 * If it it set then if a parent view is embedding a child 
	 * view that can be both an xhtml view or another view, the parent
	 * should use the xhtml view.   This essentially tells the parent
	 * view to ignore the getEmbedXHTMLView value.
	 */
	public final static String ALWAYS_EMBED_XHTML_VIEW = 
		OTXHTMLView.class.getName() + ".ALWAYS_EMBED_XHTML_VIEW"; 
	
	/**
	 * Return a string representing the "view" of this object.
	 * The string has to conform to the xhtml currently supported
	 * by the HTMLEditor kit of the jdk.  
	 * 
	 * @param otObject
	 * @return
	 */
    String getXHTMLText(OTObject otObject);
    
    /**
     * Return a boolean representing whether or not this XHTML view
     * should be embedded within a document. 'true' means that the
     * XHTML will be placed into a document, whereas 'false' means the
     * JComponent will be embedded.
     * @return
     */
    boolean getEmbedXHTMLView(OTObject otObject);
}

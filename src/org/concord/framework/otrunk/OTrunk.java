
/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
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
 */

/*
 * Created on Jan 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.concord.framework.otrunk;

/**
 * This interface represents the abstract concept of an
 * OTrunk.  The implementation is in the OTrunk package.
 * 
 * This interface along with the DefaultOTObject and
 * OTResouceSchema allows another package to create complex
 * OTObjects.  These objects can implement interfaces that
 * can't be automatically handled by the OTrunk.   
 * 
 * @author scytacki
 *
 */
public interface OTrunk {
	/* (non-Javadoc)
	 */
	public OTObject createObject(Class objectClass) throws Exception;

	public void setRoot(OTObject obj) throws Exception;

	public OTObject getRoot() throws Exception;

	public void close();

	/**
	 * Warning: this is method should only be used when you don't know
	 * which object is requesting the new OTObject.  The requestion object
	 * is currently used to keep the context of user mode or authoring mode
	 * @param childID
	 * @return
	 * @throws Exception
	 */
	public OTObject getOTObject(OTID childID) throws Exception;

	public OTObject getOTObject(OTID referingId, OTID childID) throws Exception;
	
	public OTObject getUserRuntimeObject(OTObject authoredObject, OTUser user)
		throws Exception;

}
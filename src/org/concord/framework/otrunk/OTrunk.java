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
	public <T extends OTObject> T createObject(Class<T> objectClass) throws Exception;

	public void setRoot(OTObject obj) throws Exception;

	public OTObject getRoot() throws Exception;
	public OTObjectService getRootObjectService();

	public void close();

	/**
	 * Warning: this is method should only be used when you don't know
	 * which object is requesting the new OTObject.  The requesting object
	 * is currently used to keep the context of user mode or authoring mode
	 * @param childID
	 * @return
	 * @throws Exception
	 */
	public OTObject getOTObject(OTID childID) throws Exception;

	/**
	 * This will create an OTID from the passed in string.
	 * 
	 * @param otidStr
	 * @return
	 */
	public OTID getOTID(String otidStr);
	
	public OTObject getUserRuntimeObject(OTObject authoredObject, OTUser user)
		throws Exception;

    public boolean hasUserModified(OTObject authoredObject, OTUser user)
        throws Exception;
    
    /**
     * Returns the base "authored" object for a given object.
     * This method uses the root object service to resolve the given object.
     * @param otObject
     * @return
     * @throws Exception
     */
    public <T extends OTObject> T getRuntimeAuthoredObject(T otObject) throws Exception;
    
    /**
     * Returns the base "authored" object for a given object.
     * This method uses the given object service to resolve the given object.
     * @param otObject
     * @return
     * @throws Exception
     */
    public <T extends OTObject> T getRuntimeAuthoredObject(T otObject, OTObjectService objectService) throws Exception;
    
	/** 
	 * All the services will be checked to see which is an instanceof this 
	 * serviceInterface.
	 * 
	 * @param serviceInterface
	 * @return
	 */
	public <T> T getService(Class<T> serviceInterface);
	
	/**
	 * This can be used to register OTPackages.  If you are loading
	 * in all the state from storage this should not be needed.  
	 * But if you want create OTObjects dynamically then you should
	 * register all the packages for the OTObjects you are going to create. 
	 * 
	 * @param packageClass
	 */
    public void registerPackageClass(Class<? extends OTPackage> packageClass);

}

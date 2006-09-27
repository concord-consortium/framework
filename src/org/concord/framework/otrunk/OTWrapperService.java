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

package org.concord.framework.otrunk;

public interface OTWrapperService {	
	/**
	 * This is used to register wrapper classes with the service. 
	 * They need to be registered so that getWrapper can figure out
	 * what OTWrapper instance to create for that real object.
	 * wrapper classes are recorded globably, they are also registered
	 * automatically the first time they are used in getRealObject.
	 * 
	 * This method is temporary until this functionaly can be handled elsewhere.
	 *
	 * The problematic use case is:
	 * A GraphView object has an export to otml action.  The GraphView can
	 * display any type of graphable, most of which it doesn't directly know 
	 * the classes of.  So the GraphView cannot register wrappers for these.
	 * In this case the wrappers need to registered by the application.  In an
	 * application with a plugin architecure, the initializer of the plugin
	 * should register the wrapper.   
	 *  
	 * Basically wherever the ability to add unknown graphables to the GraphView
	 * is encoded, that place should also register wrappers for those graphables.
	 */
	public void registerWrapperClass(Class wrapperClass);

	/**
	 * This is a helper method.  The real object class of a wrapper class should
	 * be in a 
	 * public final static Class [] wrappedObjectClasses
	 * field of the interface.  This method just looks up that field and returns
	 * the value.
	 *
	 * @param wrapperClass
	 * @return
	 */
	public Class [] getRealObjectClasses(Class wrapperClass);
	
	/**
	 * This searches an internal table of realObject to 
	 * see if a wrapper has been created for this object.
	 * It returns it if it has.
	 * 
	 * If the wrapper has not been created then the
	 * registered wrapper classes are search to see if there
	 * is a match.  The wrapper class needs to define a 
	 * public final static wrappedObjectClass 
	 * field.  If it matches
	 * then a wrapper object is created, and saved. 
	 *  
	 * The {@link OTWrapper} registerWrappedObject is called.
	 * followed by saveObject.
	 * 
	 * it is no longer the responsibility of registerWrappedObject
	 * to call putWrapper.
	 * 
	 * In the OTWrapper object the saveObject method can and should
	 * call getWrapper when this wrapper needs to save a reference to another
	 * realObject.  
	 * 
	 * Note: this will only call saveObject if this is the first time the
	 * realObject is seen.
	 * 
	 * The implementation should use a weak map so that if all the 
	 * references to the real object are gone then the real object
	 * can be garbage collected.  If the real object is needed again for
	 * some reason then it will just be created again.
	 *
	 * @param realObject
	 * @return
	 */
	public OTWrapper getWrapper(Object realObject);

	
	/**
	 * Look to see if this realObject has been created already or added with
	 * getWrapper.  If so return that previous realObject.
	 * 
	 * Otherwise, use the wrapper to create the object, and add load the 
	 * state from the wrapper into the realObject. 
	 *
	 * The implementation should use a weak map so that if all the 
	 * references to the real object are gone then the real object
	 * can be garbage collected.  If the real object is needed again for
	 * some reason then it will just be created again.
	 * 
	 * @param wrapper
	 * @return
	 */
	public Object getRealObject(OTWrapper wrapper);

	/**
	 * Take an real object which was created else
	 * where and connect it to an wrapper and 
	 * load the state of wrapper into the realObject
	 * @param realObject
	 * @param wrapper
	 */
	public Object getRealObject(OTWrapper wrapper, Object realObject);
}

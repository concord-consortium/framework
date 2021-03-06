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

import java.util.Vector;

public interface OTControllerService {	
	/**
	 * This method is typically not used.  Typically controller classes are 
	 * registered in a OTPackage implementation.
	 * 
	 * If a controller class is only registered using this method then it will need to be 
	 * registered each time a new view wants to use it.   If it is registered in the OTPackage
	 * then new views don't need to worry about registering it.
	 * 
	 * This method takes a controllerClass.
	 * Controller classes take OTObjects and create 
	 * real objects from them. 
	 *  
	 */
	public void registerControllerClass(Class<? extends OTController> viewClass);
	
	/**
	 * This is a helper method.  The real object class of a controller class should
	 * be in a public final static Class [] realObjectClasses
	 * field of the interface.  This method just looks up that field and returns
	 * the value.
	 *
	 * @param controllerClass
	 * @return
	 */
	public Class<?> [] getRealObjectClasses(Class<? extends OTController> controllerClass);
	
	
	/**
	 * This searches an internal table of realObjects to 
	 * see if an OTObject has been created for this object.
	 * It returns it if it has.
	 * 
	 * If the OTObject has not been created then the
	 * registered OTController classes are searched to see if there
	 * is one that can work with this realObject class.  
	 * The OTController class needs to define a 
	 * public final static Class [] realObjectClasses 
	 * field.  If it matches, that controller is used to make the
	 * OTObject.
	 *  
	 * The {@link OTController} registerRealObject is called.
	 * followed by saveRealObject.
	 * 
	 * In the OTController object the saveRealObject method can and should
	 * call getOTObject when it needs to save a reference to another
	 * realObject.  
	 * 
	 * Note: getOTObject will only call saveRealObject if this is the first time the
	 * realObject is seen by this service.
	 * 
	 * Note to implementors: a weak map should be used so that if all the 
	 * references to the real object are gone then the real object
	 * can be garbage collected.  If the real object is needed again for
	 * some reason then it will just be created again.
	 * 
	 * @param realObject
	 * @return
	 */
	public OTObject getOTObject(Object realObject);
	
	/**
	 * Look to see if this realObject has been created already or added with
	 * getOTObject.  If so return that previous realObject.
	 * 
	 * Otherwise, use the controller registered for the class of this otObject
	 * to create the realObject, and load the 
	 * state from the otObject into the realObject. 
	 *
	 * The implementation should use a weak map so that if all the 
	 * references to the real object are gone then the real object
	 * can be garbage collected.  If the real object is needed again for
	 * some reason then it will just be created again.
	 * 
	 * If the controller service is in the middle of disposing then this will
	 * return null for real objects that haven't already been created.
	 * 
	 * @param otObject
	 * @return
	 */
	public Object getRealObject(OTObject otObject);

	
	/**
	 * Take a real object which was created else
	 * where and connect it to an otObject and 
	 * load the state of otObject into the realObject
     *
     * @param realObject
	 * @param otObject
	 */
	public Object getRealObject(OTObject otObject, Object realObject);
	
	/**
	 * A helper method to get a Vector of real objects from an
	 * OTObjectList, so that we don't have to first get the OTObjectList
	 * and then create all the RealObjects ourselves.
	 * 
	 * @param otObjectList
	 * @return
	 */
	public Vector<Object> getRealObjects(OTObjectList otObjectList);
	
	/**
	 * This saves the state of realObject into this ot object.  It needs
	 * to lookup the controller for this realObject and otObject.  
	 * 
	 * This is very similar to getOTObject, which calls saveRealObject
	 * at the end.   Exposing it here allows others to use this
	 * functionality without having to directly access the controller
	 * or otObject directly  
	 * 
	 * TODO it is not clear how much state should be saved here and whether it should
	 * be recursive.  If listeners are being used on all the sub objects then when
	 * this is called after the object has been registered it doesn't need to be
	 * recursive.  If this is called to store a realObject that wasn't created by 
	 * the controllerservice this will need to recursively save all the sub objects. 
	 * 
	 * It should not mess up anything if the method is recursive.  It is just a waste of
	 * time in some cases.  However there is a danger of infinite loops with circular 
	 * references.  
	 * 
	 * @param otObject
	 * @param realObject
	 */
	public void saveRealObject(Object realObject, OTObject otObject);	
	
	/**
	 * This loads the state of this ot object into the realObject.  It needs
	 * to lookup the controller for this realObject and otObject.  
	 * 
	 * This is very similar to getRealObject but getRealObject only calls
	 * loadRealObject on the controller when it's called the first time
	 * 
	 * TODO it is not clear how much state should be loaded here and whether it should
	 * be recursive.  If listeners are being used on all the sub objects then when
	 * this is called after the object has been registered it doesn't need to be
	 * recursive.  If this is called to load a realObject that wasn't created by 
	 * the controllerservice this will need to recursively load all the sub objects. 
	 * 
	 * It should not mess up anything if the method is recursive.  It is just a waste of
	 * time in some cases.  However there is a danger of infinite loops with circular 
	 * references.  
	 * 
	 * @param otObject
	 * @param realObject
	 */
	public void loadRealObject(OTObject otObject, Object realObject);	
	
	/**
	 * This saves the state of realObject into this ot object.  It needs
	 * to lookup the controller for this realObject and otObject.  
	 * 
	 * This method is called both by getOTObject and getRealObject.  You should
	 * only need to call it explicitly if you don't want the other side effects
	 * of those methods.   For example if the realObject needs to be managed by 
	 * some parent object instead of the standard controller for the realObject
	 * 
	 * A possible alternative solution to this is to make a custom controller. 
	 * but that alternative hasn't been tried yet, so it might need more 
	 * framework support to be possible. 
	 */
	public void registerRealObject(Object realObject, OTObject otObject);
	
	/**
	 * Return the controller instance that has been or would be be used to create a
	 * real object.  You should not call any of the public methods of OTController
	 * these methods should only be called by the controller service.
	 * 
	 * @param otObject
	 * @return
	 */
	public OTController getController(OTObject otObject);
	
	/**
	 * Calling this will call dispose on all of the controllers this controller
	 * service knows about.  That will give them a chance to clean up any 
	 * listeners they've added.
	 */
	public void dispose();	
	
	/**
	 * provide a service which other controllers can use.
	 * this allows views which use controllers to provide custom services to those
	 * controllers. 
	 * 
	 * @param serviceClass
	 * @param service
	 */
	public void addService(Class<?> serviceClass, Object service);
	
	/**
	 * Lookup a service that was provided to this controllerService
	 * 
	 * @param serviceClass
	 * @return
	 */
	public <T> T getService(Class<T> serviceClass);
}

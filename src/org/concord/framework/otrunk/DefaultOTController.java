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

import java.lang.reflect.Field;

public abstract class DefaultOTController implements OTController 
{
	protected OTObject otObject;
	protected OTControllerService controllerService;
	
    public void initialize(OTObject otObject, OTControllerService controllerService)
    {
    	this.otObject = otObject;
    	this.controllerService = controllerService;
    }
	
    public OTObject getOTObject()
    {
    	return otObject;
    }
    
	/**
	 * The default implementation of this calls getRealObjectClass and
	 * instanciates an object of that type. 
	 * You need to override this one or more of your your real object classes 
	 * do not have a null constructor.  
	 * 
	 * See getRealObjectClass to understand how it works by default
	 * 
	 * If you only have one real object class with a null constructor 
	 * then you just need to add a property to your class:
	 * 
	 * public final static Class [] realObjectClasses
	 * 
	 * If you have multiple classes with null constructor you should still
	 * use the property above, but you should also override getRealObjectClass
	 * your implementation should look at the state in the OT object and determine
	 * which class to return.
	 * 
	 */
	public Object createRealObject()
	{
		return createRealObjectInternal();
	}

	/**
	 * instanciates the class returned by getRealObjectClass.
	 *
	 * @return
	 */
	protected final Object createRealObjectInternal()
	{
		Class realObjectClass = getRealObjectClass();
		try {
			return realObjectClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	

	/**
	 * This method is used by the default implementation of 
	 * createRealObject.  It is also used by createRealObjectInternal
	 * 
	 * By default this method looks for a static field on the class:
	 * 
	 * public final static Class [] realObjectClasses
	 * 
	 * It then takes the first element of that array and returns it.
	 * 
	 * You will need to override this if you have multiple classes and need
	 * to decide which one to use based on the content of the your OT object. 
	 * 
	 * @return
	 */
	protected Class getRealObjectClass()
	{
		return getRealObjectClassInternal();
	}
	
	/**
	 * This method is useful if a class inbetween your class and this class
	 * overrode the default behavior of getRealObjectClass()
	 * 
	 * @see org.concord.framework.otrunk.OTController#getRealObjectClass()
	 */
	protected final Class getRealObjectClassInternal()
	{
		try {
			Field field = getClass().getField("realObjectClasses");
			Class [] objClasses = (Class [])field.get(null);
			return objClasses[0];
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}

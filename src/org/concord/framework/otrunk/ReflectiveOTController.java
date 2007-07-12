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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class ReflectiveOTController extends DefaultOTController
{	
	protected Vector loadMappings;
	protected Vector saveMappings;
		
	public void loadRealObject(Object realObject) {

		OTObjectInterface myObject = (OTObjectInterface)otObject;
		

		// Go through all the methods of the resources class
		// and see if there is a matching method in the realObject
		// class.  Then call that method
		// look for getProperty
		// then look for setProperty
		Class resourcesClass = myObject.getClass();
		Method[] methods = resourcesClass.getMethods();
		
		Class realObjClass = realObject.getClass();

		if(loadMappings == null){
			loadMappings = new Vector();

			for(int i=0; i<methods.length; i++){
				Method method = methods[i];
				String name = method.getName();
				if(method.getParameterTypes().length != 0){
					continue;
				}
				
				String propertyPart = null;
				if(name.startsWith("get")){
					propertyPart = name.substring(3);
				} else if(name.startsWith("is")){
					propertyPart = name.substring(2);
				} else {
					continue;
				}
				String propertyName = propertyPart.substring(0,1).toLowerCase() + propertyPart.substring(1);
				Class propertyClass = method.getReturnType();

				Method realObjMethod = null;
				try {
					realObjMethod = realObjClass.getMethod("set" + propertyPart, new Class[]{propertyClass});
				} catch (SecurityException e) {
					e.printStackTrace();
					continue;
				} catch (NoSuchMethodException e) {
					continue;
				}

				Mapping mapping = new Mapping();
				mapping.propertyName = propertyName;
				mapping.getMethod = method;
				mapping.setMethod = realObjMethod;

				loadMappings.add(mapping);

			}
		} 
		
		for(int i=0; i<loadMappings.size(); i++){
			Mapping mapping = (Mapping)loadMappings.get(i);
			try {
				if(myObject.isResourceSet(mapping.propertyName)){
					Object value = mapping.getMethod.invoke(otObject, null);
					mapping.setMethod.invoke(realObject, new Object[]{value});
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

	public void registerRealObject(Object realObject) {
	}

	public void saveRealObject(Object realObject) {
		OTObjectInterface myObject = (OTObjectInterface)otObject;
		
		// Go through all the methods of the resources class
		// and see if there is a matching method in the realObject
		// class.  Then call that method
		// look for getProperty
		// then look for setProperty
		Class resourcesClass = myObject.getClass();
		Method[] methods = resourcesClass.getMethods();
		
		Class realObjClass = realObject.getClass();

		if(saveMappings == null){
			saveMappings = new Vector();

			for(int i=0; i<methods.length; i++){
				Method resourceMethod = methods[i];
				String name = resourceMethod.getName();
				if(!name.startsWith("set") ||
						resourceMethod.getParameterTypes().length != 1){
					continue;
				}				
				String propertyPart = name.substring(3);
				String propertyName = propertyPart.substring(0,1).toLowerCase() +
				propertyPart.substring(1);
				Class propertyClass = resourceMethod.getParameterTypes()[0];

				Method realObjMethod = null;
				try {
					realObjMethod = realObjClass.getMethod("get" + propertyPart, null);
				} catch (SecurityException e) {
					e.printStackTrace();
					continue;
				} catch (NoSuchMethodException e) {
					// look for the is method
					try {
						realObjMethod = realObjClass.getMethod("is" + propertyPart, null);
					} catch (SecurityException ex) {
						e.printStackTrace();
						continue;
					} catch (NoSuchMethodException ex) {
						continue;
					}
				}

				if(realObjMethod.getReturnType() != propertyClass){
					// not quite a match
					continue;
				}

				Mapping mapping = new Mapping();
				mapping.propertyName = propertyName;
				mapping.getMethod = realObjMethod;
				mapping.setMethod = resourceMethod;

				saveMappings.add(mapping);
			}
		}
		
		for(int i=0; i<saveMappings.size(); i++){
			Mapping mapping = (Mapping)saveMappings.get(i);
			try {
				Object value = mapping.getMethod.invoke(realObject, null);
				mapping.setMethod.invoke(myObject, new Object[]{value});
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

	public static class Mapping
	{
		String propertyName;
		Method getMethod;
		Method setMethod;
	}

	public boolean isRealObjectSharable(OTObject otObject, Object realObject) 
	{	
		// We'll be safe here and not share the real object.
		return false;
	}
}



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
package org.concord.framework.system;

/**
 * @author dima
 *
 */

import java.lang.reflect.*;


public final class SystemResourcesChecker
{
	
	public static final String QUICKTIME_NAME  = "quicktime";
	public static final String GL4JAVA_NAME     = "gl4java";

	public static boolean checkResource(String resName){
		boolean retValue = false;
		if(resName == null) return retValue;
		if(resName.equalsIgnoreCase(QUICKTIME_NAME)){
			retValue = checkQuickTime();
		}else if(resName.equalsIgnoreCase(GL4JAVA_NAME)){
			retValue = checkGL4Java();
		}
		return retValue;
	}
	
	
	protected static boolean checkQuickTime(){
		boolean retValue = false;
		try{
			Class.forName("quicktime.QTSession");
			retValue = true;
		}catch(Throwable t){
			 retValue = false;
		}
		return retValue;
	}
	
	protected static boolean checkGL4Java(){
		boolean retValue = false;
		try{
		    Class stringClass = String.class;
			Class clazz = Class.forName("gl4java.GLContext");
			Method m = clazz.getMethod("loadNativeLibraries",new Class[]{stringClass,stringClass,stringClass});
            Object retObj = m.invoke(null,new Object[]{null,null,null});
            if(retObj instanceof Boolean){
                retValue = ((Boolean)retObj).booleanValue();
            }
		}catch(Throwable t){
			 retValue = false;
		}
		return retValue;
	}

}


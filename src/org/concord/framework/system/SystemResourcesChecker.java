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


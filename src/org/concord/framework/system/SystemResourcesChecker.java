package org.concord.framework.system;

/**
 * @author dima
 *
 */
public final class SystemResourcesChecker
{
	public static boolean checkResource(String resName){
		boolean retValue = false;
		if(resName == null) return retValue;
		if(resName.equalsIgnoreCase("quicktime")){
			retValue = checkQuickTime();
		}
		return retValue;
	}
	
	
	protected static boolean checkQuickTime(){
		boolean retValue = false;
		try{
			Class.forName("quicktime.QTSession");
			retValue = true;
		}catch(Throwable t){
			 retValue = true;
		}
		return retValue;
	}
	
}

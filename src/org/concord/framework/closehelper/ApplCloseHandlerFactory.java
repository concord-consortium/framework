package org.concord.framework.closehelper;

public class ApplCloseHandlerFactory
{
	public static ApplCloseHandler getApplCloseHandler(){
		boolean macOS = System.getProperty("os.name").startsWith("Mac OS");
		if(!macOS) return null;
		ApplCloseHandler handler = null;
		boolean	jdk14 = false;
		try{
			Class.forName("java.nio.ByteBuffer");
			jdk14 = true;
		}catch(Throwable t){}
		
		try{
			Class appHandlerClass = null;
			if(jdk14){
				appHandlerClass = Class.forName("org.concord.client.closehelper.Mac14ApplCloseHandler");
			}else{
				appHandlerClass = Class.forName("org.concord.client.closehelper.MacApplCloseHandler");
			}
			handler =  (ApplCloseHandler)appHandlerClass.newInstance();
		}catch(Throwable t){
			handler = null;
		}

		return handler;
	}
}

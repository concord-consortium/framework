package org.concord.framework.closehelper;

import com.apple.eawt.*;


public class Mac14ApplCloseHandler extends ApplicationAdapter implements ApplCloseHandler
{
ApplCloseHandlerListener	listener;
public static Object lock = new Object();
private static Application application = null;
	public void registerHandlers(ApplCloseHandlerListener	listener){
		if(application == null) application = new Application();
		addApplCloseHandlerListener(listener);
	}
	public void addApplCloseHandlerListener(ApplCloseHandlerListener	l){
		if(l == null) return;
		if(l != listener) listener = l;
		if(application != null) application.addApplicationListener(this);

	}
	public void removeApplCloseHandlerListener(ApplCloseHandlerListener	l){
		if(l == null) return;
		if(l == listener) listener = null;
		if(application != null) application.removeApplicationListener(this);
	}



	public void handleQuit() throws IllegalStateException{
		if(listener != null){
			synchronized(lock){
				listener.handleQuit();
			}
		}
	}
	public void handlePrefs(){}
	public void handleAbout(){}

	public void handleAbout(ApplicationEvent event){}
	public void handlePreferences(ApplicationEvent event){}
	public void handleQuit(ApplicationEvent event){
		event.setHandled(true);
		if(listener != null){
			synchronized(lock){
				listener.handleQuit();
			}
		}
	}



}


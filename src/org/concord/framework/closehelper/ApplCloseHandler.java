package org.concord.framework.closehelper;

public interface ApplCloseHandler
{
	public void registerHandlers(ApplCloseHandlerListener	l);
	public void addApplCloseHandlerListener(ApplCloseHandlerListener	l);
	public void removeApplCloseHandlerListener(ApplCloseHandlerListener	l);
}

package org.concord.framework.logging;


public class LogHintMessage
{
public Object owner;
public int    priority;
public int	  logFrom;
	public LogHintMessage(Object owner,int    priority,int	  logFrom){
		setOwner(owner);
		setLogFrom(logFrom);
		setPriority(priority);
	}

	public void setOwner(Object owner){this.owner = owner;}
	public Object  getOwner(){return owner;}

	public void setLogFrom(int logFrom){this.logFrom = logFrom;}
	public int  getLogFrom(){return logFrom;}

	public void setPriority(int priority){this.priority = priority;}
	public int  getPriority(){return priority;}


	public boolean needToLog(int mode){
		int fromLog = getLogFrom();
		if(fromLog <= Loggable.LOG_FROM_UNDEFINE) return false;
		int mask = 1 << (fromLog - 1);
		return (mask & mode) != 0;
	}

}


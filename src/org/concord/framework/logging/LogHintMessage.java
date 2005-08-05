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


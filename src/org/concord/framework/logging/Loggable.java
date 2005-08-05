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

import java.io.OutputStream;
import java.io.Writer;

public interface Loggable
{
    public final static String []logMaskChoicesNames = 
    {
        "None",
        "Action mask",
        "Mouse mask",
        "Key mask",
        "Node enter mask",
        "Node exit mask",
        "Event mask",
        "Simulation start",
        "Simulation stop",
        "Simulation reset",
    };
    public final static String []logChoicesNames = 
    {
        "None",
        "From Action",
        "From Mouse",
        "From Key",
        "From Node enter",
        "From Node exit",
        "From Event",
        "From Simulation start",
        "From Simulation stop",
        "From Simulation reset",
        "Always",
    };

    

	public final static int LOG_NONE			= 0x0;
	public final static int LOG_ACTION			= 0x1;
	public final static int LOG_MOUSE			= 0x2;
	public final static int LOG_KEY				= 0x4;
	public final static int LOG_NODE_ENTER		= 0x8;
	public final static int LOG_NODE_EXIT		= 0x10;
	public final static int LOG_EVENT			= 0x20;
	public final static int LOG_SIM_START       = 0x40;
	public final static int LOG_SIM_STOP        = 0x80;
	public final static int LOG_SIM_RESET       =0x100;
	
	public final static int LOG_FROM_FORCE_LOG	= -1;
	
	public final static int LOG_FROM_UNDEFINE	= 0;
	public final static int LOG_FROM_ACTION		= 1;
	public final static int LOG_FROM_MOUSE		= 2;
	public final static int LOG_FROM_KEY		= 3;
	public final static int LOG_FROM_NODE_ENTER	= 4;
	public final static int LOG_FROM_NODE_EXIT	= 5;
	public final static int LOG_FROM_EVENT		= 6;
	public final static int LOG_FROM_SIM_START  = 7;
	public final static int LOG_FROM_SIM_STOP   = 8;
	public final static int LOG_FROM_SIM_RESET  = 9;
	public final static int LOG_FROM_ALWAYS		= LOG_FROM_FORCE_LOG;
	public final static int LOG_FROM_LAST		= LOG_FROM_SIM_RESET;

    public final static int []logMaskChoices = 
    {
        LOG_NONE,
        LOG_ACTION,
        LOG_MOUSE,
        LOG_KEY,
        LOG_NODE_ENTER,
        LOG_NODE_EXIT,
        LOG_EVENT,
        LOG_SIM_START,
        LOG_SIM_STOP,
        LOG_SIM_RESET
    };
    public final static int []logChoices = 
    {
        LOG_FROM_UNDEFINE,
        LOG_FROM_ACTION,
        LOG_FROM_MOUSE,
        LOG_FROM_KEY,
        LOG_FROM_NODE_ENTER,
        LOG_FROM_NODE_EXIT,
        LOG_FROM_EVENT,
        LOG_FROM_SIM_START,
        LOG_FROM_SIM_STOP,
        LOG_FROM_SIM_RESET,
        LOG_FROM_ALWAYS,
    };

	
	public void log(Writer writer,LogHintMessage hint);
	public void log(OutputStream out,LogHintMessage hint);
	public Loggable getLoggable(Object selector);
	
	public void setLogMode(int logmode);
	public int  getLogMode();
	public int  getDefaultLogMode();
	
	public void setLogName(String name);
	public String getLogName();
	
	public Interaction getFirstInteraction();
	public Interaction getActionInteraction();
	public Interaction getLastInteraction();
	
	public void initLogState();
	
}


/*
 * Created on Jan 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.concord.framework.data;

/**
 * @author scytacki
 *
 */
public interface DataFlowCapabilities 
{
	public final static class Capabilities 
	{
		protected boolean start;
		protected boolean stop;
		protected boolean reset;
		
		public Capabilities(boolean start, boolean stop, boolean reset)
		{
			this.start = start;
			this.stop = stop;
			this.reset = reset;
		}
		
		public boolean canStart()
		{
			return start;
		}
		public boolean canStop()
		{
			return stop;			
		}
		public boolean canReset()
		{
			return reset;
		}		
	}
	
	/**
	 * An object that wants to report to others what methods
	 * actually do things can use this interface to do so.
	 * 
	 * @return
	 */
	public Capabilities getDataFlowCapabilities();
}

/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2004-08-24 23:15:08 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;


/**
 * DataStoreEvent
 * Class name and description
 *
 * Date created: Aug 24, 2004
 *
 * @author imoncada<p>
 *
 */
public class DataStoreEvent extends DataEvent
{
	/**
	 * 
	 */
	public static final int DATA_ADDED = 1;
	public static final int DATA_REMOVED = 2;
	public static final int DATA_CHANGED = 3;
	
	protected DataStore source;
	
	/**
	 * 
	 */
	public DataStoreEvent()
	{
		this(null, DATA_CHANGED);
	}

	/**
	 * @param type
	 */
	public DataStoreEvent(DataStore source, int type)
	{
		super(type);
		setSource(source);
	}
	
	/**
	 * @return Returns the source.
	 */
	public DataStore getSource()
	{
		return source;
	}
	
	/**
	 * @param source The source to set.
	 */
	public void setSource(DataStore source)
	{
		this.source = source;
	}
}

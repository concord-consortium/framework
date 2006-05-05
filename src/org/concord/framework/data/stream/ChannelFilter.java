/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2006-05-05 15:44:33 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;

/**
 * ChannelFilter
 * Class name and description
 *
 * Date created: Jun 7, 2005
 *
 * @author scott<p>
 *
 */
public interface ChannelFilter
{
    /**
     * This provides a way to pass in filters to data consumers
     * It is a lightweight way to modify the data before displaying 
     * it without having to copy all the data. 
     * 
     * @param x
     * @param y
     * @return
     */
    public float filter(float x, float y);
}

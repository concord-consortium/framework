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

/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.concord.framework.otrunk;

import java.util.EventObject;

/**
 * @author scott
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OTChangeEvent extends EventObject
{
    /**
     * This field is added just to get rid of compiler warnings.
     * this class is not intended to be serialized.
     */
    private static final long serialVersionUID = 1L;

    public final static String OP_SET = "set";    
    public final static String OP_ADD = "add";
    public final static String OP_REMOVE = "remove";
    public final static String OP_REMOVE_ALL = "removeAll";
    public final static String OP_CHANGE = "change";
    public final static String OP_PUT = "put";
    
    private String property;
    
	private String operation;

	private Object value;
	
    public OTChangeEvent(OTObject source)
    {
        super(source);
    }
    
    /**
     * The property which changed
     */
    public String getProperty()
    {
    	return property;
    }
    
    public void setProperty(String name)
    {
    	property = name;
    }
    
    /**
     * The type of change
     */
    public String getOperation()
    {
    	return operation;
    }
    
    public void setOperation(String op)
    {
    	operation = op;
    }

	/**
	 * The effected object it depends on the operation.
	 * add: the object added
	 * remove: the object removed
	 * changed: the object changed
	 * put: the key of the new entry 
	 */
    public Object getValue()
    {
    	return value;
    }
    
    public void setValue(Object value)
    {
    	this.value = value;
    }
}

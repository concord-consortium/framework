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
 * Last modification information:
 * $Revision: 1.10 $
 * $Date: 2007-08-17 13:14:26 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;

import org.concord.framework.otrunk.otcore.OTClass;
import org.concord.framework.otrunk.otcore.OTClassProperty;




/**
 * OTResourceSchema
 * Class name and description
 *
 * Date created: Nov 11, 2004
 *
 * @author scott<p>
 *
 */
public interface OTResourceSchema extends OTChangeNotifying
{
	public OTID getGlobalId();
	
    public OTObjectService getOTObjectService();    
    
	public String getName();
	public void setName(String name);
	
	public boolean isResourceSet(String name);
	
	public String getLocalId();

    public OTClass otClass();
    
    public Object otGet(OTClassProperty property);
    public void otSet(OTClassProperty property, Object newValue);
    
    boolean otIsSet(OTClassProperty property);
    void otUnSet(OTClassProperty property);

    public OTObjectMap getAnnotations();
}

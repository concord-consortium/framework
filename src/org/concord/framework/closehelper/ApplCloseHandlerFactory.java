

/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
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
 */
package org.concord.framework.closehelper;

public class ApplCloseHandlerFactory
{
	public static ApplCloseHandler getApplCloseHandler(){
		boolean macOS = System.getProperty("os.name").startsWith("Mac OS");
		if(!macOS) return null;
		ApplCloseHandler handler = null;
		boolean	jdk14 = false;
		try{
			Class.forName("java.nio.ByteBuffer");
			jdk14 = true;
		}catch(Throwable t){}
		
		try{
			Class appHandlerClass = null;
			if(jdk14){
				appHandlerClass = Class.forName("org.concord.client.closehelper.Mac14ApplCloseHandler");
			}else{
				appHandlerClass = Class.forName("org.concord.client.closehelper.MacApplCloseHandler");
			}
			handler =  (ApplCloseHandler)appHandlerClass.newInstance();
		}catch(Throwable t){
			handler = null;
		}

		return handler;
	}
}

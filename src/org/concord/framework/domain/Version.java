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

package org.concord.framework.domain;

import java.util.Date;
import java.util.Properties;

public class Version
extends DomainVersionInfo
{
	
	public void save(Properties properties)
	{
		properties.setProperty("major.release", "" + majorRelease);
		properties.setProperty("minor.release", "" + minorRelease);
		properties.setProperty("major.version", "" + majorVersion);
		properties.setProperty("minor.version", "" + minorVersion);
		properties.setProperty("build", "" + build);
		properties.setProperty("build.os", buildOS);
		properties.setProperty("build.java", buildJava);
		properties.setProperty("build.date", (new Date()).toString());
		properties.setProperty("developer", developer);
	}
	
	public void restore(Properties properties)
	{
		majorRelease = Integer.parseInt(properties.getProperty("major.release"));
		minorRelease = Integer.parseInt(properties.getProperty("minor.release"));
		majorVersion = Integer.parseInt(properties.getProperty("major.version"));
		minorVersion = Integer.parseInt(properties.getProperty("minor.version"));
		build = Integer.parseInt(properties.getProperty("build"));
	}
}


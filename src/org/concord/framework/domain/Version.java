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


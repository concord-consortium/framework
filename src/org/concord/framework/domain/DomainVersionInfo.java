package org.concord.framework.domain;

import java.util.Date;

public class DomainVersionInfo
{
	protected int majorRelease = 1;
	protected int minorRelease = 0;
	protected int majorVersion = 1;
	protected int minorVersion = 0;
	protected int build = 0;
	protected String buildOS = System.getProperty("os.name");
	protected String buildJava = System.getProperty("java.version");
	protected String developer = System.getProperty("user.name");
	protected String buildDate = (new Date()).toString();
	public static final String [][] beanInfoProperties =
	{
		{ "majorRelease", "Major release" },
		{ "minorRelease", "Minor release" },
		{ "majorVersion", "Major version" },
		{ "minorVersion", "Minor version" },
		{ "buildNumber", "Build number" },
		{ "buildOS", "Build OS" },
		{ "buildJava", "Build Java" },
		{ "buildDate", "Build date" },
		{ "developer", "Developer" },
	};
	
	public int getMajorRelease()
	{
		return majorRelease;
	}
	
	public void setMajorRelease(int value)
	{
		majorRelease = value;
	}
	
	public int getMinorRelease()
	{
		return minorRelease;
	}
	
	public void setMinorRelease(int value)
	{
		minorRelease = value;
	}
	
	public int getMajorVersion()
	{
		return majorVersion;
	}
	
	public void setMajorVersion(int value)
	{
		majorVersion = value;
	}
	
	public int getMinorVersion()
	{
		return minorVersion;
	}
	
	public void setMinorVersion(int value)
	{
		minorVersion = value;
	}
	
	public int getBuildNumber()
	{
		return build;
	}
	
	public String getBuildOS()
	{
		return buildOS;
	}
	
	public String getBuildJava()
	{
		return buildJava;
	}
	
	public String getBuildDate()
	{
		return buildDate;
	}
	
	public String getDeveloper()
	{
		return developer;
	}
	
	public void incrementBuild()
	{
		incrementBuild(1);
	}
	
	public void incrementBuild(int amount)
	{
		build += amount;
	}
	
	public String getVersionAsString(){
	    String suffix = "\" ";
	    StringBuffer sb = new StringBuffer();
		sb.append("majorRelease=\""+getMajorRelease()+suffix);
		sb.append("minorRelease=\""+getMinorRelease()+suffix);
		sb.append("majorVersion=\""+getMajorVersion()+suffix);
		sb.append("minorVersion=\""+getMinorVersion()+suffix);
		sb.append("build=\""+getBuildNumber()+suffix);
		sb.append("buildOS=\""+getBuildOS()+suffix);
		sb.append("buildJava=\""+getBuildJava()+suffix);
		sb.append("developer=\""+getDeveloper()+suffix);
		sb.append("buildDate=\""+getBuildDate()+suffix);
		return sb.toString();
	}
}


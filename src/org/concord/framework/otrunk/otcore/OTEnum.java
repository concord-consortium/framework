package org.concord.framework.otrunk.otcore;

public interface OTEnum extends OTType {
	public boolean validInt(int testInt);
	public boolean validName(int testName);
	
	public Object getValue(int valueOrdinal);
	public Object getValue(String valueName);

}

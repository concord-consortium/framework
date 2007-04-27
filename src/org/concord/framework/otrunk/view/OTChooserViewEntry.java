package org.concord.framework.otrunk.view;

public interface OTChooserViewEntry extends OTViewEntry {
	
	public String getPropertyName();
	public void setPropertyName(String attributeName);
    
    public String getFinalViewMode();
    public void setFinalViewMode(String finalViewMode);
}

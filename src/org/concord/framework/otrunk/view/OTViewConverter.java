package org.concord.framework.otrunk.view;

public interface OTViewConverter {
	public Class<?> getFromType();
	
	public Class<?> getToType();
	
	public OTView convert(OTView original, OTViewFactory factory, OTViewEntry viewEntry);
}

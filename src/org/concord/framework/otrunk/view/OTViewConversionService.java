package org.concord.framework.otrunk.view;

public interface OTViewConversionService 
{
	public void addConverter(OTViewConverter converter);
	
	public boolean canConvert(OTView originalView, Class<? extends OTView> outputClass);
	
    public <T extends OTView> T convert(OTView originalView, Class<T> outputClass,
        	OTViewFactory viewFactory, org.concord.framework.otrunk.view.OTViewEntry viewEntry);
}

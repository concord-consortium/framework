package org.concord.framework.otrunk.view;

import java.awt.Component;

public interface OTMainComponentProvider extends OTDefaultComponentProvider {
	public Component getMainComponent();
	public Component[] getAllComponents();
}

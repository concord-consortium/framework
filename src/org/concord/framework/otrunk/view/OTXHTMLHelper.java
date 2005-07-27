package org.concord.framework.otrunk.view;

import javax.swing.JComponent;

import org.concord.framework.otrunk.OTObject;

public interface OTXHTMLHelper {
	public String embedComponent(JComponent comp, float scaleX, float scaleY, OTObject otObject);
	public String embedOTObject(OTObject obj);
}

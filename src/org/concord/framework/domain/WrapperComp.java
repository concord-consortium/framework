/*
 * Created on Apr 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.concord.framework.domain;

/**
 * @author dima
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
import java.awt.Component;

public interface WrapperComp
{
	public Component wrapComponent(Component comp);
	public String getOriginalClassName();
}

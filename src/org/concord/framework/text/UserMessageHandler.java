/*
 * Created on Jun 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.concord.framework.text;

/**
 * @author scott
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface UserMessageHandler {
	public int showOptionMessage(String message, String title, String [] options, 
			String defaultOption);

	public void showMessage(String message, String title);
}

/*
 * Created on Aug 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.concord.framework.util;


/**
 * @author scott
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface SimpleTreeNode 
{
	public String toString();
	
	public int getChildCount();
	
	public int getIndexOfChild(SimpleTreeNode child);
	
	public SimpleTreeNode getChild(int index);
	
	public void setName(String name);
	
	public Object getObject();
}

package org.concord.framework.otrunk.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.concord.framework.otrunk.OTObject;

public class OTObjectTree extends JTree {
	private static final long serialVersionUID = 1L;

	public OTObjectTree() {
		super();
	}
	
	public OTObjectTree(TreeNode node) {
		super(node);
	}

	@Override
	public String convertValueToText(Object value, boolean selected,
			boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		if(value != null) {
			String sValue = null;
			if (value instanceof DefaultMutableTreeNode) {
				value = ((DefaultMutableTreeNode)value).getUserObject();
				if (value instanceof OTObject) {
					sValue = getOTObjectString((OTObject) value);
				} else {
					sValue = value.toString();
				}
			} else {
				sValue = value.toString();
			}
			if (sValue != null) {
				return sValue;
			}
		}
		return "";
	}
	
	private String getOTObjectString(OTObject obj) {
		String shortClassStr = obj.otClass().getName();
		shortClassStr = shortClassStr.substring(shortClassStr.lastIndexOf(".")+1, shortClassStr.length());
		return shortClassStr + " -- " + obj.getName() + " -- " + obj.getGlobalId();
	}
}

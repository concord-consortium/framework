/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2006-05-05 15:44:33 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.util;

import java.awt.Color;
import java.util.Vector;

public interface CheckedColorTreeModel
{
    /**
     * This is used when an item is created or renamed in the 
     * dialogs.  It should be localized.
     * 
     * @return
     */
    public String getItemTypeName();
    
    public Vector getItems(Object parent);
    
    public Object addItem(Object parent, String name, Color color);

    public Object removeItem(Object parent, Object item);
    
    public void setSelectedItem(Object item, boolean checked);
    
    public void updateItems();
    
    public Color getItemColor(Object item);
    
    public String getItemLabel(Object item);
    
    public void setItemLabel(Object item, String label);
    
    public void setItemChecked(Object item, boolean checked);
    
    public Color getNewColor();
}

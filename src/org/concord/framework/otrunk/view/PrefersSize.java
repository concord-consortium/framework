package org.concord.framework.otrunk.view;

import java.awt.Dimension;

public interface PrefersSize {
    /**
     * Calculate and return a desired size based on a maximum possible size passed in.
     * The returned Dimension should be smaller than or equal to the passed in maxSize (ie ret.width <= maxSize.width && ret.height <= maxSize.height)
     * 
     * @param maxSize The outer bounds this object can be displayed in
     * @return Dimension The requested dimensions this view would prefer. Must be smaller or equal to maxSize.
     */
    public Dimension requestPreferredSize(Dimension maxSize);
}

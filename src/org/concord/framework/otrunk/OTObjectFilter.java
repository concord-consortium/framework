package org.concord.framework.otrunk;

import java.util.Iterator;
import java.util.Vector;

/**
 * A simple abstract class for being able to define a filtering mechanism for an OTObject or OTObjectList
 * 
 * @author aunger
 */
public abstract class OTObjectFilter {

	abstract public boolean keepObject(OTObject obj);
	
	public Vector filterList(OTObjectList list) {
		Vector originalList = list.getVector();
		return filterList(originalList);
	}
	
	public Vector filterList(Vector list) {
		Vector filtered = new Vector();
		Iterator items = list.iterator();
		while (items.hasNext()) {
			OTObject obj = (OTObject) items.next();
			if (keepObject(obj)) {
				filtered.add(obj);
			}
		}
		return filtered;
	}
}

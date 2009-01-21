package org.concord.framework.otrunk;

import java.util.Vector;

/**
 * A simple abstract class for being able to define a filtering mechanism for an OTObject or OTObjectList
 * 
 * @author aunger
 */
public abstract class OTObjectFilter {

	abstract public boolean keepObject(OTObject obj);
	
	public Vector<OTObject> filterList(OTObjectList list) {
		Vector<OTObject> originalList = list.getVector();
		return filterList(originalList);
	}
	
	public Vector<OTObject> filterList(Vector<OTObject> list) {
		Vector<OTObject> filtered = new Vector<OTObject>();
		for (OTObject obj : list) {
			if (keepObject(obj)) {
				filtered.add(obj);
			}
		}
		return filtered;
	}
}

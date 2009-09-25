package org.concord.framework.otrunk.view;

import java.util.Vector;

import org.concord.framework.otrunk.OTChangeListener;
import org.concord.framework.otrunk.OTObject;

public interface OTLabbookManager
{
	/*
	 * This object will be added directly to an entry, so the original should be cloned, preferably
	 * using the copy method in the OTLabbookViewProvider for the object
	 */ 
	public void add(OTObject otObject);
	
	/*
	 * If a refence to the original object is included, it will be easier to tie the entry back
	 * to its origins.
	 */
	public void add(OTObject otObject, OTObject originalObject);
	
	/*
	 * A container, such as the section the object was in, should be included to allow the lab
	 * book to sort based on contaner.
	 */
	public void add(OTObject otObject, OTObject originalObject, OTObject container);
	
	public void addSnapshot(OTObject snapshot);
	
	public void addDataCollector(OTObject dataCollector);
	
	public void addDrawingTool(OTObject drawingTool);
	
	public void addText(OTObject text);
	
	public void remove(OTObject labbookEntry);
	
	public Vector getSnapshots();
	
	public Vector getGraphs();
	
	public Vector getDrawings();
	
	public Vector getText();

	public Vector getAllEntries();
	
	public boolean isEmpty();
	
	public void addLabbookListener(OTChangeListener listener);
	
	public void removeLabbookListener(OTChangeListener listener);
	
	public boolean getEmbedInDrawTool();
	
}

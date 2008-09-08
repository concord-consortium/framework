package org.concord.framework.otrunk.view;

import java.util.Vector;

import org.concord.framework.otrunk.OTChangeListener;
import org.concord.framework.otrunk.OTObject;
import org.concord.framework.otrunk.OTObjectService;

public interface OTLabbookManager
{
	public void add(OTObject otObject);
	
	public void add(OTObject otObject, OTObject container);
	
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

	public void setOTObjectService(OTObjectService objectService);
	
}

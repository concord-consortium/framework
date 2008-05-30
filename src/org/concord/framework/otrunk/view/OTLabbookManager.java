package org.concord.framework.otrunk.view;

import java.util.Vector;

import org.concord.framework.otrunk.OTObject;

public interface OTLabbookManager
{
	public void add(OTObject otObject);
	
	public void addSnapshot(OTObject snapshot);
	
	public void addDataCollector(OTObject dataCollector);
	
	public void addDrawingTool(OTObject drawingTool);
	
	public void addQuestion(OTObject question);
	
	public void remove(OTObject labbookEntry);
	
	public Vector getSnapshots();
	
	public Vector getGraphs();
	
	public Vector getDrawings();
	
	public Vector getQuestions();

	public Vector getAllEntries();
	
	public OTObject getSectionsClass();
}

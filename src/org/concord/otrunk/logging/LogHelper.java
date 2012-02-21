package org.concord.otrunk.logging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.concord.framework.otrunk.OTObject;
import org.concord.framework.otrunk.OTObjectList;
import org.concord.framework.util.TimeProvider;
import org.concord.otrunk.logging.OTModelEvent.EventType;

public class LogHelper {
	/**
	 * Add a new event of the passed in type
	 * @param model
	 * @param type
	 */
	public static void add(OTModelLogging model, EventType type) {
		add(model, type, null);
	}
	
	/**
	 * Add a new event of the passed in type, with extra details attached
	 * @param model
	 * @param type
	 * @param details
	 */
	public static void add(OTModelLogging model, EventType type, HashMap<String, OTObject> details) {
		try {
		    TimeProvider timeProvider = model.getOTObjectService().getOTrunkService(TimeProvider.class);
			OTModelEvent item = model.getOTObjectService().createObject(OTModelEvent.class);
			item.setType(type);
			item.setTimestamp(timeProvider.currentTimeMillis());
			
			if (details != null) {
    			for (Entry<String, OTObject> e : details.entrySet()) {
    			    item.getInfo().putObject(e.getKey(), e.getValue());
    			}
			}
			model.getLog().add(item);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getNumStarts(OTModelLogging model) {
		return getNumEvents(model, EventType.START);
	}
	
	public static int getNumPlaybackStarts(OTModelLogging model) {
		return getNumEvents(model, EventType.PLAYBACK_START);
	}
	
	public static int getNumResets(OTModelLogging model) {
		return getNumEvents(model, EventType.RESET);
	}
	
	public static int getNumEvents(OTModelLogging model, EventType type) {
		ArrayList<OTModelEvent> events = getEvents(model, type);
		return events.size();
	}
	
	public static long getTotalCollectionTime(OTModelLogging model) {
		OTObjectList items = model.getLog();
		long sum = 0;
		long start = 0;
		for (int i = 0; i < items.size(); ++i) {
		    OTModelEvent item = (OTModelEvent) items.get(i);
		    if (item != null) {
    			EventType name = item.getType();
    			if (name != null) {
        			if (name.equals(EventType.START)) {
        				start = item.getTimestamp();
        			}
        			else if (name.equals(EventType.STOP)) {
        				sum += item.getTimestamp() - start;
        			}
    			}
		    }
		}
		return sum;
	}
	
	public static ArrayList<OTModelEvent> getEvents(OTModelLogging model, EventType type) {
		return getEvents(model, type, -2); // default time is -1, so use -2 to include all events
	}
	
	public static ArrayList<OTModelEvent> getEvents(OTModelLogging model, EventType type, long afterTime) {
		OTObjectList items = model.getLog();
		ArrayList<OTModelEvent> events = new ArrayList<OTModelEvent>();
		for (int i = 0; i < items.size(); ++i) {
		    OTModelEvent item = (OTModelEvent) items.get(i);
			if (item != null && item.getType() != null && item.getType().equals(type) && item.getTimestamp() > afterTime) {
				events.add(item);
			}
		}
		return events;
	}
	
	public static ArrayList<OTObject> getExtraInfo(ArrayList<OTModelEvent> events, String infoKey) {
		ArrayList<OTObject> infoItems = new ArrayList<OTObject>();
		for (OTModelEvent event : events) {
			OTObject obj = getExtraInfo(event, infoKey);
			if (obj != null) {
				infoItems.add(obj);
			}
		}
		return infoItems;
	}
	
	public static OTObject getExtraInfo(OTModelEvent event, String infoKey) {
		return event.getInfo().getObject(infoKey);
	}
}

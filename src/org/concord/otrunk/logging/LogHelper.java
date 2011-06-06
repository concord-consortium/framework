package org.concord.otrunk.logging;

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
	
	public static int getNumCollections(OTModelLogging model) {
		OTObjectList items = model.getLog();
		int cnt = 0;
		for (int i = 0; i < items.size(); ++i) {
		    OTModelEvent item = (OTModelEvent) items.get(i);
			if (item != null && item.getType() != null && item.getType().equals(EventType.START)) {
				++cnt;
			}
		}
		return cnt;
	}
	
	public static long getTotalCollectionTime(OTModelLogging model) {
		OTObjectList items = model.getLog();
		long sum = 0;
		long start = 0;
		for (int i = 0; i < items.size(); ++i) {
		    OTModelEvent item = (OTModelEvent) items.get(i);
			EventType name = item.getType();
			if (name.equals(EventType.START)) {
				start = item.getTimestamp();
			}
			else if (name.equals(EventType.STOP)) {
				sum += item.getTimestamp() - start;
			}
		}
		return sum;
	}
}

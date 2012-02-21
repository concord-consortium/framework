package org.concord.otrunk.logging;

import org.concord.framework.otrunk.OTObjectInterface;
import org.concord.framework.otrunk.OTObjectMap;
import org.concord.framework.otrunk.OTResourceMap;

public interface OTModelEvent extends OTObjectInterface {
    /**
     * See http://ccl.northwestern.edu/netlogo/docs/logging.html
     * for a description of each event type and what types of things
     * it will log.
     * @author aunger
     *
     */
    public static enum EventType {
    	// NOTE: If you change any of these, you'll mess up any generated student data, since the learner
    	// data format saves these as strings.
    	
        // types of events MW logs
        MW_EVENT,
        // types of events Netlogo logs
        NL_GLOBAL, NL_GREEN, NL_CODE, NL_WIDGET, NL_BUTTON, NL_SPEED_SLIDER, NL_TURTLE, NL_LINK,
        // some default types for startable models
        START, STOP, RESET,
        // for playback
        PLAYBACK_START, PLAYBACK_STOP, PLAYBACK_RESET
        
    }
    
    public EventType getType();
    public void setType(EventType type);
    
    public static long DEFAULT_timestamp = -1;
    public long getTimestamp();
    public void setTimestamp(long millis);
    
    @Deprecated
    /** use getInfo() instead */
    public OTResourceMap getDetails();
    
    public OTObjectMap getInfo();

}

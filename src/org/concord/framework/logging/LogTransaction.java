package org.concord.framework.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.concord.framework.text.*;

public final class LogTransaction
{
	public final static int	UNDEFINE_PRIORITY		= 	0;
	public final static int	LOW_PRIORITY			= 	1;
	public final static int	MIDDLE_PRIORITY			= 	50;
	public final static int	HIGH_PRIORITY			= 	100;


    public static final String ACTIVITY_PRIORITY_UNDEF 	= "undefined";
    public static final String ACTIVITY_PRIORITY_LOW 	= "low";
    public static final String ACTIVITY_PRIORITY_NORMAL	= "middle";
	public static final String ACTIVITY_PRIORITY_HIGH 	= "high";


	public final static int EMPTY_QUESTION 				= 1;
	public final static int TEXT_QUESTION 				= 2;
	public final static int MULTIPLE_CHOICE_QUESTION 	= 3;
	public final static int SURVEY_QUESTION 			= 4;

    public static final String QUESTION_TYPE_EMPTY 				= "empty";
    public static final String QUESTION_TYPE_TEXT 				= "text";
    public static final String QUESTION_TYPE_MULTIPLE_CHOICE	= "multiplechoice";
    public static final String QUESTION_SURVEY					= "survey";


    public static final String TRANSACTION_TRANSITION_STRING    = "transition";
    public static final String TRANSACTION_ACTION_STRING        = "action";
    public static final String TRANSACTION_QUESTION_STRING      = "question";
    public static final String TRANSACTION_OBSERVATION_STRING   = "observation";
    public static final String TRANSACTION_EVENT_STRING         = "event";
    public static final String TRANSACTION_START_ACT_STRING     = "startactivity";
    public static final String TRANSACTION_END_ACT_STRING       = "endactivity";
    public static final String TRANSACTION_START_SECTION_STRING = "startsection";
    public static final String TRANSACTION_END_SECTION_STRING   = "endsection";

    public static final int TRANSACTION_UNDEFINED_ID     = 0;
    public static final int TRANSACTION_TRANSITION_ID    = 1;
    public static final int TRANSACTION_ACTION_ID        = 2;
    public static final int TRANSACTION_QUESTION_ID      = 3;
    public static final int TRANSACTION_OBSERVATION_ID   = 4;
    public static final int TRANSACTION_EVENT_ID         = 5;
    public static final int TRANSACTION_START_ACT_ID     = 6;
    public static final int TRANSACTION_END_ACT_ID       = 7;
    public static final int TRANSACTION_START_SECTION_ID = 8;
    public static final int TRANSACTION_END_SECTION_ID   = 9;

    public final static String START_ACTIVITY_ACTION     = "START OF ACTIVITY";
    public final static String END_ACTIVITY_ACTION       = "END OF ACTIVITY";
    public final static String START_SECTION_ACTION      = "START OF SECTION:";
    public final static String END_SECTION_ACTION        = "END OF SECTION:";


    public static int getActionTransactionIDFromMesage(Message m){
        if(m == null) return TRANSACTION_ACTION_ID;
        String msg = m.getText();
        if(msg == null) return TRANSACTION_ACTION_ID;
        if(msg.indexOf(START_ACTIVITY_ACTION) >= 0){
            return TRANSACTION_START_ACT_ID;
        }
        if(msg.indexOf(END_ACTIVITY_ACTION) >= 0){
            return TRANSACTION_END_ACT_ID;
        }
        if(msg.indexOf(START_SECTION_ACTION) >= 0){
            return TRANSACTION_START_SECTION_ID;
        }
        if(msg.indexOf(END_SECTION_ACTION) >= 0){
            return TRANSACTION_END_SECTION_ID;
        }
        return TRANSACTION_ACTION_ID;
    }

    public static String getPriorityAsString(int priority){
    	int limit1 = (LOW_PRIORITY + MIDDLE_PRIORITY) / 2;
    	int limit2 = (MIDDLE_PRIORITY + HIGH_PRIORITY) / 2;
    	
    	if(priority <= UNDEFINE_PRIORITY){
    		return ACTIVITY_PRIORITY_UNDEF;
    	}else if(priority >= LOW_PRIORITY && priority < limit1){
    		return ACTIVITY_PRIORITY_LOW;
    	}else if(priority >= limit1 && priority < limit2){
    		return ACTIVITY_PRIORITY_NORMAL;
    	}
    	return ACTIVITY_PRIORITY_HIGH;
    }
    
    public static int getPriorityAsInt(String str){
    	if(str == null) return UNDEFINE_PRIORITY;
    	if(str.equalsIgnoreCase(ACTIVITY_PRIORITY_LOW)) return LOW_PRIORITY;
    	if(str.equalsIgnoreCase(ACTIVITY_PRIORITY_NORMAL)) return MIDDLE_PRIORITY;
    	if(str.equalsIgnoreCase(ACTIVITY_PRIORITY_HIGH)) return HIGH_PRIORITY;
    	return UNDEFINE_PRIORITY;
    }
    
    public static String validatePriority(String str){
    	if(str == null) return ACTIVITY_PRIORITY_NORMAL;
    	String strIntern = str.intern();
    	if(strIntern == ACTIVITY_PRIORITY_LOW) return str;
    	if(strIntern == ACTIVITY_PRIORITY_HIGH) return str;
    	return ACTIVITY_PRIORITY_NORMAL;
    }
    
    public static String getQuestionTypeAsString(int type){
    	String ret = QUESTION_TYPE_MULTIPLE_CHOICE;
    	switch (type){
    		case EMPTY_QUESTION:
    			ret = QUESTION_TYPE_EMPTY;
    			break;
    		case TEXT_QUESTION:
     			ret = QUESTION_TYPE_TEXT;
   				break;
   			case SURVEY_QUESTION:
     			ret = QUESTION_SURVEY;
   				break;
    		default:
    			break;
    	}
    	return ret;
    }
    public static int getQuestionTypeAsInt(String str){
    	if(str == null) return MULTIPLE_CHOICE_QUESTION;
    	if(str.equalsIgnoreCase(QUESTION_TYPE_EMPTY)) return EMPTY_QUESTION;
    	if(str.equalsIgnoreCase(QUESTION_TYPE_TEXT)) return TEXT_QUESTION;
    	if(str.equalsIgnoreCase(QUESTION_TYPE_MULTIPLE_CHOICE)) return MULTIPLE_CHOICE_QUESTION;
    	if(str.equalsIgnoreCase(QUESTION_SURVEY)) return SURVEY_QUESTION;
    	return MULTIPLE_CHOICE_QUESTION;
    }
    public static String validateQuestionType(String str){
    	if(str == null) return QUESTION_TYPE_MULTIPLE_CHOICE;
    	String strIntern = str.intern();
    	if(strIntern == QUESTION_TYPE_EMPTY) return str;
    	if(strIntern == QUESTION_TYPE_TEXT) return str;
    	if(strIntern == QUESTION_SURVEY) return str;
    	return QUESTION_TYPE_MULTIPLE_CHOICE;
    }    

    public static String createDateString(Date date){
    	return "<date> " + createDateElementContent(date) + " </date>";
    }


    public static String createDateElementContent(Date date){
    	return sortFormat.format(date) + "&nbsp;" + viewFormat.format(date);
    }
    protected static SimpleDateFormat sortFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    protected static SimpleDateFormat viewFormat = new SimpleDateFormat("MM/dd/yy | HH:mm:ss");

    public static String getTransactionAsString(int t){
        switch(t){
            case TRANSACTION_TRANSITION_ID:     return TRANSACTION_TRANSITION_STRING;
            case TRANSACTION_ACTION_ID:         return TRANSACTION_ACTION_STRING;
            case TRANSACTION_QUESTION_ID:       return TRANSACTION_QUESTION_STRING;
            case TRANSACTION_OBSERVATION_ID:    return TRANSACTION_OBSERVATION_STRING;
            case TRANSACTION_EVENT_ID:          return TRANSACTION_EVENT_STRING;
            case TRANSACTION_START_ACT_ID:      return TRANSACTION_START_ACT_STRING;
            case TRANSACTION_END_ACT_ID:        return TRANSACTION_END_ACT_STRING;
            case TRANSACTION_START_SECTION_ID:  return TRANSACTION_START_SECTION_STRING;
            case TRANSACTION_END_SECTION_ID:    return TRANSACTION_END_SECTION_STRING;
        }
        return null;
    }
    
    public static int getTransactionAsInt(String str){
        if(str == null) return TRANSACTION_UNDEFINED_ID;
        if(str.equalsIgnoreCase(TRANSACTION_TRANSITION_STRING)) return TRANSACTION_TRANSITION_ID;
        if(str.equalsIgnoreCase(TRANSACTION_ACTION_STRING)) return TRANSACTION_ACTION_ID;
        if(str.equalsIgnoreCase(TRANSACTION_QUESTION_STRING)) return TRANSACTION_QUESTION_ID;
        if(str.equalsIgnoreCase(TRANSACTION_OBSERVATION_STRING)) return TRANSACTION_OBSERVATION_ID;
        if(str.equalsIgnoreCase(TRANSACTION_EVENT_STRING)) return TRANSACTION_EVENT_ID;
        if(str.equalsIgnoreCase(TRANSACTION_START_ACT_STRING)) return TRANSACTION_START_ACT_ID;
        if(str.equalsIgnoreCase(TRANSACTION_END_ACT_STRING)) return TRANSACTION_END_ACT_ID;
        if(str.equalsIgnoreCase(TRANSACTION_START_SECTION_STRING)) return TRANSACTION_START_SECTION_ID;
        if(str.equalsIgnoreCase(TRANSACTION_END_SECTION_STRING)) return TRANSACTION_END_SECTION_ID;
        return TRANSACTION_UNDEFINED_ID;
    }

}


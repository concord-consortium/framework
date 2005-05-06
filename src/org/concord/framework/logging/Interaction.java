
/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package org.concord.framework.logging;

import org.concord.framework.text.Message;
import org.concord.framework.util.Indexable;

public class Interaction implements Indexable, DateContained{

public final static int INTERACTION_UNDEFINED   = 0;
public final static int INTERACTION_FIRST       = 1;
public final static int INTERACTION_LAST        = 2;
public final static int INTERACTION_MIDDLE      = 3;
public final static int INTERACTION_ACTION      = 4;
public final static int INTERACTION_ENTER       = 5;
public final static int INTERACTION_EXIT        = 6;
public final static int LAST_INTERACTION_TYPE   = INTERACTION_EXIT;

public final static String []InteractionTypes = {"unknown","first","last","middle","action","enter","exit"};


public final static String elementName = LogTransaction.TRANSACTION_INTERACTION; 
DateContainer 	date;
Message 		message;
int 			index = -1;
int             type = INTERACTION_UNDEFINED;
String          description;

	public void setDate(DateContainer date){this.date = date;}
	public void setDate(java.util.Date utildate){
		setDate(new DateContainer(utildate));
	}
	public DateContainer getDate(){return date;}

	public void setMessage(Message message){this.message = message;}

	public Message getMessage(){return message;}
	
	public void setIndex(String str){
	    int value = -1;
	    try{
	        value = Integer.parseInt(str);
	    }catch(Throwable t){
	        value = -1;
	    }
        setIndex(value);
	}
	public void setIndex(int index){this.index = index;}
	public int getIndex(){return index;}

	public void setDescription(String description){this.description = description;}
	public String getDescription(){return description;}

	public void setType(String str){
	    setType(getInteractionTypeFromString(str));
	}
	
	public void setType(int type){
	    this.type = type;
	    checkType();
	}
	
	protected void checkType(){
	    if(type < INTERACTION_UNDEFINED || type > LAST_INTERACTION_TYPE){
	        type = INTERACTION_UNDEFINED;
	    }
	}
	
	public static int getInteractionTypeFromString(String str){
	    if(str == null) return INTERACTION_UNDEFINED;
	    for(int i = 0; i < InteractionTypes.length; i++){
	        if(str.equalsIgnoreCase(InteractionTypes[i])) return i;
	    }
	    return INTERACTION_UNDEFINED;
	}
	
	public int getType(){return type;}

	public String getXMLLogString(){
		String eol = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		sb.append("<"+LogTransaction.TRANSACTION_INTERACTION);
		sb.append(" type=\""+InteractionTypes[getType()]+"\" index=\""+getIndex()+"\">");
		sb.append(eol);
		if(date != null){
    		sb.append(date.getXMLLogString());
		    sb.append(eol);
    	}
    	if(description != null){
    	    sb.append("<description>");
            sb.append(LoggableAdapter.wrapStringAsCDATA(description));
    	    sb.append("</description>");
    	}
        if(message != null){
    	    sb.append("<message>");
            sb.append(LoggableAdapter.wrapStringAsCDATA(message.getText()));
    	    sb.append("</message>");
        }		
		sb.append("</"+LogTransaction.TRANSACTION_INTERACTION+">");
		sb.append(eol);
    	return sb.toString();
	}

    public static Interaction createInteraction(java.util.Date needDate,int type,int index){
        if(needDate == null) return null;
        Interaction interaction = new Interaction();
        interaction.setDate(needDate);
        interaction.setType(type);
        interaction.setIndex(index);
        return interaction;
    }


}


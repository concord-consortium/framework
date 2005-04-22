
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
import java.util.Date;
import java.util.StringTokenizer;

public class DateContainer{
public final static String elementName = "date"; 
Date date;
protected static java.text.SimpleDateFormat sortFormat = new java.text.SimpleDateFormat("yyyy.MM.dd.kk.mm.ss");
protected static java.text.SimpleDateFormat viewFormat = new java.text.SimpleDateFormat("MM/dd/yy | kk:mm:ss");

	public DateContainer(Date date){
		setDate(date);
	}
	public DateContainer(String str){
		parseDate(str);
	}
	public void setDate(Date date){
		this.date = date;
	}
	public void setDate(String str){
		parseDate(str);
	}
	public Date getDate(){return date;}

	void parseDate(String str){
		if(str == null || str.length() < 1) return;
//2002.09.30.09.28.29
		try{
			StringTokenizer parser = new StringTokenizer(str,". \t");
			int year = Integer.parseInt(parser.nextToken()) - 1900;
			int month = Integer.parseInt(parser.nextToken()) - 1;
			int day = Integer.parseInt(parser.nextToken());
			int h = Integer.parseInt(parser.nextToken());
			int m = Integer.parseInt(parser.nextToken());
			int s = Integer.parseInt(parser.nextToken());
			date = new Date(year,month,day,h,m,s);
		}catch(NumberFormatException nfe){
			throw nfe;
		}catch(Exception e){
			System.out.println("parseDate Exception "+e+" string:  "+str);
		}		
	}


	public String toString(){
		if(date == null){
			return "Date not Specified";
		}
		String str = "Date: "+date.toString();
		return str;
	}
	
	public String getXMLLogString(){
		if(date == null) return "";
    	return "<date> " + sortFormat.format(date) + "&nbsp;" + viewFormat.format(date) + " </date>";
	}

}

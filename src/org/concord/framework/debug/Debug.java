

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
//
// Class: Debug
//
// Copyright © 1998, The Concord Consortium
//
// Original Author: Bob Miner
//
// $Revision: 1.3 $
// $Date: 2004-11-12 18:40:24 $
// $Author: eblack $
//

package org.concord.framework.debug;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Debug contains useful code and constants for debugging.<p>
 *
 * There are two methods that developers are expected to use for routine
 * debugging support during development. Assert() is used to test a condition
 * and, if the condition fails, log the message and terminate the thread that
 * called it. It should only be used in situations where failing the test is a
 * catastrophic condition. Its function does NOT change between debug and
 * release versions. The log() method will log an arbitrary string, and
 * is conditioned on a passed in "level" integer.  See the log level defintions
 * below for information on what level to use and when.
 *
 * @version		$Revision: 1.3 $ $Date: 2004-11-12 18:40:24 $
 * @author 		$Author: eblack $
 */
public class Debug
{
    /**
     * Debug constant.<p>
     *
     * Set this to true for normal, debuggable builds.<p>
     *
     * Set this to false for release, non-debuggable builds.<p>
     *
     * When this is false, the compiler will eliminate any code
     * wrapped in if(debug) { ... }.<p>
    **/
    public final static boolean 	DEBUG = true;
    
    // This is for dynamic debugging...
    public static boolean debug = false;

    /**
     * Constants used for the log level and in calls to log(). Note that if
     * you need an additional level constants, just take the next one available
     * and add it to the list with an appropriate comment.
     *
     * The first three log levels, NONE, ERROR and WARNING are global log levels
     * meaning they may be used by anyone and are available in the release version.
     * Setting Debug's log level to NONE means no log messages will be displayed,
     * to ERROR means only ERROR level log messages will be displayed and to WARNING
     * means that ERROR and WARNING level log messages will be displayed.
     *
     * The log levels that follow after the first three are for debug use only and
     * represent areas of functionality within Symposium. Setting Debug's log level
     * to one of these will cause only ERROR, WARNING and that level messages to be
     * displayed, filtering out all other debug messages.
    **/
    public final static int 		NONE 		= 0;	// indicates nothing is logged (for servers without displays)
    public final static int 		ERROR		= 1;	// catastrophic un-recoverable error
    public final static int 		WARNING		= 2;	// unexpected but recoverable error, important enough to be logged
    public final static int			INFO		= 3;	// general informative message

    // util levels
    public final static int 		UTIL		= 10;	// org.concord.util debug messages

    // biologica levels
    public final static int 		BTEST		= 20;	// org.concord.biologica.test debug messages
    public final static int 		BENGINE		= 21;	// org.concord.biologica.engine debug messages

    /**
     * The current log level - use to determine which log messages are written
     * to output. It is set to the INFO level by default, meaning it will
     * display ERROR, WARNING or INFO level log messages.
    **/
    private static int 				logLevel = INFO;

    // This array lets us strip the portion of object names which is extraneous from
    // the logging output. You may add your class name prefix if you wish.
    // For this array to work most effectively, always put a prefix which is a superset
    // of another prefix first in the array.  For example "org.concord.biologica.engine" should
    // preceed "org.concord.biologica."
    private static String [] prefixes = {"org.concord.util.",
                                         "org.concord.biologica.engine.",
                                         "org.concord.biologica.test.",
                                         "org.concord.biologica.sb",
                                         "org.concord."};

    /**
     * The print stream that we write to. This is System.out (stdout) initially, but
     * may be re-directed as appropriate via the setPrintWriter() method.  May be
     * null, meaning no output should be directed to this stream.
    **/
    private static PrintWriter 		myPrintWriter = new PrintWriter(System.out);

    /**
     * Indicates if we should close printer writer when a new one is set.
     * You don't want to do this for System.out or System.err, but do
     * want to close it for other printer writers.<p>
    **/
    private static boolean			closePrintWriterWhenDone = false;

    /**
     * A second print stream that we may write to in addition to myPrintWriter.  This
     * is null initially, meaning it won't be used, but it may be re-directed as
     * appropriate via the setPrintWriter2() method.  May be null, meaning no output
     * should be directed to this stream.
     *
    **/
    private static PrintWriter		myPrintWriter2 = null;

    /**
     * Indicates if we should close printer writer 2 when a new one is set.
     * You don't want to do this for System.out or System.err, but do
     * want to close it for other printer writers.<p>
    **/
    private static boolean			closePrintWriter2WhenDone = false;

    /**
     * Global instance of a Debug object. This is used so our writeString method
     * can be non-static, allowing it to be overridden in situations where streams
     * are not available (e.g. servlets)
    **/
    private static Debug			myInstance = new Debug();
    
    private static Vector debugList = new Vector();
    private static boolean partialMatch = false;

    // Methods.

    public static void setDebug(boolean value)
    {
        debug = value;
    }
    
    public static void addDebugging(String token)
    {
        debugList.addElement(token.intern());
    }
    
    public static void removeDebugging(String token)
    {
        debugList.removeElement(token.intern());
    }
    
    public static boolean isDebugging(Object object, String token)
    {
        if (debug)
        {
            if (object instanceof Class)
                token = ((Class) object).getName() + "." + token;
            else if (object instanceof String)
                token = ((String) object) + "." + token;
            else if (object != null)
                token = object.getClass().getName() + "." + token;
            if (partialMatch)
            {
                int n = debugList.size();
                for (int i = 0; i < n; i++)
                {
                    String test = (String) debugList.elementAt(i);
                    if (token.indexOf(test) > -1)
                        return true;
                }
            }
            else
                return debugList.contains(token.intern());
        }
        return false;
    }
    
    public static boolean isDebugging(String token)
    {
        if (debug)
            return debugList.contains(token.intern());
        return false;
    }
    
    public static void setPartialMatch(boolean partial)
    {
        partialMatch = partial;
    }
    
    public static boolean isPartialMatch()
    {
        return partialMatch;
    }
    
    /**
     * Get the log level. This makes it possible for outside debugging code
     * to also condition itself on Debug's log level;
     *
     * @return Current log level.
    **/
    public final static int getLogLevel()
    {
        return logLevel;
    }

    /**
     * Set the log level.
     *
     * @param level Level to which the logging system should be set (duh).
    **/
    public final static void setLogLevel(int level)
    {
        if (level < 0)
        {
            myInstance.writeString("Debug setLogLevel received negative log level", null);
        }
        else
        {
            logLevel = level;
            myInstance.writeString("Debug resetting log level to " + level, null);
        }
    }

    /**
     * Log a debug string to the system output if the passed in log level is
     * LOWER than or equal to the current log level OR if the Debug log level
     * is greater than INFO, the level passed in is INFO or less or equal
     * to the current Debug log level.
     *
     * If the level passed in is set to ERROR, this method will throw a run-time
     * exception to halt the calling thread (as this is a catastrophic error).
     *
     * @param 		messageLevel The logging level of this log message.
     * @param 		message The String which is the message itself.
     * @obj 		Object which will have it's name pre-pended to the message, may be null.
     * @exception	Error - if the error reported is catastrophic, no one should catch this
    **/
    public final static void log(int messageLevel, String message, Object obj)
    throws Error
    {
        // Determine if we should display the message.
        if (DEBUG)
        {
            // In debug mode, display the message in two cases:
            //	1. If the messageLevel is INFO or less and the
            //     current logLevel is greater than or equal to
            //     the messageLevel.
            //  2. If the messageLevel is greater than INFO
            //     and the current logLevel equals the messageLevel.
            if (messageLevel <= INFO && messageLevel <= logLevel)
            {
                if (messageLevel == ERROR)
                    myInstance.writeString("ERROR: " + message, obj);
                else if (messageLevel == WARNING)
                    myInstance.writeString("WARNING: " + message, obj);
                else if (messageLevel == INFO)
                    myInstance.writeString("INFO: " + message, obj);
                else
                    myInstance.writeString(message,obj);
            }
            else if (messageLevel > INFO && messageLevel == logLevel)
            {
                myInstance.writeString(message,obj);
            }
        }
        else
        {
            // In release mode, we never display messages of a level
            // greater than INFO.  So only display INFO level
            // messages or less and only if it is less than or equal
            // to our log level.
            if (messageLevel <= INFO && messageLevel <= logLevel)
            {
                if (messageLevel == ERROR)
                    myInstance.writeString("ERROR: " + message, obj);
                else if (messageLevel == WARNING)
                    myInstance.writeString("WARNING: " + message, obj);
                else if (messageLevel == INFO)
                    myInstance.writeString("INFO: " + message, obj);
                else
                    myInstance.writeString(message,obj);
            }
        }
    }

    /**
     * Print out a message to our output stream. Note that this ALWAYS writes to
     * the output stream - it is intended to be used for start-up information etc.
     * that is always desirable.
    **/
    public final static void println(String message)
    {
        myInstance.writeString(message, null);
    }

    /**
     * Print a stack trace for the exception passed in to the logging stream.
     * This method should be used rather than calling exception.printStackTrace
     * so the trace can be written to the Debug stream. Note that there is no
     * level associated with this method as it is assumed a stack trace is always
     * wanted regardless of the level.
     *
     * @param e Throwable - exception to print stack trace for
    **/
    public final static void printStackTrace(Throwable e)
    {
        ByteArrayOutputStream out_stream = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(out_stream);
        e.printStackTrace(out);
        myInstance.writeString(out_stream.toString(), null);
    }

    /**
     * Set the instance of Debug that we use when calling writeString() - this
     * allows writeString() to be overridden for situations where there are no
     * streams (e.g. servlets).
     *
     * @param newInstance Debug - instance of Debug (or subclass) to use
    **/
    public final static void setDebugInstance(Debug newInstance)
    {
        myInstance = newInstance;
    }

    /**
     * Set the printStream to print logging output to. Output defaults to
     * System.out initially.  Closes old print stream if one exists.
     *
     * @param 	pw PrintWriter - new print writer, may be null
     * @param	closeWhenDone boolean - should the print writer be closed when a new one is set?
    **/
    public final static void setPrintWriter (PrintWriter pw, boolean closeWhenDone)
    {
        // Close existing stream
        if (myPrintWriter != null)
        {
            myPrintWriter.flush();
            if (closePrintWriterWhenDone)
            {
                myPrintWriter.close();
            }
        }

        // Set new print writer, which may be null
        myPrintWriter = pw;
        closePrintWriterWhenDone = closeWhenDone;
    }

    /**
     * Set the printStream2 to print logging output to. Output defaults to
     * null initially.  Closes old print stream if one exists.
     *
     * @param	pw2 PrintWriter - new print writer 2, may be null
     * @param	closeWhenDone boolean - should the print writer be closed when a new one is set?
    **/
    public final static void setPrintWriter2 (PrintWriter pw2, boolean closeWhenDone)
    {
        // Close existing stream
        if (myPrintWriter2 != null)
        {
            myPrintWriter2.flush();
            if (closePrintWriter2WhenDone)
            {
                myPrintWriter2.close();
            }
        }

        // Set new stream, which may be null
        myPrintWriter2 = pw2;
        closePrintWriter2WhenDone = closeWhenDone;
    }

    protected final static PrintWriter getPrintWriter()
        { return myPrintWriter; }

    protected final static PrintWriter getPrintWriter2()
        { return myPrintWriter2; }

    /**
     * Send a string to our print stream for debugging purposes. Prepend the object's name.
     * Remove the name qualifier on the object for better readability if that qualifier is
     * one of the strings in the "prefixes" array (private variable).
     *
     * Note that this is non-static (all other methods are static) to allow it to be
     * overridden for situations where streams are not available (e.g. servlets).
     *
     * @param message String to be output.
     * @param obj Object whose name will be prepended to the message.
    **/
    protected void writeString(String message, Object obj)
    {
        String 	s;
        String 	name;
        Date	now;

        now = new Date();
        if (obj != null)
        {
            name = obj.getClass().getName();
            for (int i = 0; i < prefixes.length; i++)
            {
                if (name.startsWith(prefixes[i]))
                {
                    name = name.substring(prefixes[i].length());
                }
            }
            s = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.LONG).format(now) + " - " + name + ": " + message;
        }
        else
        {
            s = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.LONG).format(now) + " - " + message;
        }

        String indent = "";
        int length = s.length();
        int start = 0;
        int charsPrinted;
        int end;

        while (length > 0)
        {
            charsPrinted = 100;
            if (length > charsPrinted)
            {
                end = start + charsPrinted;

                // Try to find a space in next 10 chars or so
                while (s.charAt(end) != ' ' && end < (s.length() - 1))
                {
                    end++;
                    charsPrinted++;
                    if (charsPrinted > 109)
                        break;
                }
            }
            else
            {
                end = start + (length);
            }

            if (myPrintWriter != null)
            {
                myPrintWriter.println(indent + s.substring(start, end));
                myPrintWriter.flush();
            }
            
            if (myPrintWriter2 != null)
            {
                myPrintWriter2.println(indent + s.substring(start, end));
                myPrintWriter2.flush();
            }

            indent += "  ";
            length -= charsPrinted;
            start += charsPrinted;
        }
    }
}


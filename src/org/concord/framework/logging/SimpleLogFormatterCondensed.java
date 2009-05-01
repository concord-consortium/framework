package org.concord.framework.logging;

import java.util.logging.LogRecord;

public class SimpleLogFormatterCondensed extends SimpleLogFormatter 
{
	@Override
	protected String getSourceClassString(LogRecord record) {
		String sourceClassName = record.getSourceClassName();
		int dotIndex = sourceClassName.lastIndexOf('.');
		if(dotIndex != -1){
			sourceClassName = sourceClassName.substring(dotIndex+1);
		}
		return sourceClassName;
	}
}

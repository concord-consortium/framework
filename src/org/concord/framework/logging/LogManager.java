package org.concord.framework.logging;

public interface LogManager
{
    void logAction(int level, String message);
    void logAction(int level, String message, int priority);
    void logLoggable(Loggable loggable,LogHintMessage hint);
    void close();
}


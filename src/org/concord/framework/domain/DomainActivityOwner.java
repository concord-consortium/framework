package org.concord.framework.domain;

import org.concord.framework.logging.LogManager;

public interface DomainActivityOwner
{
    String getActivityName();
    String getActivityTagName();
    java.util.Vector getDomainUsers();
    DomainProxyInfo getProxyInfo();
    long getActivityCreationTime();
    DomainVersionInfo  getDomainVersionInfo();
    int getActivityInstanceID();
    int getActivityID();
    int getTopicID();
    LogManager getLogManager();
    
    
    void closeEverything();
}


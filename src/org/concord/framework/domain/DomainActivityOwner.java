package org.concord.framework.domain;

import java.util.Vector;

import org.concord.framework.logging.LogManager;

public interface DomainActivityOwner
{
    public String getActivityName();
    public String getActivityTagName();
    public Vector getDomainUsers();
    public DomainProxyInfo getProxyInfo();
    public long getActivityCreationTime();
    public DomainVersionInfo  getDomainVersionInfo();
    public int getActivityInstanceID();
    public int getActivityID();
    public int getTopicID();
    public LogManager getLogManager();
    void closeEverything();
}


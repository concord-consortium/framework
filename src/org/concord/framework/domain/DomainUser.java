package org.concord.framework.domain;


public interface DomainUser
{
    String  getUserName();
    String  getUserGender();
    int     getUserID();
    String  getPassword();
    String  getEncryptedBlowfishKey();
    String  getEncryptedPrivateKey();
}


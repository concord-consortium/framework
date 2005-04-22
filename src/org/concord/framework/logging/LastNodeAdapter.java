package org.concord.framework.logging;

public class  LastNodeAdapter implements LastNodeListener{

    public LastNodeAdapter(){
    }
    
    public void notifyAboutLastNode(String nodeName){
        System.out.println("NODE "+nodeName);
    }
}

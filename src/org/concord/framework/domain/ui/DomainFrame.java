package org.concord.framework.domain.ui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.lang.reflect.*;
import java.util.Vector;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.concord.framework.closehelper.*;
import org.concord.framework.logging.LogManager;
import org.concord.framework.domain.DomainActivityOwner;
import org.concord.framework.domain.DomainOpener;
import org.concord.framework.domain.DomainView;
import org.concord.framework.domain.DomainEngine;


public class DomainFrame 
extends JFrame
implements ApplCloseHandlerListener, DomainView
{
	protected DomainActivityOwner domainActivityOwner;
	protected DomainOpener domainOpener;
	protected LogManager logManager;

	public DomainFrame()
	{
		super();
        setIconImage(org.concord.framework.domain.DomainUtil.FRAME_ICON.getImage());

	}
	
	public DomainFrame(String title)
	{
		super(title);
        setIconImage(org.concord.framework.domain.DomainUtil.FRAME_ICON.getImage());

	}
	
	protected WindowListener windowListener = new WindowAdapter()
	{
		public void windowClosing(WindowEvent event)
		{
			if (exitOnClose)
			{
				closeEverything();
				dispose();
        		org.concord.framework.domain.DomainUtil.systemExit();
			}
			else
			{
				closeEverything();
				setVisible(false);
				dispose();
			}
		}
	    public void windowOpened(WindowEvent event)
	    {
//	        if(RunActivity.pacifier != null) RunActivity.pacifier.stop();
	        if(domainOpener != null) domainOpener.closeEverything();
	    }
	};
	
	protected boolean exitOnClose = defaultExitOnClose();
	
	protected boolean defaultExitOnClose()
	{
		addWindowListener(windowListener);
		return !org.concord.framework.domain.DomainUtil.isSingleVM();
	}
	
	public void setExitOnClose(boolean value)
	{
		exitOnClose = value;
	}
	
	public boolean isExitOnClose()
	{
		return exitOnClose;
	}
	
	public boolean isFocusTraversable()
	{
		return true;
	}
	
	public void addNotify()
	{
		super.addNotify();
		ApplCloseHandler appHandler = ApplCloseHandlerFactory.getApplCloseHandler();
		if(appHandler != null)
		{
			appHandler.registerHandlers(this);
		}
		
	}
	
	public LogManager getLogManager(){return logManager;}
	public void setLogManager(LogManager logManager){this.logManager = logManager;}
	
	public DomainOpener getDomainOpener(){return domainOpener;}
	public void setDomainOpener(DomainOpener domainOpener){this.domainOpener = domainOpener;}
	
	public void closeEverything()
	{
		if(domainActivityOwner != null)
		{
			domainActivityOwner.closeEverything();
		}
		if(logManager != null) logManager.close();
		removeWindowListener(windowListener);
	}


	public void handleQuit()
	{
		closeEverything();
        org.concord.framework.domain.DomainUtil.systemExit();
		if(org.concord.framework.domain.DomainUtil.isSingleVM())
		{
			setVisible(false);
		}
	}
	
	
	public void closeActivityFrame()
	{
		closeEverything();
        org.concord.framework.domain.DomainUtil.systemExit();
		if(org.concord.framework.domain.DomainUtil.isSingleVM())
		{
			setVisible(false);
		}
	}
	
	public DomainView create(String viewSpecification)
	{
		return this;
	}
	public void release(DomainView view){}
	
	public Vector getViewMethods()
	{
		return null;
	}
	
	public Vector getViewEvents()
	{
		return null;
	}
	
	public Vector getViewActions()
	{
		return null;
	}
	
	public DomainEngine getEngine()
	{
		return null;
	}
	
	public void setDomainActivityOwner(Object domainActivityOwner)
	{
		if(domainActivityOwner instanceof DomainActivityOwner)
		{
			this.domainActivityOwner = (DomainActivityOwner)domainActivityOwner;
		}
	}
	
	public void save(Writer writer)
	{
	}
	
	public void restore(Reader reader)
	{
	}
	
	public void save(OutputStream output)
	{
	}
	
	public void restore(InputStream input)
	{
	}
	
	public Element serializeAsElement(Document document)
	{
		return null;
	}

}


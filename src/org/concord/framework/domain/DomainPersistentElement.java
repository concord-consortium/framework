package org.concord.framework.domain;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public interface DomainPersistentElement
{
	public void save(Writer writer);
	public void restore(Reader reader);
	public void save(OutputStream output);
	public void restore(InputStream input);
	
	public Element serializeAsElement(Document document);
	public Object restoreFromElement(Element xmlElement);
}


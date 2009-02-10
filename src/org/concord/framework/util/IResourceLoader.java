package org.concord.framework.util;

import java.io.InputStream;
import java.net.URL;

/**
 * This interface should describe the public methods of net.sf.sail.core.net.ResourceLoader.
 * ResourceLoader doesn't depend on this interface in order to keep the SAIL code from depending on OTrunk.
 * This interface is likewise defined here in order to keep the OTrunk code from depending on SAIL.
 * @author aunger
 *
 */
public interface IResourceLoader {
	public InputStream getRemoteResource(URL resourceUrl);
	public long getLastModified();
}

package org.concord.framework.otrunk.wrapper;

import java.net.URL;

import org.concord.framework.otrunk.OTObjectInterface;

/**
 * OTBlob is an OTrunk wrapper around a byte array. Src can contain either a
 * plain URL or a bytearray. A plain URL will be transformed into a byte array
 * under the hood when requested.
 * 
 * @author sfentress
 *
 */
public interface OTBlob
    extends OTObjectInterface
{
	/**
	 * This can be either a plain URL or a byte array. Either will return
	 * a byte array when requested.
	 * 
	 * @return
	 */
	public byte[] getSrc();
	
	public void setSrc(byte[] src);
	
	public void setSrc(URL src);
}

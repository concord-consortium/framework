/*
 * Last modification information:
 * $Revision: 1.2 $
 * $Date: 2004-10-26 17:27:21 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;

import org.concord.framework.data.DataDimension;


/**
 * DataChannelDescription
 * Class name and description
 *
 * Date created: Aug 24, 2004
 *
 * @author imoncada<p>
 *
 */
public class DataChannelDescription
{
	private String name;
	
	private DataDimension unit = null;
	private float tuneValue = 1.0f;

	// power of 10 precision
	private int precision;

	// The absolute min and max of the data or NaN if
	// not available
	private float absoluteMin, absoluteMax;

	// The recommended min and max of the data or NaN if
	// not available
	private float recommendMin, recommendMax;
	
	//This indicates if the data is numeric, so the precision will have to be used, etc
	private boolean numericData = true;

	/**
	 * 
	 */
	public DataChannelDescription()
	{
		this("");
	}

	/**
	 * 
	 */
	public DataChannelDescription(String name)
	{
		this.name = name;
	}
	
	public void	setUnit(DataDimension unit)
	{
		this.unit = unit;
	}

	public DataDimension getUnit()
	{
		return unit;
	}

	public void setTuneValue(float tuneValue)
	{
		this.tuneValue = tuneValue;
	}
	
	public float getTuneValue()
	{
		return tuneValue;
	}

	/**
	 * @return Returns the absoluteMax.
	 */
	public float getAbsoluteMax() {
		return absoluteMax;
	}
	/**
	 * @param absoluteMax The absoluteMax to set.
	 */
	public void setAbsoluteMax(float absoluteMax) {
		this.absoluteMax = absoluteMax;
	}
	/**
	 * @return Returns the absoluteMin.
	 */
	public float getAbsoluteMin() {
		return absoluteMin;
	}
	/**
	 * @param absoluteMin The absoluteMin to set.
	 */
	public void setAbsoluteMin(float absoluteMin) {
		this.absoluteMin = absoluteMin;
	}
	/**
	 * @return Returns the precision.
	 */
	public int getPrecision() {
		return precision;
	}
	/**
	 * @param precision The precision to set.
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	/**
	 * @return Returns the recommendMax.
	 */
	public float getRecommendMax() {
		return recommendMax;
	}
	/**
	 * @param recommendMax The recommendMax to set.
	 */
	public void setRecommendMax(float recommendMax) {
		this.recommendMax = recommendMax;
	}
	/**
	 * @return Returns the recommendMin.
	 */
	public float getRecommendMin() {
		return recommendMin;
	}
	/**
	 * @param recommendMin The recommendMin to set.
	 */
	public void setRecommendMin(float recommendMin) {
		this.recommendMin = recommendMin;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return Returns the numericData.
	 */
	public boolean isNumericData()
	{
		return numericData;
	}
	
	/**
	 * @param numericData The numericData to set.
	 */
	public void setNumericData(boolean numericData)
	{
		this.numericData = numericData;
	}
}

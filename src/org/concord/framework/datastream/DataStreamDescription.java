package org.concord.framework.datastream;

public class DataStreamDescription
{
	public final static int DATA_SEQUENCE = 0;
	public final static int DATA_SERIES = 1;

	private int dataType = DATA_SEQUENCE;
	private int channelPerSample;
	private float dt;
	private int dataOffset = 0;
	private int nextSampleOffset = 1;
	
	private String unit = null;
	private float tuneValue = 1.0f;

	// power of 10 precision
	private int precision;

	// The absolute min and max of the data or NaN if
	// not available
	private float absoluteMin, absoluteMax;

	// The recommended min and max of the data or NaN if
	// not available
	private float recommendMin, recommendMax;

	public DataStreamDescription(){
		this(0.0f,1);
	}

	public DataStreamDescription(float dt,int chPerSample){
		this.dt = dt;
		this.channelPerSample 	= chPerSample;
	}
	
	public void setDt(float dt)
	{
		this.dt = dt;
	}

	public float getDt()
	{
		return dt;
	}

	public void	setChannelPerSample(int chPerSample)
	{
		this.channelPerSample = chPerSample;
	}

	public int getChannelPerSample()
	{
		return channelPerSample;
	}

	public void	setDataType(int dataType)
	{
		this.dataType = dataType;
	}

	public int getDataType()
	{
		return dataType;
	}

	public void	setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getUnit()
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

	public void setDataOffset(int dataOffset)
	{
		this.dataOffset = dataOffset;
	}

	public int getDataOffset()
	{
		return dataOffset;
	}

	public void setNextSampleOffset(int next)
	{
		nextSampleOffset = next;
	}

	/** 
	 * This returns how much the index must be incremented to 
	 * get to the next sample.
	 * @return
	 */
	public int getNextSampleOffset()
	{
		return nextSampleOffset;
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
}

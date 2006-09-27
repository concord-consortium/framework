package org.concord.framework.otrunk;

import java.lang.reflect.Field;

public abstract class DefaultOTWrapper extends DefaultOTObject 
	implements OTWrapper 
{
	public DefaultOTWrapper(OTResourceSchema resources)
	{
		super(resources);
	}

	/**
	 * The default implementation of this calls getRealObjectClass and
	 * instanciates an object of that type. 
	 * You need to override this one or more of your your real object classes 
	 * do not have a null constructor.  
	 * 
	 * See getRealObjectClass to understand how it works by default
	 * 
	 * If you only have one real object class with a null constructor 
	 * then you just need to add a property to your class:
	 * 
	 * public final static Class [] wrappedObjectClasses
	 * 
	 * If you have multiple classes with null constructor you should still
	 * use the property above, but you should also override getRealObjectClass
	 * your implementation should look at the state in the OT object and determine
	 * which class to return.
	 * 
	 */
	public Object createRealObject()
	{
		return createRealObjectInternal();
	}

	/**
	 * instanciates the class returned by getRealObjectClass.
	 *
	 * @return
	 */
	protected final Object createRealObjectInternal()
	{
		Class realObjectClass = getRealObjectClass();
		try {
			return realObjectClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	

	/**
	 * This method is used by the default implementation of 
	 * createRealObject.  It is also used by createRealObjectInternal
	 * 
	 * By default this method looks for a static field on the class:
	 * 
	 * public final static Class [] wrappedObjectClasses
	 * 
	 * It then takes the first element of that array and returns it.
	 * 
	 * You will need to override this if you have multiple classes and need
	 * to decide which one to use based on the content of the your OT object. 
	 * 
	 * @return
	 */
	protected Class getRealObjectClass()
	{
		return getRealObjectClassInternal();
	}
	
	/**
	 * This method is useful if a class inbetween your class and this class
	 * overrode the default behavior of getRealObjectClass()
	 * 
	 * @see org.concord.framework.otrunk.OTWrapper#getRealObjectClass()
	 */
	protected final Class getRealObjectClassInternal()
	{
		try {
			Field field = getClass().getField("realObjectClasses");
			Class [] objClasses = (Class [])field.get(null);
			return objClasses[0];
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}

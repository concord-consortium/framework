package org.concord.framework.util;


public class EnumHelper {
    public static Object getValueByOrdinal(Class<Enum> klass, int valueOrdinal) {
        for(Enum constant: klass.getEnumConstants()){
            if(constant.ordinal() == valueOrdinal){
                return constant;
            }
        }
        
        return null;
    }
    
    public static Object getValueByName(Class<Enum> klass, String valueName) {
        for(Enum constant: klass.getEnumConstants()){
            if(constant.name().equals(valueName)){
                return constant;
            }
        }
        
        return null;
    }
    
    public static Object getValue(Class<Enum> klass, String value) {
        try{
             Integer integer = Integer.decode(value);
             Object ret = getValueByOrdinal(klass, integer); 
             if(ret != null){
                 return ret;
             }
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }

        Object ret = getValueByName(klass, value.trim());
        if(ret != null){
            return ret;
        }
        
        return null;
    }
    
    /* FIXME This method isn't working right now... */
    public static Object getValue(String className, String value) {
        Class<?> klass;
        try {
            klass = Class.forName(className);
            if (klass.isAssignableFrom(Enum.class)) {
                return getValue((Class<Enum>) klass, value);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package org.concord.framework.system;

import java.awt.Color;
import java.util.prefs.Preferences;
import java.util.prefs.BackingStoreException;

public class  SingleProperty{
    String name;
    Object value;
    public SingleProperty(String name,int value){
        this(name, new Integer(value));
    }

    public SingleProperty(String name,boolean value){
        this(name, new Boolean(value));
    }

    public SingleProperty(String name,double value){
        this(name, new Double(value));
    }

    public SingleProperty(String name,float value){
        this(name, new Float(value));
    }

    public SingleProperty(String name,Object value){
        this.name = name;
        this.value = value;
    }
    
    public String getName(){return name;}
    public Object getValue(){return value;}
    
    public void setName(String name){this.name = name;}
    public void setValue(Object value){this.value = value;}

    
    
    public static String unquote(String str){
        if(str == null) return null;
        int bi = 0;
        int ei = str.length();
        boolean doUnquote = false;
        if(str.startsWith("\"") || str.startsWith("\'")){
            bi++;
            doUnquote = true;
        }
        if(str.endsWith("\"") || str.endsWith("\'")){
            ei--;
            doUnquote = true;
        }
        return (doUnquote)?str.substring(bi,ei):str;
    }
    
    public static Color parseColorPropertyValue(String nm){
        Color retValue = null;
        if(nm == null) return retValue;
        try{
            if(!nm.startsWith("0x")){
                retValue = Color.decode("0x"+nm);
            }else{
                retValue = Color.decode(nm);
            }
        }catch(Exception e){}        
        return retValue;
    }
    
    public static void updateSingleProperty(Preferences node,String key,SingleProperty singleProperty){
        updateSingleProperty(node,key,singleProperty,false);
    }
    
    public static void updateSingleProperty(Preferences node,String key,SingleProperty singleProperty,boolean initTime){
        if(node == null || singleProperty == null) return;
        if(singleProperty.value instanceof Integer){
            int v = ((Integer)singleProperty.value).intValue();
            if(initTime){
                v = node.getInt(key,v);
                singleProperty.value = new Integer(v);
            }
            node.putInt(key,v);
        }else if(singleProperty.value instanceof Long){
            long v = ((Float)singleProperty.value).longValue();
            if(initTime){
                v = node.getLong(key,v);
                singleProperty.value = new Long(v);
            }
            node.putLong(key,v);
        }else if(singleProperty.value instanceof Float){
            float v = ((Float)singleProperty.value).floatValue();
            if(initTime){
                v = node.getFloat(key,v);
                singleProperty.value = new Float(v);
            }
            node.putFloat(key,v);
        }else if(singleProperty.value instanceof Double){
            double v = ((Double)singleProperty.value).doubleValue();
            if(initTime){
                v = node.getDouble(key,v);
                singleProperty.value = new Double(v);
            }
            node.putDouble(key,v);
        }else if(singleProperty.value instanceof Boolean){
            boolean v = ((Boolean)singleProperty.value).booleanValue();
            if(initTime){
                v = node.getBoolean(key,v);
                singleProperty.value = new Boolean(v);
            }
            node.putBoolean(key,v);
        }else if(singleProperty.value instanceof Color){
            Color c = (Color)singleProperty.value;
            int v = c.getRGB(); 
            if(initTime){
                v = node.getInt(key,v);
                singleProperty.value = new Color(v);
            }
            node.putInt(key,v);
        }else if(singleProperty.value instanceof String){
            String v = (String)singleProperty.value;
            if(initTime){
                v = node.get(key,v);
                singleProperty.value = v;
            }
            node.put(key,v);
        }else if(singleProperty.value instanceof byte[]){
            byte[] v = (byte [])singleProperty.value;
            if(initTime){
                v = node.getByteArray(key,v);
                singleProperty.value = v;
            }
            node.putByteArray(key,v);
        }
        try{
            node.flush();
        }catch(BackingStoreException e){
            System.out.println("updateSingleProperty BackingStoreException "+e);
        }

    }

    public static void printPreferences(Preferences pref){
        if(pref == null){
            System.out.println("Preferences == NULL");
            return;
        }
        System.out.println("Preferences "+pref.absolutePath());
        try{
            String []keys = pref.keys();
            System.out.println("keys "+keys.length);
            for(int i = 0; i < keys.length; i++){
                System.out.println("keys["+i+"] = "+keys[i]);
                System.out.println("value["+i+"] = "+pref.get(keys[i],null));
            }
        }catch(BackingStoreException bse){
            System.out.println("BackingStoreException "+bse);
        }
    }
}

package org.concord.framework.otrunk.wrapper;

import org.concord.framework.otrunk.DefaultOTObject;
import org.concord.framework.otrunk.OTResourceSchema;
import org.concord.framework.util.EnumHelper;

public class OTEnumValue extends DefaultOTObject {
    public static interface ResourceSchema extends OTResourceSchema 
    {
        public String getType();
        public void setType(String var);
        
        public String getValue();
        public void setValue(String var);
    }
    private ResourceSchema resources;

    public OTEnumValue(ResourceSchema resources)
    {
        super(resources);
        this.resources = resources;
    }
    
    public Object getValue() {
        return EnumHelper.getValue(resources.getType(), resources.getValue());
    }
    
    public void setValue(Enum value) {
        resources.setType(value.getClass().getSimpleName());
        resources.setValue(value.name());
    }
}

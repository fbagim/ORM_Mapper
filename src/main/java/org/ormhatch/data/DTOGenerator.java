package org.ormhatch.data;

import java.util.Locale;
import java.util.Map;

public class DTOGenerator extends ClassGenerator{

    @Override
    public String buildClass(String className, Map<String,TableData> attributes, String pkgName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("package "+pkgName);
        stringBuffer.append("\n");
        stringBuffer.append("public class "+className +" {");
        stringBuffer.append("\n");
        for(Object key : attributes.keySet()){
            String attribute = (String) key;
            TableData tableData =  attributes.get(attribute);
            stringBuffer.append("\t");
            String dataType = DataTypeMapper.dataTypeMap.get(tableData.getDatatype());
            stringBuffer.append("private "+dataType+" "+lowerCase(attribute)+";");
            stringBuffer.append("\n");
        }
        stringBuffer.append("\n");
        for(Object key : attributes.keySet()){
            String attribute = (String) key;
            TableData tableData =  attributes.get(attribute);
            stringBuffer.append("\t");
            String dataType = DataTypeMapper.dataTypeMap.get(tableData.getDatatype());
            stringBuffer.append("public "+dataType+" get"+upperCase(attribute)+"() {");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("\t");
            stringBuffer.append("return "+lowerCase(attribute)+";");

            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("}");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("public void "+dataType+" set"+upperCase(attribute)+"("+dataType+" "+lowerCase(attribute) +"){");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("\t");
            stringBuffer.append("this."+lowerCase(attribute)+" = "+lowerCase(attribute)+";");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("}");
            stringBuffer.append("\n");
        }
        stringBuffer.append("}");
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }
    public static String lowerCase(String str)
    {
        if(str == null) return str;
        return str.substring(0, 1).toLowerCase(Locale.ROOT) + str.substring(1);
    }
    public static String upperCase(String str)
    {
        if(str == null) return str;
        return str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1);
    }

    @Override
    public String writeClass(String className, String classStr, String fileLocation) {
        return null;
    }
}

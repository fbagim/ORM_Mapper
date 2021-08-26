package org.ormhatch.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class DTOGenerator extends ClassGenerator{

    @Override
    public StringBuffer buildClass(String className, Map<String,TableData> attributes, String pkgName) {
        if(!className.isEmpty() && attributes.size()>0 && !pkgName.isEmpty() ){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(createPackage(pkgName,attributes));
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("@Entity");
            stringBuffer.append("\n");
            stringBuffer.append("@Table(name = "+className.toLowerCase(Locale.ROOT)+")");
            stringBuffer.append("\n");
            stringBuffer.append("public class "+className +" implements Serializable {");
            stringBuffer.append("\n");
            String pkId = null;
            for(Object key : attributes.keySet()){
                String attribute = (String) key;
                TableData tableData =  attributes.get(attribute);
                if(tableData.getPk()){
                    pkId = key.toString();
                    stringBuffer.append("\t");
                    stringBuffer.append("@Id");
                    stringBuffer.append("\n");
                    stringBuffer.append("\t");
                    stringBuffer.append("@GeneratedValue(strategy = GenerationType.IDENTITY)");
                    stringBuffer.append("\n");
                    stringBuffer.append("\t");
                    String dataType = DataTypeMapper.dataTypeMap.get(tableData.getDatatype());
                    stringBuffer.append("private "+ dataType+" "+lowerCase(attribute)+";");
                    stringBuffer.append("\n");
                }
            }
            attributes.remove(pkId);

            for(Object key : attributes.keySet()){
                String attribute = (String) key;
                TableData tableData =  attributes.get(attribute);

                String dataType = DataTypeMapper.dataTypeMap.get(tableData.getDatatype());
                stringBuffer.append("\t");
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
            return stringBuffer;
        }
        return null;
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
    public void writeClass(String className, String classStr, String fileLocation,String packageName) throws Exception {
        String filePath = null;
        if (!packageName.isEmpty() && !fileLocation.isEmpty()) {
            filePath = packageName.replace(".", "/");

            File location = new File(fileLocation + "/" + filePath);
            BufferedWriter writer = null;
            try {
                if(!location.exists()){
                    location.mkdirs();
                }
                File file = new File(fileLocation + "/" + filePath + "/" + className + ".java");
              //  file.deleteOnExit();
                file.createNewFile();

                writer = new BufferedWriter(new FileWriter(file));
                writer.write(classStr);
                writer.flush();
            } catch (IOException e) {
                writer.close();
                throw e;
            } finally {
                if (writer != null) writer.close();
            }
        } else {
            throw new Exception("PackageName or fileLocation invalid");
        }
    }

    @Override
    public StringBuffer createPackage(String packageName, Map<String, TableData> attributes) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("package "+packageName);
        stringBuffer.append("\n");
        stringBuffer.append("\n");
        stringBuffer.append("import java.io.Serializable;");
        stringBuffer.append("\n");
        stringBuffer.append("import java.lang.*;");
        stringBuffer.append("\n");
        stringBuffer.append("import java.time.LocalDateTime;");
        stringBuffer.append("\n");
        stringBuffer.append("import java.util.*;");
        stringBuffer.append("\n");
        stringBuffer.append("import javax.persistence.Entity;");
        stringBuffer.append("\n");
        stringBuffer.append("import javax.persistence.GenerationType;");
        stringBuffer.append("\n");
        stringBuffer.append("import javax.persistence.GeneratedValue;");
        stringBuffer.append("\n");
        stringBuffer.append("import javax.persistence.Id;");
        stringBuffer.append("\n");
        stringBuffer.append("import javax.persistence.OneToOne;");
        stringBuffer.append("\n");
        stringBuffer.append("import javax.persistence.Table");

        return  stringBuffer;
    }
}

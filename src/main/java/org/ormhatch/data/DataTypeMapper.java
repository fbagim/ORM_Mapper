package org.ormhatch.data;

import java.util.HashMap;
import java.util.Map;

public class DataTypeMapper {
    public static Map<String, String> dataTypeMap = new HashMap();

    public static  void addData(String type){
        if(type.equals("BIT")){
            dataTypeMap.put("BIT","Boolean");
        }  else if(type.equals("INT")) {
            dataTypeMap.put("INT", "Integer");
        } else if(type.equals("INT UNSIGNED")){
            dataTypeMap.put("INT UNSIGNED", "Integer");
        }else if(type.equals("BIGINT")){
            dataTypeMap.put("BIGINT","Long");
        }else if(type.equals("FLOAT")){
            dataTypeMap.put("FLOAT","Float");
        }else if(type.equals("NUMERIC")){
            dataTypeMap.put("NUMERIC","BigDecimal");
        }else if(type.equals("DECIMAL")){
            dataTypeMap.put("DECIMAL","BigDecimal");
        }else if(type.equals("DECIMAL")){
            dataTypeMap.put("DECIMAL","BigDecimal");
        }else if(type.equals("DATETIME")){
            dataTypeMap.put("DATETIME","Date");
        }else if(type.equals("TIMESTAMP")){
            dataTypeMap.put("TIMESTAMP","Date");
        }else if(type.equals("TIME")){
            dataTypeMap.put("TIME","Date");
        }else if(type.equals("DATE")){
            dataTypeMap.put("DATE","Date");
        }else if(type.equals("CHAR")){
            dataTypeMap.put("CHAR","String");
        }else if(type.equals("VARCHAR")){
            dataTypeMap.put("VARCHAR","String");
        }else if(type.equals("TEXT")){
            dataTypeMap.put("TEXT","String");
        }else if(type.equals("BINARY")){
            dataTypeMap.put("BINARY","byte[]");
        }else if(type.equals("VARBINARY")){
            dataTypeMap.put("VARBINARY","byte[]");
        }else if(type.equals("BLOB")){
            dataTypeMap.put("BLOB","byte[]");
        }
    }
}

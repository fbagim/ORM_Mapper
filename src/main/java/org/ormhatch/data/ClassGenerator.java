package org.ormhatch.data;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ClassGenerator {
   public abstract StringBuffer buildClass(String className, ConcurrentHashMap<String ,TableData> attributes, String pkgName) throws Exception;
   public abstract Boolean writeClass(String className, String classStr, String fileLocation,String packageName) throws IOException;
   public abstract StringBuffer createPackage(String packageName,ConcurrentHashMap<String,TableData> attributes) throws Exception;

   public StringBuffer createPackageDesc() throws Exception {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("/*This Code is generated by ORM Plugin and developed by Asanka Gimhana..*/");
      return stringBuffer;
   }
}

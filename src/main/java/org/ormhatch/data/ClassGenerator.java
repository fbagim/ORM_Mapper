package org.ormhatch.data;

import java.io.IOException;
import java.util.Map;

public abstract class ClassGenerator {
   public abstract StringBuffer buildClass(String className, Map<String ,TableData> attributes, String pkgName);
   public abstract void writeClass(String className, String classStr, String fileLocation,String packageName) throws Exception;
   public abstract StringBuffer createPackage(String packageName,Map<String,TableData> attributes) throws Exception;
}

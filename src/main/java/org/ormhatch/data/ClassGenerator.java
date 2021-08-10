package org.ormhatch.data;

import java.util.Map;

public abstract class ClassGenerator {
   public abstract String buildClass(String className, Map<String ,TableData> attributes, String pkgName);
   public abstract String writeClass(String className, String classStr, String fileLocation);
}

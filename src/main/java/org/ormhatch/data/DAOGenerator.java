package org.ormhatch.data;

import org.ormhatch.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class DAOGenerator extends ClassGenerator {
    @Override
    public StringBuffer buildClass(String className, ConcurrentHashMap<String, TableData> attributes, String pkgName) throws Exception {
        if(!className.isEmpty() && !pkgName.isEmpty() ){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(createPackageDesc());
            stringBuffer.append("\n");
            stringBuffer.append(createPackage(pkgName,attributes));
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("public interface "+ Util.toTitleCase(className) +"Repository extends CrudRepository<"+Util.toTitleCase(className)+", Long> {");
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("}");
            return stringBuffer;
        }
        return null;
    }

    @Override
    public void writeClass(String className, String classStr, String fileLocation, String packageName) throws Exception {
        String filePath = null;
        if (!packageName.isEmpty() && !fileLocation.isEmpty()) {
            filePath = packageName.replace(".", "/");

            File location = new File(fileLocation + "/" + filePath);
            BufferedWriter writer = null;
            try {
                if(!location.exists()){
                    location.mkdirs();
                }
                File file = new File(fileLocation + "/" + filePath + "/" + Util.toTitleCase(className)  + "Repository.java");
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
    public StringBuffer createPackage(String packageName, ConcurrentHashMap<String, TableData> attributes) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("package "+packageName+".repository");
        stringBuffer.append("\n");
        stringBuffer.append("\n");
        stringBuffer.append("import "+packageName+".model");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.data.repository.CrudRepository;");
        stringBuffer.append("\n");
        return  stringBuffer;
    }
}

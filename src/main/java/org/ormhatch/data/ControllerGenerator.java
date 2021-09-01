package org.ormhatch.data;

import org.ormhatch.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ControllerGenerator extends ClassGenerator {
    @Override
    public StringBuffer buildClass(String className, ConcurrentHashMap<String, TableData> attributes, String pkgName) throws Exception {
        if(!className.isEmpty() && !pkgName.isEmpty() ){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(createPackageDesc());
            stringBuffer.append("\n");
            stringBuffer.append(createPackage(pkgName,attributes));
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("@RestController");
            stringBuffer.append("\n");
            stringBuffer.append("public class  "+ Util.toTitleCase(className) +"    {");
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("private final "+Util.toTitleCase(className)+"Repository "+className+"Repository;");
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("@Autowired");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("public "+Util.toTitleCase(className)+"Controller("+Util.toTitleCase(className)+"Repository "+className+"Repository) {");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("\t");
            stringBuffer.append("this."+className+"Repository = "+className+"Repository;");
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("}");
            stringBuffer.append("\n");

            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("@GetMapping('/"+className+"s/{id}')");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("public "+Util.toTitleCase(className)+" find"+Util.toTitleCase(className)+"ById(@PathVariable(\"id\") final Long id) {");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("\t");
            stringBuffer.append("return "+className+";");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("}");
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("@PostMapping('/"+className+"s')");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("@ResponseStatus(HttpStatus.OK)");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("public "+Util.toTitleCase(className)+" create"+Util.toTitleCase(className)+"(@RequestBody("+ Util.toTitleCase(className)+" "+className +") {");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("\t");
            stringBuffer.append("return "+className+";");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("}");
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("@PutMapping('/"+className+"s')");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("@ResponseStatus(HttpStatus.OK)");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("public "+Util.toTitleCase(className)+" update"+Util.toTitleCase(className)+"(@RequestBody("+ Util.toTitleCase(className)+" "+className +") {");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("\t");
            stringBuffer.append("return "+className+";");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("}");
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("@DeleteMapping('/"+className+"s')");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("@ResponseStatus(HttpStatus.OK)");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("public "+Util.toTitleCase(className)+" delete"+Util.toTitleCase(className)+"(@RequestBody("+ Util.toTitleCase(className)+" "+className +") {");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("\t");
            stringBuffer.append("return "+className+";");
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append("}");
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("}");
            return stringBuffer;
        }
        return null;
    }

    @Override
    public Boolean writeClass(String className, String classStr, String fileLocation, String packageName) throws IOException{

        String filePath = null;
        if (!packageName.isEmpty() && !fileLocation.isEmpty()) {
            filePath = packageName.replace(".", "/");

            File location = new File(fileLocation + "/" + filePath);
            BufferedWriter writer = null;
            try {
                if(!location.exists()){
                    location.mkdirs();
                }
                File file = new File(fileLocation + "/" + filePath + "/" + Util.toTitleCase(className)  + "Controller.java");
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
            return true;
        } else {
            throw new IOException("PackageName or fileLocation invalid");
        }
    }

    @Override
    public StringBuffer createPackage(String packageName, ConcurrentHashMap<String, TableData> attributes) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("package "+packageName+".controllers");
        stringBuffer.append("\n");
        stringBuffer.append("\n");
        stringBuffer.append("import "+packageName+".model");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.beans.factory.annotation.Autowired;");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.data.domain.Page;");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.data.domain.PageRequest;");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.data.domain.Pageable;");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.data.domain.Sort;");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.data.querydsl.binding.QuerydslPredicate;");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.web.bind.annotation.GetMapping;");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.web.bind.annotation.PathVariable;");
        stringBuffer.append("\n");
        stringBuffer.append("import org.springframework.web.bind.annotation.RestController;");

        return  stringBuffer;
    }
}

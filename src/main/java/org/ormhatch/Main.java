package org.ormhatch;

import org.ormhatch.data.*;
import org.ormhatch.db.DBConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String ss[]) throws Exception {
        ConcurrentHashMap<String, TableData> map = new ConcurrentHashMap<>();
        Connection connection = DBConnector.getConnection("","","","");
        ResultSet resultSet = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        ResultSet resultSet2 = connection.getMetaData().getTypeInfo();
        List<Pkfk> fkList = new ArrayList();

        System.out.println("DATA TYPE");
        System.out.println("----------------------------------");
        DTOGenerator dtoGenerator = new DTOGenerator();
        DAOGenerator daoGenerator = new DAOGenerator();
        ControllerGenerator ctlGenerator = new ControllerGenerator();

        while (resultSet2.next()) {
            DataTypeMapper.addData(resultSet2.getString("TYPE_NAME"));
        }

        System.out.println("Printing TABLE_TYPE \"TABLE\" ");
        System.out.println("----------------------------------");
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            ResultSet resultSetPK = connection.getMetaData().getPrimaryKeys(null, null, tableName);
            ResultSet resultSetFK = connection.getMetaData().getExportedKeys(null, null, tableName);
            List pkList = new ArrayList();

            while (resultSetPK.next()) {
                pkList.add(resultSetPK.getString(".COLUMN_NAME"));
            }
            while (resultSetFK.next()) {
                Pkfk pkfk = new Pkfk(tableName,resultSetFK.getString("PKTABLE_NAME"),resultSetFK.getString(".FKTABLE_NAME") ,false,false,false,resultSetFK.getString("PKCOLUMN_NAME"));
                fkList.add(pkfk);
            }
            if (fkList.size()>0) {
                for(Pkfk pkfk : fkList){
                    TableData tableData =null;
                    if(!pkfk.getDone()){
                        if(!pkfk.getDoneL() && !pkfk.getDoneR()){
                            tableData = new TableData( pkfk.getPkCol(),pkfk.getFkTable(), false,false, false, true, pkfk.getFkTable(), true,false,false);
                            pkfk.setDoneL(true);
                        } else if(!pkfk.getDoneL()){
                            tableData = new TableData( pkfk.getPkCol(),pkfk.getFkTable(), false,false, false, true, pkfk.getFkTable(), true,false,false);
                            pkfk.setDoneL(true);
                        }else if(!pkfk.getDoneR()){
                            tableData = new TableData( pkfk.getPkCol(), pkfk.getFkTable(), false,false, false, true, pkfk.getFkTable(), false,true,false);
                            pkfk.setDoneR(true);
                        } else if(pkfk.getDoneL() && pkfk.getDoneR()){
                            tableData = new TableData( pkfk.getPkCol(),pkfk.getFkTable(), false,false, false, true, pkfk.getFkTable(), true,true,true);
                            pkfk.setDone(true);
                        }
                    }
                    map.put(pkfk.getFkTable(), tableData);
                }
            }
            ResultSet columns = connection.getMetaData().getColumns(null, null, resultSet.getString("TABLE_NAME"), null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String datatype = columns.getString("TYPE_NAME");
                String isNullable = columns.getString("IS_NULLABLE");
                String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");

                //Printing results
                TableData tableData = null;
                if (pkList.contains(columnName)) {
                    tableData = new TableData(columnName, datatype, Boolean.getBoolean(isNullable), Boolean.getBoolean(is_autoIncrment), true, false, null,false,false,false);
                    map.put(columnName, tableData);
                } else {
                    tableData = new TableData(columnName, datatype, Boolean.getBoolean(isNullable), Boolean.getBoolean(is_autoIncrment), false, false, null, false,false,false);
                    map.put(columnName, tableData);
                }
            }

            StringBuffer stringBufferDto = dtoGenerator.buildClass(tableName, map, "com.test.data");
            if (stringBufferDto != null) {
                try {
                    dtoGenerator.writeClass(tableName, stringBufferDto.toString(), "D:\\MSC\\ORM_TEST_2", "com.test.model");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            StringBuffer stringBufferDao = daoGenerator.buildClass(tableName, null, "com.test.data");
            if (stringBufferDto != null) {
                try {
                    daoGenerator.writeClass(tableName, stringBufferDao.toString(), "D:\\MSC\\ORM_TEST_2", "com.test.repository");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            StringBuffer stringBufferCotroller = ctlGenerator.buildClass(tableName, null, "com.test.data");
            if (stringBufferDto != null) {
                try {
                    ctlGenerator.writeClass(tableName, stringBufferCotroller.toString(), "D:\\MSC\\ORM_TEST_2", "com.test.controller");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

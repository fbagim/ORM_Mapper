package org.ormhatch;

import org.ormhatch.data.DTOGenerator;
import org.ormhatch.data.DataTypeMapper;
import org.ormhatch.data.TableData;
import org.ormhatch.db.DBConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

        public static void main(String ss[]) throws SQLException {
            HashMap<String, TableData> map = new HashMap<>();
            Connection connection =  DBConnector.getConnection();
            ResultSet resultSet = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
            ResultSet resultSet2 = connection.getMetaData().getTypeInfo();

            System.out.println("DATA TYPE");
            System.out.println("----------------------------------");
            DTOGenerator dtoGenerator = new DTOGenerator();

            while(resultSet2.next())
            {
                DataTypeMapper.addData(resultSet2.getString("TYPE_NAME"));
            }

            System.out.println("Printing TABLE_TYPE \"TABLE\" ");
            System.out.println("----------------------------------");
            while(resultSet.next())
            {
                String tableName = resultSet.getString("TABLE_NAME");
                ResultSet resultSetPK = connection.getMetaData().getPrimaryKeys(null,null,tableName);
                ResultSet resultSetFK = connection.getMetaData().getExportedKeys(null,null,tableName);
                List pkList = new ArrayList();
                List fkList = new ArrayList();

                while(resultSetPK.next())
                {
                    pkList.add(resultSetPK.getString(".COLUMN_NAME"));
                }
                while(resultSetFK.next())
                {
                    fkList.add(resultSetPK.getString(".COLUMN_NAME"));
                }

                ResultSet columns = connection.getMetaData().getColumns(null,null, resultSet.getString("TABLE_NAME"), null);
                while(columns.next())
                {
                    String columnName = columns.getString("COLUMN_NAME");
                    String datatype = columns.getString("TYPE_NAME");
                    String isNullable = columns.getString("IS_NULLABLE");
                    String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");

                    //Printing results
                    TableData tableData =null;
                    if(pkList.contains(columnName)){
                          tableData = new TableData(columnName, datatype,Boolean.getBoolean(isNullable),Boolean.getBoolean(is_autoIncrment),true,false);
                    } else if(fkList.contains(columnName)){
                          tableData = new TableData(columnName, datatype,Boolean.getBoolean(isNullable),Boolean.getBoolean(is_autoIncrment),false,true);
                    } else {
                        tableData = new TableData(columnName, datatype,Boolean.getBoolean(isNullable),Boolean.getBoolean(is_autoIncrment),false,false);
                    }
                    map.put(columnName,tableData);
                }

                StringBuffer stringBuffer =  dtoGenerator.buildClass(tableName,map,"com.test.data");
                if(stringBuffer !=null){
                    try {
                        dtoGenerator.writeClass(tableName,stringBuffer.toString(),"D:\\MSC\\ORM_TEST_1","com.test.data");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}

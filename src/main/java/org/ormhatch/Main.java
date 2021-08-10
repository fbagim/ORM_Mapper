package org.ormhatch;

import org.ormhatch.data.DTOGenerator;
import org.ormhatch.data.DataTypeMapper;
import org.ormhatch.data.TableData;
import org.ormhatch.db.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
                ResultSet columns = connection.getMetaData().getColumns(null,null, resultSet.getString("TABLE_NAME"), null);
                while(columns.next())
                {
                    String columnName = columns.getString("COLUMN_NAME");
                    String datatype = columns.getString("TYPE_NAME");
                    String isNullable = columns.getString("IS_NULLABLE");
                    String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
                    //Printing results
                    TableData tableData = new TableData(columnName, datatype,Boolean.getBoolean(isNullable),Boolean.getBoolean(is_autoIncrment));
                    map.put(columnName,tableData);
                }
                dtoGenerator.buildClass(tableName,map,"");
            }


        }
}

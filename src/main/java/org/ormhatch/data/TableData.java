package org.ormhatch.data;

public class TableData {
    private String columnName;
    private String datatype;
    private Boolean isNullable;
    private Boolean isAutoIncrment;

    public TableData(String columnName, String datatype, Boolean isNullable, Boolean isAutoIncrment) {
        this.columnName = columnName;
        this.datatype = datatype;
        this.isNullable = isNullable;
        this.isAutoIncrment = isAutoIncrment;
    }
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public Boolean getNullable() {
        return isNullable;
    }

    public void setNullable(Boolean nullable) {
        isNullable = nullable;
    }

    public Boolean getAutoIncrment() {
        return isAutoIncrment;
    }

    public void setAutoIncrment(Boolean autoIncrment) {
        isAutoIncrment = autoIncrment;
    }
}

package org.ormhatch.data;

public class TableData {
    private String columnName;
    private String datatype;
    private Boolean isNullable;
    private Boolean isAutoIncrment;
    private Boolean isPk;
    private Boolean isFk;
    private Boolean isLeft;
    private String joinedTable;
    private Boolean isRight;
    private Boolean isDone;


    public TableData(String columnName, String datatype, Boolean isNullable, Boolean isAutoIncrment,Boolean isPk,Boolean isFk, String joinedTable, Boolean isLeft, Boolean isRight, Boolean isDone) {
        this.columnName = columnName;
        this.datatype = datatype;
        this.isNullable = isNullable;
        this.isAutoIncrment = isAutoIncrment;
        this.isAutoIncrment = isAutoIncrment;
        this.isPk = isPk;
        this.isFk = isFk;
        this.joinedTable = joinedTable;
        this.isLeft = isLeft;
        this.isRight = isRight;
        this.isDone = isDone;
    }

    public String getJoinedTable() {
        return joinedTable;
    }

    public void setJoinedTable(String joinedTable) {
        this.joinedTable = joinedTable;
    }

    public Boolean getFk() {
        return isFk;
    }

    public void setFk(Boolean fk) {
        isFk = fk;
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

    public Boolean getPk() {
        return isPk;
    }

    public void setPk(Boolean pk) {
        isPk = pk;
    }

    public Boolean getLeft() {
        return isLeft;
    }

    public void setLeft(Boolean left) {
        isLeft = left;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}

package org.ormhatch.data;

public class Pkfk {
    private String tableName;
    private String pkCol;
    private String colName;
    private String fkTable;
    private Boolean isDoneL;
    private Boolean isDoneR;
    private Boolean isDone;

    public Pkfk(String tableName, String pkCol, String fkTable, Boolean isDoneL, Boolean isDoneR, Boolean isDone,String colName) {
        this.tableName = tableName;
        this.pkCol = pkCol;
        this.fkTable = fkTable;
        this.isDoneL = isDoneL;
        this.isDoneR = isDoneR;
        this.isDone = isDone;
        this.colName = colName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Boolean getDoneL() {
        return isDoneL;
    }

    public void setDoneL(Boolean doneL) {
        isDoneL = doneL;
    }

    public Boolean getDoneR() {
        return isDoneR;
    }

    public void setDoneR(Boolean doneR) {
        isDoneR = doneR;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPkCol() {
        return pkCol;
    }

    public void setPkCol(String pkCol) {
        this.pkCol = pkCol;
    }

    public String getFkTable() {
        return fkTable;
    }

    public void setFkTable(String fkTable) {
        this.fkTable = fkTable;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}

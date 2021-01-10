package com.ett.pdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vin lan
 * @className TextRow
 * @description TODO
 * @createTime 2021-01-08  10:31
 **/
public class TextRow {
    double y;
    int rowIndex;
    Map<Double, TextColumn> columnsMap = new HashMap<Double, TextColumn>();
    List<TextColumn> columns = new ArrayList<>();
    String rowContent = "";

    /**
     * 重要，由于解析的时候，columnsMap中的x值是乱序的(因为itext解析也是有点乱序的)，因此需要对columnsMap进行顺序排序之后，
     * 才能得到正确的列，才能将排序好的数据放进columns中。
     */
    public void sortAndAddColumns() {
        this.rowContent = "";
        columns.clear();
        columnsMap.entrySet().stream().
                sorted(Map.Entry.<Double, TextColumn>comparingByKey()).forEachOrdered(entry -> {
            entry.getValue().setColumnIndex(columns.size());
            columns.add(entry.getValue());
            rowContent = rowContent + " " + entry.getValue().getContent();
        });
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getRowContent() {
        return rowContent;
    }

    public void addRowContent(String str) {
        this.rowContent = rowContent.concat(" ").concat(str);
    }

    public int getColumnsNumber() {
        if (this.columns != null) {
            return this.columns.size();
        } else {
            return 0;
        }
    }

    public Map<Double, TextColumn> getColumnsMap() {
        return columnsMap;
    }

    public List<TextColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TextColumn> columns) {
        this.columns = columns;
    }
}

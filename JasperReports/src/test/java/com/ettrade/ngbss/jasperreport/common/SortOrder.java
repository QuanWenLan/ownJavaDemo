package com.ettrade.ngbss.jasperreport.common;

public enum SortOrder {
    ASCENDING("asc"),
    DESCENDING("dsc");

    final String name;

    SortOrder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SortOrder getSortOrder(String sortOrderStr) {
        for (SortOrder sortOrder : SortOrder.values()) {
            if (sortOrder.getName().equals(sortOrderStr)) {
                return sortOrder;
            }
        }
        return null;
    }
}

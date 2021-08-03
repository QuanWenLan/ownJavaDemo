package com.ettrade.ngbss.jasperreport.common;

public class SortKey {
    private String attribute;
    private SortOrder order;

    public SortKey() {
    }

    public SortKey(String attribute, SortOrder order) {
        this.attribute = attribute;
        this.order = order;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public SortOrder getOrder() {
        return order;
    }

    public void setOrder(SortOrder order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return attribute + "." + order.getName();
    }

    public static SortKey fromString(String string) {
        String[] sortTmpArr = string.split("\\.");
        return new SortKey(sortTmpArr[0], SortOrder.getSortOrder(sortTmpArr[1]));
    }
}

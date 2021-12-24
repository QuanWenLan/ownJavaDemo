package com.ett.report;

public enum ReportMimeType {

    PDF(0x0001),
    XLS(0x0010),
    XLSX(0x0100),
    CSV(0x1000);

    private final int value;

    private ReportMimeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

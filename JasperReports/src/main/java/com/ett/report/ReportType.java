package com.ett.report;

public enum ReportType {

    ClientPortfolioReport(1),
    TransactionSummaryReport(2),
    ConfrimationNotes_Batch(3);

    private final int value;

    private ReportType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

package com.ett.report;

public interface JasperReportEngine extends JRConstants {

    public static final int CREATE_SUCCESSFUL = 1 << 0;

    public static final int CREATE_FAILED = 1 << 1;

    public static final int TEMPLATE_NOT_FOUND = 1 << 2;
    
    public static final int NO_DATA = 1 << 3;

    public int createReport(JRContext context);

}

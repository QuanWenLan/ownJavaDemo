package com.ett.report;


import java.util.Map;

public interface JRContext extends Map<String, Object>, JRConstants {

//    public ReportMimeType getReportMimeType();

    public String getTemplate();

    public JRConnectionProvider getConnectionProvider();

    public JRExporter getExporter();

    public String getSystemId();

//    public LoginUser getActor();

    public Class<? extends OFTPJRDataSource> getJRDataSourceClass();

    public String getPricePattern();

    public String getAmtPattern();

    public String getQtyPattern();

    public String getExchangeRatePattern();

    public String getRatePattern();
}

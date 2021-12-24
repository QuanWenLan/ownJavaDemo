package com.ett.report;


import java.util.HashMap;

public class DefaultJRContext extends HashMap<String, Object> implements JRContext {

    private static final long serialVersionUID = 1L;

    private ReportMimeType reportMimeType;
    private String template;
    private JRConnectionProvider connectionProvider;
    private JRExporter exporter;
    private String systemId;
//    private LoginUser actor = null;
    private Class<? extends OFTPJRDataSource> jrDataSourceClass;

    public ReportMimeType getReportMimeType() {
        return reportMimeType;
    }

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public JRConnectionProvider getConnectionProvider() {
        return connectionProvider;
    }

    @Override
    public JRExporter getExporter() {
        return exporter;
    }

/*    public LoginUser getActor() {
        return actor;
    }

    public void setActor(LoginUser actor) {
        this.actor = actor;
    }*/

    @Override
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public void setReportMimeType(ReportMimeType reportMimeType) {
        this.reportMimeType = reportMimeType;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setConnectionProvider(JRConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void setExporter(JRExporter exporter) {
        this.exporter = exporter;
    }

    @Override
    public Class<? extends OFTPJRDataSource> getJRDataSourceClass() {
        return jrDataSourceClass;
    }

    public void setJrDataSourceClass(Class<? extends OFTPJRDataSource> jrDataSourceClass) {
        this.jrDataSourceClass = jrDataSourceClass;
    }

    @Override
    public String getPricePattern() {
        return REPORT_PARAMETER_PRICE_PATTERN_VALUE;
    }

    @Override
    public String getAmtPattern() {
        return REPORT_PARAMETER_AMT_PATTERN_VALUE;
    }

    @Override
    public String getQtyPattern() {
        return REPORT_PARAMETER_QTY_PATTERN_VALUE;
    }

    @Override
    public String getExchangeRatePattern() {
        return REPORT_PARAMETER_EXCHANGE_RATE_PATTERN_VALUE;
    }

    @Override
    public String getRatePattern() {
        return REPORT_PARAMETER_RATE_PATTERN_VALUE;
    }

}
